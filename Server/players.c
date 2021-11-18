#include "players.h"

// Creacion de jugadores
struct Player p1 = {0, 3}; 
struct Player p2 = {0, 3}; 

void addScore(int player, int add) {
    if (player == 1) {
        if (p1.score < 10000 && p1.score + add >= 10000) {
            modifyLives(1, 1);
        }

        p1.score += add;
    }
    else {
        if (p2.score < 10000 && p2.score + add >= 10000) {
            modifyLives(2, 1);
        }
        p2.score += add;
    }  
}

void modifyLives(int player, int modify) {
    if (player == 1)
    {
        p1.lives += modify;
    }
    else
    {
        p2.lives += modify;
    }
}

