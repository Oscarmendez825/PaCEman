#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <unistd.h>

int main() {

    //Crea el socket 
    int network_socket;
    network_socket = socket(AF_INET, SOCK_STREAM, 0);

    //Direccion a la que conectarse
    struct sockaddr_in server_address;
    server_address.sin_family = AF_INET;
    server_address.sin_port = htons(1201);
    server_address.sin_addr.s_addr = INADDR_ANY;

    int connect_status = connect(network_socket, (struct sockaddr *) &server_address, sizeof(server_address));

    //Errores en conexion
    if (connect_status == -1) {
        printf("There was an error during the connection process \n");
    }

    //Recibir mensajes del servidor
    char server_message[256];
    recv(network_socket, &server_message, sizeof(server_message), 0);

    //Imprimir mensaje recibido
    printf("El server dice: %s \n", server_message);

    //Cerrar socket
    close(network_socket);


    return 0;
}