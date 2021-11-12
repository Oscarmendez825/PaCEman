package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gabriel
 */
public class CMoneda extends CGameObjects{

    public CMoneda(int X, int Y)
    {
        super.iPosX = X;
        super.iPosY = Y;
    }
    
    public CMoneda()
    {
  
    }
    
    public void setX(int PosX)
    {
      super.iPosX = PosX;
    }
    
    public void setY(int PosY){
      super.iPosY = PosY;
    }
    
    public int getX()
    {
        return (iPosX/25);
    }
    
    public int getY()
    {
        return (iPosY/25);
    }
    
    @Override
    public void paintElements(Graphics g) {
       
//      Un circulo color naranja dentro de uno amarillo
        
        g.setColor(Color.white);
        g.fillOval(iPosX+3, iPosY+3, 6, 6);
    }
    
}