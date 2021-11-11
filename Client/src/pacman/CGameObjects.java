package pacman;

import java.awt.Graphics;

/**
 *
 * @author Gabriel
 */
public abstract class CGameObjects {
    int iPosX;
    int iPosY;
    
    public abstract void paintElements(Graphics g);
}