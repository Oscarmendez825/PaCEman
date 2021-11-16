#include <stdio.h>
#include "/home/dani/Downloads/Servidor_PaceMan-master/server/server.h"

void* run_server(void* h)
{
    run();
}

int main()
{
    pthread_t thread_id;
    char b[100];

    init_server();
    pthread_create(&thread_id, NULL, run_server, NULL);

    while (b[0] != 'x')
    {
        printf("Escriba un comando:\n");
        fgets (b, 100, stdin);

        send_to_all(b);
    }

    return 0;
}