#include "players.h"
#include <stdio.h>

// Creacion de jugadores
struct Player p1 = {0, 3}; 
struct Player p2 = {0, 3}; 

int addScore(int player, int add) {
    if (player == 1) {
        if (p1.score < 10000 && (p1.score + add) >= 10000) {
            modifyLives(1, 1);
        }
        p1.score += add;

        return p1.score;
    }
    else {
        if (p2.score < 10000 && p2.score + add >= 10000) {
            modifyLives(2, 1);
        }
        p2.score += add;
        return p2.score;
    }  
}

int modifyLives(int player, int modify) {
    if (player == 1)
    {
        p1.lives += modify;
        return p1.lives;
    }
    else
    {
        p2.lives += modify;
        return p2.lives;
    }
}

int getLives(int player) {
    int lives;

    if (player == 1)
    {
        lives = p1.lives;
    }
    else
    {
        lives = p2.lives;
    }
    
    return lives;
}
