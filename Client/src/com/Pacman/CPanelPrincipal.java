package com.Pacman;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Clase que crea el panel principal del juego
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */

public class CPanelPrincipal extends JPanel implements Runnable,KeyListener,InterfaceGame
{
    private CTablero  tablero = new CTablero();
    public boolean    isPause = false;
    private Thread    hilo;
    static int       iCont;
    
    /**
     * Constructor de la clase PanelPrincipal
     * En este se crea el hilo necesario para el juego
     */
     public CPanelPrincipal()
     {
        hilo = new Thread(this);
        setFocusable(true);
     }
     /**
      * Inicia el hilo
      */
     public void iniciar()
     {
        iCont = 0; 
        hilo.start();
     }
     /**
      * Pausa el hilo
      */
     public void pausar()
     {
        hilo.suspend();
     }
     /**
      * Continua con el hilo
      */
     public void continuar()
     {
        hilo.resume();
     }
     /**
      * Detiene el hilo
      */
    public void detener()
    {
        hilo.stop();
    }
    /**
     * Metodo que corre los metodos del juego
     */
    @Override
    public void run() 
    {
      try
      {
          //Se definen las direcciones iniciales
          tablero.setRandomDirectionGhosts();
          tablero.Pacman.setDireccion(0);
         while( !tablero.isPlaying() && !tablero.esGanador())
         {
          Thread.sleep(250);
          tablero.moverFantasmas(iCont);
          
          if(iCont == 10)
          {
            iCont = 0;
          }
          //Se cambian las direcciones del Pacman
           switch(tablero.Pacman.getDireccion())
           {
                 case IZQ: 
                        if((tablero.getObject( tablero.Pacman.getY() , tablero.Pacman.getX()-1))!= 1){
                           tablero.moverPacman();
                        }
                     break;   
                 case DER:
                         if((tablero.getObject( tablero.Pacman.getY() , tablero.Pacman.getX()+1))!= 1){
                           tablero.moverPacman();
                         }
                     break;
                 case PAR:
                         if((tablero.getObject( tablero.Pacman.getY()-1,tablero.Pacman.getX()))!= 1){
                           tablero.moverPacman();
                         }
                     break;
                 case PAB: 
                         if((tablero.getObject( tablero.Pacman.getY()+1, tablero.Pacman.getX() ))!= 1){ 
                           tablero.moverPacman();
                         }
                     break;
             }
           
             repaint();
             iCont++;
         }
         //Mensaje de pasra de nivel
          if( tablero.esGanador() && !tablero.isPlaying())
          {
               JOptionPane.showMessageDialog(this, "Pasaste de Nivel!", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
               tablero.repintar();
          }
          //Mensaje de perdedor
          else{
               JOptionPane.showMessageDialog(this, "! Perdiste !", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
          }
    
          
      }catch(InterruptedException | HeadlessException e)
      {
            JOptionPane.showMessageDialog(this,"Ha ocurrido un Error: "+e.getMessage(),"Advertencia",JOptionPane.ERROR_MESSAGE);
      }
      
    }
    /**
     * Metodo para pintar los elementos del juego
     * @param g : Graphics
     */
    @Override
     public void paintComponent(Graphics g)
     {
//         fondo del juego
         g.setColor(Color.black);
         g.fillRect(0, 0, getWidth(), getHeight());
         
//         Se pintan los elementos
        for(int i = 0; i < tablero.fantasmitas.size(); i++) 
        {
            tablero.fantasmitas.get(i).paintElements(g);
        }
         
        for(CMuro cuadrito : tablero.cuadritos) 
        {
            cuadrito.paintElements(g);
        }
         
        for(int i=0; i < tablero.coins.size();i++) {
           tablero.coins.get(i).paintElements(g);
        }
        
        for(int i=0; i < tablero.pastillas.size();i++) {
           tablero.pastillas.get(i).paintElements(g);
        }
        
         for(int i=0; i < tablero.frutas.size();i++) {
           tablero.frutas.get(i).paintElements(g);
        }
        
        for(int s=0; s<tablero.bombas.size();s++)
        {
                
            tablero.bombas.get(s).paintElements(g);   
        }
           
           
    
          tablero.Pacman.paintElements(g);
     }

    @Override
    public void keyTyped(KeyEvent arg0) 
    {
        
    }
/**
 * Eventos de teclas presionadas
 * @param arg0 : KeyEvent
 */
    @Override
    public void keyPressed(KeyEvent arg0) 
    {
        int key = arg0.getKeyCode();
        
        switch(key)
        {
            case KeyEvent.VK_LEFT:  tablero.Pacman.setDireccion(IZQ); break;
            case KeyEvent.VK_RIGHT: tablero.Pacman.setDireccion(DER); break;  
            case KeyEvent.VK_UP:    tablero.Pacman.setDireccion(PAR);  break;
            case KeyEvent.VK_DOWN:  tablero.Pacman.setDireccion(PAB);  break;
            case KeyEvent.VK_SPACE: 
                       
                     if(isPause == false)
                       { 
                           pausar(); 
                           isPause = true; 
                       }
                       else
                       { 
                           continuar(); 
                           isPause = false;
                       } 
                       break;
            case KeyEvent.VK_ESCAPE: 
                             detener(); 
                             System.exit(1); 
                             break;  
            case KeyEvent.VK_X: 
                // Aqui le cambie
           
                   if( tablero.bombas.size()<3)
                   {
                     
                           tablero.setObject(5, tablero.Pacman.getY(), tablero.Pacman.getX());
                           tablero.bombas.add(new CBomba(tablero.Pacman.getX()*25 , tablero.Pacman.getY()*25));
                          
                   }
             
          
             break;    
        }
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }

    @Override
    public void moverElemento(int iEstado) {
    }
    
}