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
public  java.lang.Boolean isBomba = false;
private java.lang.Integer nMuros = 0;
private java.lang.Integer nGhost = 0;
private java.lang.Integer nCoins = 0;
private java.lang.Integer nPills = 0;
private java.lang.Integer nFruits = 0;
public ArrayList <CPastilla>  pastillas;
public ArrayList <CFruta>     frutas;
private static java.lang.Integer puntaje = 0;
private static java.lang.Boolean isPower = false;
private static java.lang.Integer  vidas = 3;
private int mTemporal[][];
 
//Creacion del tablero del juego
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
   
    
 /**
  * Constructor de la clase tablero
  * Se definene los arrays de los elementos del juego
  */
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
 /**
  * Brinda un color para los fantasmas
  * @return Color
  */
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
    
    /**
     * Brinda el valor de la posicion de la matriz
     * @param iFila: Integer
     * @param iCol: Integer
     * @return Integer
     */
    public int getObject(int iFila, int iCol)
    {
      return  iMatrizObj [iFila][iCol];   
    }
   /**
    * Establece el objeto en la matriz
    * @param obj: Integer
    * @param iFila: Integer
    * @param iCol : Integer
    */ 
    public void setObject(int obj,int iFila, int iCol)
    {
        iMatrizObj [iFila][iCol] = obj; 
    }
    /**
     * Metodo para mover al pacman en la matriz
     * Envia los datos de la direccion al servidor
     */
    public void moverPacman()
    {
      iMatrizObj [ Pacman.getY() ][ Pacman.getX() ] = 0;  
      Pacman.moverElemento( Pacman.getDireccion() );
      iMatrizObj [ Pacman.getY() ][ Pacman.getX() ] = 3;
      String direccionPacman = "PacmanDireccion,"+Pacman.getDireccion();
      enviarDatos(direccionPacman);
    }
    /**
     * Metodo para mover a los fantasmas en la matriz
     * @param iPos : Integer
     * Envia los datos de la direccion del fantasma al servidor
     */
    private void moverGhost(int iPos)
    {
      iMatrizObj [ fantasmitas.get(iPos).getY() ][fantasmitas.get(iPos).getX()] = 0;
      fantasmitas.get(iPos).moverElemento( fantasmitas.get(iPos).getDireccion() );
      iMatrizObj [ fantasmitas.get(iPos).getY() ][fantasmitas.get(iPos).getX()] = 2;
      String direccionFantasma = ("FDireccion," + fantasmitas.get(iPos).getDireccion()) + "," + iPos;
      enviarDatos(direccionFantasma);
    }
    
    /**
     *Metodo para mover fantasmas las posiciones necesarias y cambiar la posicion 
     * @param iTiempo : Integer
     */
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
    /**
     * Valida si el jugador no ha muerto
     * @return Boolean
     */
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

                          enviarDatos("PierdeVida");

                      }

                  }


            }

          return bFinish;
     }
    /**
     * Establece una direccion random para los fantasmas
     */
    public void setRandomDirectionGhosts()
    {
      for(int i = 0;i <  fantasmitas.size() ; i++)
      {  
        fantasmitas.get(i).setDireccion( this.getRandomDirection() );
      }
    }
    /**
     * Valida si el jugador es ganador
     * @return Boolean
     */     
    public boolean esGanador()
    {  
//        si se acaban las monedas ganas
        checkCoins();
        checkPastillas();
        checkFrutas();
        
        return coins.isEmpty();
    } 

    /**
     * Metodo para comer monedas
     */
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
     /**
     * Metodo para comer pastillas
     */
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
    /**
     * Metodo para comer frutas
     */
    
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
    
    /**
     * Brinda una direccion random
     * @return Integer
     */
    public int getRandomDirection()
    {
         Random rnd = new Random();
         return (rnd.nextInt(4)+1);
    }
/**
 * Metodo para mover elemento
 * @param iEstado : Integer
 */    
    @Override
    public void moverElemento(int iEstado) {
    }
    /**
     * Brinda la posicion de matriz
     * @return Integer
     */
    public int[][] getiMatrizObj() {
        return iMatrizObj;
    }
    /**
     * Establece el dato en la matriz por posicion
     * @param iMatrizObj : Integer
     */
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

    /**
     * Brinda el array de pastillas
     * @return ArrayList
     */
    public ArrayList<CPastilla> getPastillas() {
        return pastillas;
    }

    /**
     * Establece las pastillas
     * @param pastillas : ArrayList
     */
    public void setPastillas(ArrayList<CPastilla> pastillas) {
        this.pastillas = pastillas;
    }
    /**
     * Brinda el array de frutas
     * @return ArrayList
     */
    public ArrayList<CFruta> getFrutas() {
        return frutas;
    }
    /**
     * Establece las frutas
     * @param frutas ArrayList
     */
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
/**
 * Se pinta la matriz secundaria
 * @param iMatrizObj 
 */
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
    /**
     * Se repintan los objetos de la matriz 
     */
    public void repintar(){
       iMatrizObj = mTemporal;
       pintarMapa(iMatrizObj);
    }
}
