#include <stdio.h>
#include "server.h"
#include "players.h"

void* run_server(void* h);

int main()
{
    pthread_t thread_id;
    char b[100];

    // Se inicializa el server
    init_server();

    // Se crea un thread para manejar al servidor por aparte
    pthread_create(&thread_id, NULL, run_server, NULL);

    while (b[0] != 'x')
    {
        // Recibe el input de la consola
        printf("Escriba un comando:\n");
        fgets (b, 100, stdin);

        // Envia el input al cliente
        send_to_all(b);
    }

    return 0;
}

// Metodo para correr el servidor en un thread
void* run_server(void* h)
{
    run();
}
