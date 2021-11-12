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
    server_address.sin_port = htons(1201);
    server_address.sin_addr.s_addr = htons(INADDR_ANY);

    // Se hace un bind del server al port y la IP
    bind(server_socket, (struct sockaddr*) &server_address, sizeof(server_address));

    // Esperar (listen) nuevas conexiones
    listen(server_socket, 5);

    // Socket cliente
    int client_socket;
    client_socket = accept(server_socket, (struct sockaddr*) NULL, NULL);

    // Mensaje cliente
    char input[256];
    char output[256];

    while (1)
    {
        bzero(input, 256);
        bzero(output, 256);

        read(client_socket, input, 256);
        printf("%s", input); // Mostrar mensaje del cliente

        fgets(output, sizeof(output), stdin);
        send(client_socket, output, sizeof(output), 0);

    }
    
    close(server_socket);
    return 0;
}