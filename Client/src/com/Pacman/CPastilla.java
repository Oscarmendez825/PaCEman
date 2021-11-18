package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *Clase base de la pastilla
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public class CPastilla extends CGameObjects{
    
    /**
     * Constructor de la clase pastilla
     * @param X: Integer
     * @param Y: Integer
     */ 
    public CPastilla(int X, int Y){
        
        super.iPosX = X;
        super.iPosY = Y;
    }
    
    public CPastilla(){
        
    }
    /**
     * Establece la posicion en X de la pastilla
     * @param PosX : Integer
     */
    public void setX(int PosX)
    {
      super.iPosX = PosX;
    }
    /**
     * Establece la posicion en Y de la pastilla
     * @param PosY : Integer
     */
    public void setY(int PosY){
      super.iPosY = PosY;
    }
    /**
     * Brinda la posicion en X de la pastilla
     * @return Integer
     */
    public int getX()
    {
        return (iPosX/25);
    }
    /**
     * Brinda la posicion en Y de la pastilla
     * @return Integer
     */
    public int getY()
    {
        return (iPosY/25);
    }
    /**
     * Pinta la pastilla a partir de formas
     * @param g : Graphics
     */
    @Override
    public void paintElements(Graphics g) {
        g.setColor(Color.yellow);
        g.fillOval(iPosX, iPosY, 16, 16);
        
        
    }  
}
