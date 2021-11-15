#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <netdb.h>
#include <unistd.h>
#include <arpa/inet.h>
#include <string.h>
#include <stdbool.h>
#include <pthread.h>

int PORT = 8000;
int BACKLOG = 6;

void * handle_connection(void* pointer_client);
void* send_message(void* pointer_client);


int main() {

    // Se crea el servidor
    int server_socket;
    server_socket = socket(AF_INET, SOCK_STREAM, 0);

    if (server_socket < 0) {
        perror("Client Error: Socket not created succesfully");
        return 0;
    }

    // Se define la direccion del servidor
    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(PORT);
    server_address.sin_addr.s_addr = htons(INADDR_ANY);

    // Se hace un bind del server al port y la IP
    bind(server_socket, (struct sockaddr*) &server_address, sizeof(server_address));

    // Esperar (listen) nuevas conexiones
    listen(server_socket, BACKLOG);

    while (true)
    {
        // Socket cliente
        int client_socket;
        client_socket = accept(server_socket, (struct sockaddr*) NULL, NULL);
        printf("Nuevo cliente\n");

        // Thread para manejar el socket cliente
        pthread_t t;
        int *pointer_client = malloc(sizeof(int));  // Para que no afecte a los demas threads
        *pointer_client = client_socket;

        pthread_create(&t, NULL, handle_connection, pointer_client); // Thread para leer mensajes
        pthread_create(&t, NULL, send_message, pointer_client); // Thread para leer mensajes

    }
    
    close(server_socket);
    return 0;
}


// Funcion para manejar los reads de los sockets clientes
void* handle_connection(void* pointer_client) {
    int client_socket = *((int*) pointer_client);
    free(pointer_client);   //Ya no se necesita el puntero

    char input[256];

    while (true) {
        bzero(input, 40);

        read(client_socket, input, 40);

        if (input != NULL) {
            //printf("%s\n", input);  // Mostrar mensaje del cliente
            //send(client_socket, input, sizeof(input), 0);
        }
    }
    
    return NULL;
}

// Funcion para enviar mensajes desde el servidor
void* send_message(void* pointer_client) {
    int client_socket = *((int*) pointer_client);
    free(pointer_client);   //Ya no se necesita el puntero

    char console_in[256];

    while (true) {
        bzero(console_in, 40);

        fgets(console_in, sizeof(console_in), stdin);

        // Enviar mensaje
        if (console_in != "") {
            send(client_socket, console_in, sizeof(console_in), 0);
        }
    }
    return NULL;
}