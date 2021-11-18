#ifndef SERVER_VARIABLES_H
#define SERVER_VARIABLES_H

#include <sys/types.h>

extern int PORT;

extern int current_client;
extern int socket_desc, client_sock, c;
extern struct sockaddr_in server , client;
extern pthread_mutex_t locker;
extern struct client* clients_arr[4];

#endif //SERVER_VARIABLES_H
