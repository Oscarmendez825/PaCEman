package com.Pacman;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public class CPacman extends CGameObjects implements InterfaceGame {
    
    private int     iDireccion;
    private boolean isOpen=true;
    
    /**
     * Constructor de la clase Pacman
     * @param iX: Integer
     * @param iY: Integer
     * @param iDir : Integer
     */
    
    public CPacman(int iX,int iY,int iDir)
    {
        super.iPosX=iX;
        super.iPosY=iY;
        iDireccion=iDir;
    }
    
    public CPacman()
    {
        super.iPosX=0;
        super.iPosY=0;
    }
    
    /**
     * Establece la posicion en X de Pacman
     * @param PosX : Integer
     */
    public void setX(int PosX)
    {
      super.iPosX = PosX;
    }
    /**
     * Establece la posicion en Y de Pacman
     * @param PosY 
     */
    public void setY(int PosY){
      super.iPosY = PosY;
    }
    /**
     * Brinda la posicion en X de Pacman
     * @return  Integer
     */
    public int getX()
    {
        return (iPosX/25);
    }
    /**
     * Brinda la posicion en Y de Pacman
     * @return Integer
     */
    public int getY()
    {
        return (iPosY/25);
    }
    /**
     * Establece la direccion de Pacman
     * @param iDir : Integer
     */
    public void setDireccion(int iDir)
    {
        iDireccion=iDir;
    }
    /**
     * Brinda la direccion de Pacman
     * @return Integer
     */
    public int getDireccion()
    {
        return iDireccion;
    }
    /**
     * Se pinta a Pacman a partir de figuras
     * @param g 
     */
    @Override
    public void paintElements(Graphics g) 
    {
        
     switch(iDireccion)
     {
         case DER: 
             if(isOpen)//Se valida si tiene la boca abierta
             {
                g.setColor(Color.ORANGE); g.fillArc(iPosX, iPosY, 25, 25, 45, 275); isOpen=false;
             }
             else
             {
                g.setColor(Color.ORANGE); g.fillArc(iPosX, iPosY, 25, 25, 0, 360); isOpen=true;
             }
                g.setColor(Color.black); g.fillOval(iPosX+8, iPosY+4, 4, 4);
             break;
             
         case IZQ:
             if(isOpen)//Se valida si tiene la boca abierta
             {
                  g.setColor(Color.ORANGE);  g.fillArc(iPosX, iPosY, 25, 25, 225, 275); isOpen=false;
             }
             else
             {
                  g.setColor(Color.ORANGE); g.fillArc(iPosX, iPosY, 25, 25, 0, 360); isOpen=true;
             }
             g.setColor(Color.black); g.fillOval(iPosX+8, iPosY+4, 4, 4);
             break;
         case PAR:
             if(isOpen)//Se valida si tiene la boca abierta
             {
                g.setColor(Color.ORANGE);  g.fillArc(iPosX, iPosY, 25, 25, 135, 270); isOpen=false;  
             }else{
                  g.setColor(Color.ORANGE); g.fillArc(iPosX, iPosY, 25, 25, 0, 360); isOpen=true;
             }
              g.setColor(Color.black); g.fillOval(iPosX+4, iPosY+8, 4, 4);
             break;
      
         case PAB: 
             if(isOpen)//Se valida si tiene la boca abierta
             {
                g.setColor(Color.ORANGE);  g.fillArc(iPosX, iPosY, 25, 25, 315, 270); isOpen=false;  
             }else{
                  g.setColor(Color.ORANGE); g.fillArc(iPosX, iPosY, 25, 25, 0, 360); isOpen=true;
             }
              g.setColor(Color.black); g.fillOval(iPosX+4, iPosY+8, 4, 4);
             break;
         case Quiet:
             g.setColor(Color.ORANGE); g.fillArc(iPosX, iPosY, 25, 25, 0, 360); isOpen=true;
             g.setColor(Color.black); g.fillOval(iPosX+4, iPosY+8, 4, 4);
             break;
     }   
    }
/**
 * Metodo para mover a Pacman las posiciones necesarias
 * @param iEstado : Integer
 */
    @Override
    public void moverElemento(int iEstado) {

     switch (iEstado)
     {
         case PAB:
              this.iPosY +=25;
             break;
         case PAR:
              this.iPosY -=25;
              break;
         case IZQ:
             this.iPosX -=25;
              break;
         case DER:
             this.iPosX += 25;
              break;
             
     }
    
    }
    
}