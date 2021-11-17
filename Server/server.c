#include "server.h"
#include "/home/dani/Documents/GitHub/PaCEman/Server/variables.h"


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
        if( pthread_create(&thread_id, NULL,  connection_handler, (void*) &client_sock) < 0)
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

