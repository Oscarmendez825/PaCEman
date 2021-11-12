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

int main()
{

    // Se crea el servidor
    int server_socket;
    server_socket = socket(AF_INET, SOCK_STREAM, 0);

    // Se define la direccion del servidor
    struct sockaddr_in server_address;

    bzero(&server_address, sizeof(server_address));

    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(1201);
    // server_address.sin_addr.s_addr = htons(INADDR_ANY);

    inet_pton(AF_INET, "127.0.0.1", &(server_address.sin_addr));

    // Conectarse al servidor
    connect(server_socket, (struct sockaddr *)&server_address, sizeof(server_address));

    // Mensaje
    char message[256];
    char send_line[256];

    while (!strstr(message, "endgame"))
    {
        bzero(message, 256);

        fgets(send_line, sizeof(send_line), stdin);
        write(server_socket, send_line, sizeof(send_line));

        printf("%s", message); // Mostrar mensaje del cliente
    }

    // Enviar mensaje
    // send(client_socket, server_message, sizeof(server_message), 0);

    close(server_socket);
    return 0;
}
