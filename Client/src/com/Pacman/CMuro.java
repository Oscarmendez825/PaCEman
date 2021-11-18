package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public class CMuro extends CGameObjects{

    /**
     * Constructor de la clase muro
     * @param iX:Integer
     * @param iY: Integer
     */
   public CMuro(int iX, int iY)
    {
        super.iPosX = iX;
        super.iPosY = iY;
    }
   
   /**
    * Se pinta el muro a partir de figuras
    * @param g : Graphics
    */
    @Override
    public void paintElements(Graphics g) {
        g.setColor(Color.blue);
        g.fillRoundRect(iPosX, iPosY, 25, 25, 6, 6);
        g.setColor(Color.black);
        g.fillRoundRect(iPosX+5, iPosY+5, 15, 15, 6, 6);
        
    }
    
}
