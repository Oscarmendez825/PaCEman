#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
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
    server_address.sin_addr.s_addr = INADDR_ANY;

    // Se hace un bind del server al port y la IP
    bind(server_socket, (struct sockaddr*) &server_address, sizeof(server_address));

    int client_socket;
    
    // Esperar (listen) nuevas conexiones
    listen(server_socket, 5);

    while (client_socket = accept(server_socket, (struct sockaddr*) NULL, NULL))
    {

        char client_message[256];
        int length_msg = recv(server_socket, &client_message, sizeof(client_message), 0);

        //if (length_msg > 0) {
           // printf("Cliente: %s \n", client_message);
        //}


        int childpid, n;
		if ( (childpid = fork ()) == 0 ) 
		{
				
			//Cerrar socket
			close (server_socket);
        }
    }   
    

    // Enviar mensaje inicial
    //send(client_socket, server_message, sizeof(server_message), 0);
    
    return 0;
}
