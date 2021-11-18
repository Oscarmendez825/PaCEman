#include "server.h"
#include "variables.h"
#include "players.h"

int PORT = 8080;
void handleMessage(char* msg);

// Metodo que inicializa todo el servidor
void init_server()
{
    memset(clients_arr, 0, 4);
    current_client = 0;

    // Creacion del socket del servidor
    socket_desc = socket(AF_INET , SOCK_STREAM , 0);

    // Maneja errores de creacion del socket
    if (socket_desc == -1)
    {
        printf("No se pudo crear el socket");
    }
    puts("Socket creado");

    // Establece direcciones del socket
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(PORT);

    // Bind del socket al puerto y direccion deseados
    if (bind(socket_desc,(struct sockaddr *)&server , sizeof(server)) < 0)
    {
        perror("Enlace fallido. Error");
        return;
    }
    puts("Enlace hecho");

    // Escuchar posibles conexiones
    listen(socket_desc , 3);

    c = sizeof(struct sockaddr_in);

    puts("Esperando conexiones entrantes...");
    c = sizeof(struct sockaddr_in);
}

// Metodo para manejar al servidor en un thread
void run()
{
    pthread_t thread_id;

    // Aceptar conexiones
    while( (client_sock = accept(socket_desc, (struct sockaddr *)&client, (socklen_t*)&c)) )
    {
        puts("Conexion aceptada");

        // Thread para cada conexion especifica
        if( pthread_create(&thread_id, NULL, connection_handler, (void*) &client_sock) < 0)
        {
            perror("No se pudo crear el thread ");
            return;
        }

        puts("Controlador asignado");
        current_client++;
    }

    if (client_sock < 0)
    {
        perror("Aceptacion fallida");
        return;
    }
}


// Maneja cada conexion individualmente
void *connection_handler(void *socket_desc)
{
    int sock = *(int*) socket_desc;
    int read_size;
    char message[50], client_message[256];

    set_client(&sock);
    sprintf(message, "Eres el cliente numero %d \n", current_client);
    write(sock, message, strlen(message));

    // Se lee constantemente para recibir nuevos mensajes
    while ((read_size = recv(sock, client_message, 256, 0)) > 0)
    {
        // Mensajes devueltos a todos los clientes
        send_to_all(strcat(client_message, "\n"));

        // Analizar mensaje
        if (client_message != "")
        {
            handleMessage(client_message);
        }
        
        bzero(client_message, 256);
    }

    if (read_size == 0)
    {
        puts("Cliente desconectado");
        fflush(stdout);
    }
    else if (read_size == -1)
    {
        perror("recv failed");
    }
}

// Analiza los mensajes entrantes del cliente
void handleMessage(char* msg) {
    char buf[256];
    strcpy(buf, msg);
    int i = 0;
    char *p = strtok(buf, ",");
    char *array[3];
    int player = 1;

    // Se crea un array con cada token del string (tokenizado con ,)
    while (p != NULL)
    {
        array[i++] = p;
        p = strtok(NULL, ",");
    }

    // Se verifica cual jugador envio el mensaje, con el primer token
    if (strcmp(array[0], "Cliente1") == 0)
    {   
        player = 1;
    }
    else if (strcmp(array[0], "Cliente2") == 0)
    {
        player = 2;
    }

    // Se analiza el resto del mensaje (otros tokens)
    if (strcmp(array[1], "ComeMoneda\n") == 0)
    {
        addScore(player, 10);
    }
    else if (strcmp(array[1], "PierdeVida") == 0)
    {
        modifyLives(player, -1); 
    }
    else if (strcmp(array[1], "ComeFruta") == 0)
    {
        if (strcmp(array[2], "Naranja"))
        {
            addScore(player, 4000);
        }
        else if (strcmp(array[2], "Limon"))
        {
            addScore(player, 5000);
        }
        else if (strcmp(array[2], "Cereza"))
        {
            addScore(player, 6000);
        }
    }
    else {
        printf("basura \n");

    }
}

// Metodo para enviar un mensaje a todos los clientes conectados
void send_to_all(char* message)
{
    for (int i = 0; i < 4; ++i)
    {
        if (clients_arr[i] != 0)
            write(*clients_arr[i]->sock , message, strlen(message));
    }
}

// Se incluye el nuevo cliente a la lista de clientes
void set_client(int* sock)
{
    pthread_mutex_lock(&locker);

    struct client *ptr_one;
    ptr_one = (struct client*) malloc(sizeof(struct client));
    ptr_one->sock = sock;

    clients_arr[current_client] = ptr_one;

    pthread_mutex_unlock(&locker);
}
