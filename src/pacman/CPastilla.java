package pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gabriel
 */
public class CPastilla extends CGameObjects{
    
    CPastilla(int X, int Y){
        
        super.iPosX = X;
        super.iPosY = Y;
    }
    
    CPastilla(){
        
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
        g.setColor(Color.yellow);
        g.fillOval(iPosX, iPosY, 16, 16);
        
        
    }  
}
