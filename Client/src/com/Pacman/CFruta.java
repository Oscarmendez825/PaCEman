package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gabriel
 */
public class CFruta extends CGameObjects{
    
    private Color  cColor;
    
   public CFruta(Color cFondo,int X, int Y){
        cColor = cFondo;
        super.iPosX = X;
        super.iPosY = Y;
 
    }
 
    public CFruta(){
        
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
        g.setColor(cColor);
        
        g.fillOval(iPosX, iPosY, 15, 15);
        g.fillOval(iPosX+12, iPosY, 15, 15);
        
        
    }

    public int getcColor() {
        return cColor.getRGB();
    }
    
    
}
