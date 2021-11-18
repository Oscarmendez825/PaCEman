#ifndef PLAYERS_H
#define PLAYERS_H

// Se define la estructura jugador
struct Player {
    int score;
    int lives;
};

// Definicion de funciones
int modifyLives(int player, int modify);

int addScore(int player, int add);


#endif
