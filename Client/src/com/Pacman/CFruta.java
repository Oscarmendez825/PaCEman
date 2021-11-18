package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *Clase base de las frutas
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public class CFruta extends CGameObjects{
    
    private Color  cColor;
    
    /**
     * Constructor de la clase Fruta
     * @param cFondo: Color
     * @param X: Integer
     * @param Y: Integer
     */ 
   public CFruta(Color cFondo,int X, int Y){
        cColor = cFondo;
        super.iPosX = X;
        super.iPosY = Y;
 
    }
 
    public CFruta(){
        
    }
    
    /**
     *Establece la posicion en X de la fruta 
     * @param PosX : Integer
     */
    
    public void setX(int PosX)
    {
      super.iPosX = PosX;
    }
    /**
     * Establece la posicion en Y de la fruta
     * @param PosY : Integer
     */
    public void setY(int PosY){
      super.iPosY = PosY;
    }
    /**
     * Brinda la posicion en X de la fruta
     * @return Integer
     */
    public int getX()
    {
        return (iPosX/25);
    }
    /**
     * Brinda la posicion en Y de la fruta
     * @return Integer
     */
    public int getY()
    {
        return (iPosY/25);
    }
    
    /**
     * Se pintan las frutas a partir de figuras
     * @param g : Graphics
     */

    @Override
    public void paintElements(Graphics g) {
        g.setColor(cColor);
        
        g.fillOval(iPosX, iPosY, 15, 15);
        g.fillOval(iPosX+12, iPosY, 15, 15);
        
        
    }

     /**
     * Brinda el color de la fruta
     * @return Color
     */
    public int getcColor() {
        return cColor.getRGB();
    }
    
    
}
