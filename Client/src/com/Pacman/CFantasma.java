package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase base de fantasmas
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez

 */
public class CFantasma extends CGameObjects implements InterfaceGame{

    private Color cColor;
    private java.lang.Integer iDireccion;
    
     /**
     * Constructor de la clase Fantasma
     * @param cFondo: Color
     * @param posX : Integer
     * @param posY : Integer
     */  
    
   public CFantasma(Color cFondo, int posX, int posY)
    {
        cColor = cFondo;
        super.iPosX = posX;
        super.iPosY = posY;
    }
    
   public CFantasma()
    {
        
    }
   
     /** 
    * Establece la direccion del fantasma
    * @param iDir : Integer
    */  
    public void setDireccion(int iDir){
        iDireccion=iDir;
    }
     /**
     * Brinda la direccion del fantasma
     * @return  Integer
     */  
    public int getDireccion(){
        return iDireccion;
    }
    
     /**
     * Establece la posicion en X del fantasma
     * @param PosX:Integer 
     */   
    
     public void setX(int PosX)
    {
      super.iPosX = PosX;
    }
 
     /**
     * Establece la posicion en Y del fantasma
     * @param PosY : Integer
     */
    public void setY(int PosY){
      super.iPosY = PosY;
    }
    
    /**
     * Brinda la posicion horizontal del fantasma
     * @return : Integer
     */ 
    public int getX()
    {
        return (iPosX/25);
    }
    
    /**
     * Brinda la posición vertical del fantasma
     * @return : Integer
     */ 
    public int getY()
    {
        return (iPosY/25);
    }
    
    /**
     * Se pintan los fantasmas a partir de figuras
     * @param g : Graphics
     */
    @Override
    public void paintElements(Graphics g) {
        
//      Color del fantasma
        g.setColor(cColor);
//      Cabeza del fantasma  
        g.fillArc(iPosX, iPosY, 20, 20, 0, 180);
//      Cuerpo
        g.fillRect(iPosX+1, iPosY+10, 19, 10);
        g.setColor(Color.WHITE);
//        Ojos 
        g.fillOval(iPosX+2, iPosY+4, 7, 7);
        g.fillOval(iPosX+10, iPosY+4, 7, 7);
        
//      ojo negro   
        g.setColor(Color.black);
        g.fillOval(iPosX+4, iPosY+8, 3, 3);
        g.fillOval(iPosX+12,iPosY+8, 3, 3);
//      boca
        g.drawArc(iPosX+7, iPosY+15, 5, 5, 0, 180);
    }

     /**
     * Metodo para mover el fantasma las posiciones necesarias
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
