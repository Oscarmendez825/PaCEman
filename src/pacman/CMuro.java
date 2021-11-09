package pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gabriel
 */
public class CMuro extends CGameObjects{

    
    CMuro(int iX, int iY)
    {
        super.iPosX = iX;
        super.iPosY = iY;
    }
    
    @Override
    public void paintElements(Graphics g) {
//       Un simple rectangulo cafe redondeado 
        g.setColor(new Color(185, 122, 87));
        g.fillRoundRect(iPosX, iPosY, 25, 25, 6, 6);
        g.setColor(Color.black);
        g.drawRoundRect(iPosX, iPosY, 25, 25, 6, 6);
    }
    
}
