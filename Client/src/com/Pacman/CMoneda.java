package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase base de las monedas
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public class CMoneda extends CGameObjects{

    /**
     * Constructor de la clase Moneda
     * @param X: Integer
     * @param Y : Integer
     */
    public CMoneda(int X, int Y)
    {
        super.iPosX = X;
        super.iPosY = Y;
    }
    
    public CMoneda()
    {
  
    }
    
    /**
     * Establece la posicion en X de la moneda
     * @param PosX : Integer
     */
    
    public void setX(int PosX)
    {
      super.iPosX = PosX;
    }
    
    /**
     * Establece la posicion en Y de la moneda
     * @param PosY : Integer
     */
    public void setY(int PosY){
      super.iPosY = PosY;
    }
    
    /**
     * Brinda la posicion en X de la moneda
     * @return Integer
     */
    public int getX()
    {
        return (iPosX/25);
    }
    
    /**
     * Brinda la posicion en Y de la moneda
     * @return Integer
     */
    
    public int getY()
    {
        return (iPosY/25);
    }
    
    
    /**
     * Se pinta la moneda a partir de figuras
     * @param g 
     */
    @Override
    public void paintElements(Graphics g) {
          
        g.setColor(Color.white);
        g.fillOval(iPosX+3, iPosY+3, 6, 6);
    }
    
}