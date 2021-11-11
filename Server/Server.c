#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

int main() {

    char server_message[256] = "Welcome to PaCEman";

    // Se crea el servidor
    int server_socket;
    server_socket = socket(AF_INET, SOCK_STREAM, 0);

    // Se define la direccion del servidor
    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(9002);
    server_address.sin_addr.s_addr = INADDR_ANY;

    // Se hace un bind del server al port y la IP
    bind(server_socket, (struct sockaddr*) &server_address, sizeof(server_address));
        
    // Esperar (listen) nuevas conexiones
    listen(server_socket, 4);

    // Aceptar conexiones
    int client_socket;
    client_socket = accept(server_socket, NULL, NULL);

    // Enviar mensaje inicial
    send(client_socket, server_message, sizeof(server_message), 0);

    // Cerrar socket servidor
    close(server_socket);

    return 0;
}
