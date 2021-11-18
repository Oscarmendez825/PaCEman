#ifndef PLAYERS_H
#define PLAYERS_H

// Se define la estructura jugador
struct Player {
    int score;
    int lives;
};

// Definicion de funciones
void modifyLives(int player, int modify);

void addScore(int player, int add);


#endif
