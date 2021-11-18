package com.Pacman;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author Oscar Mendez
 * @author Gabriel Gonzalez
 * @author Daniela Brenes
 * Clase CTablero del cliente1
 * 
 */
public class CTablero implements InterfaceGame{
 
private Cliente client;
private Thread thread;
public  CPacman   Pacman; 
public  ArrayList <CMuro>     cuadritos;
public  ArrayList <CBomba>    bombas; 
public  ArrayList <CMoneda>   coins; 
public  ArrayList <CFantasma> fantasmitas;
public  boolean isBomba = false;
private int nMuros = 0;
private int nGhost = 0;
private int nCoins = 0;
private int nPills = 0;
private int nFruits = 0;
public ArrayList <CPastilla>  pastillas;
public ArrayList <CFruta>     frutas;
private static int puntaje = 0;
private static boolean isPower = false;
private static int  vidas = 3;
private int mTemporal[][];
 
private int iMatrizObj [][] = { {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 1, 0, 0, 1, 0, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 1, 1, 1, 1, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1},
                                 {1, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 4, 4, 4, 4, 4, 1, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 4, 4, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 4, 4, 0, 4, 4, 0, 0, 4, 1, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 1, 1, 1, 1, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 4, 1, 4, 4, 4, 4, 4, 0, 0, 0, 1, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 4, 4, 4, 4, 4, 4, 0, 1, 0, 0, 1, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 6, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 1, 1, 1, 1, 1, 0, 0, 1},
                                 {1, 5, 1, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 4, 1, 4, 4, 0, 1, 0, 0, 1},
                                 {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 4, 4, 4, 0, 0, 0, 0, 1},
                                 {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                               };
   
    
 
 public CTablero()
 {
    client = new Cliente(this);
    thread = new Thread(client);
    thread.start();
    Pacman = new CPacman();
    cuadritos = new ArrayList<>();
    bombas = new ArrayList<>();
    coins = new ArrayList<>();   
    fantasmitas = new ArrayList<>();
    pastillas = new ArrayList<>();
    frutas = new ArrayList<>();
    mTemporal = iMatrizObj;
    pintarMapa(iMatrizObj);
    
    

 }
  
    public Color getRandomColor()
    {
        Color c = Color.BLACK;
        
        Random rnd = new Random();
        
        switch (rnd.nextInt(5))
        {
            case 0:
                c = Color.ORANGE;
                break;
            case 1:
                 c = Color.BLUE;
                break;
            case 2:
                 c = Color.GREEN;
                break;
            case 3:
                 c = Color.PINK;
                break;
            case 4:
                 c = Color.RED;
                break;
        }
         return c;
    }
    
    public int getObject(int iFila, int iCol)
    {
      return  iMatrizObj [iFila][iCol];   
    }
    
    public void setObject(int obj,int iFila, int iCol)
    {
        iMatrizObj [iFila][iCol] = obj; 
    }
    
    public void moverPacman()
    {
      iMatrizObj [ Pacman.getY() ][ Pacman.getX() ] = 0;  
      Pacman.moverElemento( Pacman.getDireccion() );
      iMatrizObj [ Pacman.getY() ][ Pacman.getX() ] = 3;
      String direccionPacman = "PacmanDireccion,"+Pacman.getDireccion();
      enviarDatos(direccionPacman);
    }
    
    private void moverGhost(int iPos)
    {
      iMatrizObj [ fantasmitas.get(iPos).getY() ][fantasmitas.get(iPos).getX()] = 0;
      fantasmitas.get(iPos).moverElemento( fantasmitas.get(iPos).getDireccion() );
      iMatrizObj [ fantasmitas.get(iPos).getY() ][fantasmitas.get(iPos).getX()] = 2;
      String direccionFantasma = ("FDireccion," + fantasmitas.get(iPos).getDireccion()) + "," + iPos;
      enviarDatos(direccionFantasma);
    }
    
    
    public void moverFantasmas(int iTiempo)
    {
            for(int i = 0; i < fantasmitas.size(); i++)
            {
                switch(fantasmitas.get(i).getDireccion())
                {
                    case PAR:
                        
                           if( iMatrizObj[ fantasmitas.get(i).getY() - 1 ][ fantasmitas.get(i).getX() ] != 1 && iTiempo < 10)
                           {
                             moverGhost(i);  
                           }
                           else
                           {
                              fantasmitas.get(i).setDireccion( this.getRandomDirection() );
                           }
                               
                        break;
                        
                    case PAB:
                           
                          if(iMatrizObj[ fantasmitas.get(i).getY() + 1][fantasmitas.get(i).getX()] != 1 && iTiempo < 10)
                          {
                             moverGhost(i);   
                          }
                          else
                          {
                              fantasmitas.get(i).setDireccion( this.getRandomDirection() );
                          }
                        
                        break;
                    case IZQ:
                         
                          if(iMatrizObj[ fantasmitas.get(i).getY()][fantasmitas.get(i).getX() - 1] != 1 && iTiempo < 10)
                          {
                             moverGhost(i);   
                          }
                          else
                          {
                              fantasmitas.get(i).setDireccion( this.getRandomDirection() );
                          }
                        break;
                        
                    case DER:
                        
                        if(iMatrizObj[ fantasmitas.get(i).getY() ][fantasmitas.get(i).getX() + 1] != 1 && iTiempo < 10)
                          {
                             moverGhost(i);   
                          }
                          else
                          {
                              fantasmitas.get(i).setDireccion( this.getRandomDirection() );
                          }
                        
                        break;
                }
                
                
                    for(int m=0; m < bombas.size(); m++)
                    {
                             if( fantasmitas.get(i).getX() == bombas.get(m).getX() && fantasmitas.get(i).getY() == bombas.get(m).getY())
                            {

                                iMatrizObj [fantasmitas.get(i).getY()][ fantasmitas.get(i).getX()] = 0;

                                fantasmitas.remove(i);
                                bombas.remove(m); 

                            }
                    }  
            }    
    }
    
    public boolean isPlaying()
     {
          boolean bFinish = false;

            for(int i=0;i < fantasmitas.size() ;i++)
            {
                  if( fantasmitas.get(i).getX() == Pacman.getX() &&  fantasmitas.get(i).getY() == Pacman.getY() && vidas == 1)
                  {
                     bFinish = true;
                  }

                  if(fantasmitas.get(i).getX() == Pacman.getX() &&  fantasmitas.get(i).getY() == Pacman.getY() && vidas != 1){

                      if(isPower == true){
                          fantasmitas.remove(i);
                      }
                      else{

                          vidas --;

                      }

                  }


            }

          return bFinish;
     }
     
    public void setRandomDirectionGhosts()
    {
      for(int i = 0;i <  fantasmitas.size() ; i++)
      {  
        fantasmitas.get(i).setDireccion( this.getRandomDirection() );
      }
    }
           
    public boolean esGanador()
    {  
//        si se acaban las monedas ganas
        checkCoins();
        checkPastillas();
        checkFrutas();
        
        return coins.isEmpty();
    } 

    
    public void checkCoins()
    {
       for(int i=0; i < coins.size() ;i++)
       {
            if( Pacman.getX() == coins.get(i).getX() && Pacman.getY() == coins.get(i).getY())
            {
                coins.remove(i);
                enviarDatos("ComeMoneda");                 
            }
       } 
    }
    public void checkPastillas()
    {
       for(int i=0; i < pastillas.size() ;i++)
       {
            if( Pacman.getX() == pastillas.get(i).getX() && Pacman.getY() == pastillas.get(i).getY())
            {
                pastillas.remove(i); 
                isPower = true;
            }
       } 
    }
    
   public void checkFrutas()
    {
       for(int i=0; i < frutas.size() ;i++)
       {
            if( Pacman.getX() == frutas.get(i).getX() && Pacman.getY() == frutas.get(i).getY())
            {
                
                int fruta = frutas.get(i).getcColor();
                System.out.println(fruta);
                switch (fruta) {
                    case -65536:
                        enviarDatos("ComeFruta,Cereza");
                        break;
                    case -16711936:
                        enviarDatos("ComeFruta,Limon");
                        break;
                    default:
                        enviarDatos("ComeFruta,Naranja");
                        break;
                }
                frutas.remove(i); 
            }
       } 
    }
    
    
    public int getRandomDirection()
    {
         Random rnd = new Random();
         return (rnd.nextInt(4)+1);
    }

    @Override
    public void moverElemento(int iEstado) {
    }

    public int[][] getiMatrizObj() {
        return iMatrizObj;
    }

    public void setiMatrizObj(int[][] iMatrizObj) {
        this.iMatrizObj = iMatrizObj;
    }
    /**
     * Brinda el numero total de muros
     * @return Integer 
     */
    public int getnMuros() {
        return nMuros;
    }
    /**
     * Establece el numero total de muros
     * @param nMuros:Integer
     */
    public void setnMuros(int nMuros) {
        this.nMuros = nMuros;
    }
    /**
     * Brinda el numero total de muros
     * @return Integer 
     */
    public int getnGhost() {
        return nGhost;
    }
    /**
     * Establece el numero total de fantasmas
     * @param nGhost:Integer
     */
    public void setnGhost(int nGhost) {
        this.nGhost = nGhost;
    }
    /**
     * Brinda el numero total de pastillas
     * @return Integer 
     */
    public int getnCoins() {
        return nCoins;
    }
    /**
     * Establece el numero total de monedas
     * @param nCoins:Integer
     */
    public void setnCoins(int nCoins) {
        this.nCoins = nCoins;
    }

    public ArrayList<CPastilla> getPastillas() {
        return pastillas;
    }

    public void setPastillas(ArrayList<CPastilla> pastillas) {
        this.pastillas = pastillas;
    }

    public ArrayList<CFruta> getFrutas() {
        return frutas;
    }

    public void setFrutas(ArrayList<CFruta> frutas) {
        this.frutas = frutas;
    }
    /**
     * Brinda el numero total de pastillas
     * @return Integer 
     */
    public int getnPills() {
        return nPills;
    }
    /**
     * Establece el numero total de pastilas
     * @param nPills:Integer
     */
    public void setnPills(int nPills) {
        this.nPills = nPills;
    }
    /**
     * Brinda el numero total de frutas
     * @return Integer 
     */
    public int getnFruits() {
        return nFruits;
    }
    /**
     * Establece el numero total de frutas
     * @param nFruits:Integer
     */
    public void setnFruits(int nFruits) {
        this.nFruits = nFruits;
    }
    /**
     * Establece el numero total de puntos
     * @param puntos:Integer
     */
    public void setPuntaje(int puntos){
       puntaje += puntos; 
   
   }
    /**
     * Brinda el numero total de puntos
     * @return Integer
     */
    public int getPuntaje(){
        return puntaje;
    }
    /**
     * Brinda el numero total de vidas
     * @return Integer
     */
    public static int getVidas() {
        return vidas;
    }
    /**
     * Establece el numero total de vidas
     * @param vidas:Integer
     */
    public static void setVidas(int vidas) {
        CTablero.vidas = vidas;
    }
    /**
     * Encargado de enviar los datos a la clase cliente para enviarlos luego al server
     * @param data:String
     */
    private void enviarDatos(String data){
        String mensaje ="";
        mensaje = data.trim();
        client.mandarMensaje(mensaje);
    }

    private void pintarMapa(int[][] iMatrizObj) {
        for(int i=0; i < 23; i++)
    {
        for(int j=0; j < 23; j++)
        {
           switch(iMatrizObj[i][j])
           {
               case 1:
//                   Es muro
                   cuadritos.add(nMuros,new CMuro(j*25,i*25) );
                   nMuros++;
                   break;
               case 2:
//                   Es un ghost
                    fantasmitas.add(nGhost,new CFantasma( this.getRandomColor(), j*25, i*25));
                    nGhost++;
                   break;
               case 3:
//                   Es pacman
                     Pacman.setX(j*25);
                     Pacman.setY(i*25);
                     Pacman.setDireccion( IZQ );
                   break;
               case 4:
//                   Es moneda
                     coins.add(nCoins,new CMoneda(j*25,i*25));
                     nCoins++;
                   break;
                   
               case 5:
//                  Es pastilla
                   pastillas.add(nPills, new CPastilla(j*25,i*25));
                   nPills++;
                   break;
                   
                case 6:
//                  Es Fruta
                   frutas.add(nFruits, new CFruta(Color.GREEN,j*25,i*25));
                   nFruits++;
                   break;
           }
        }
    }
    }
    public void repintar(){
       iMatrizObj = mTemporal;
       pintarMapa(iMatrizObj);
    }
}
