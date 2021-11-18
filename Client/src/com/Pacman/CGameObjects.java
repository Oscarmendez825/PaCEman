package com.Pacman;

import java.awt.Graphics;

/**
 * Clase padre de los objetos del juego
 * Les da posiciones y forma
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public abstract class CGameObjects {
    int iPosX;
    int iPosY;
    
    /**
     * Metodo para formar los objetos
     * @param g : Graphics
     */
    public abstract void paintElements(Graphics g);
}