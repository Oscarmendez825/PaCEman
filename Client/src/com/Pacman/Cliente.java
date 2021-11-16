package com.Pacman;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.in;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class Cliente implements Runnable {

        private Socket port;
        private int genport = 8080;
        private DataOutputStream dataout;
        private String message = "";
        private CTablero tablero;
        
        private InputStreamReader inputStreamReader;
        private BufferedReader bufferedReader;

        
        public Cliente(CTablero juego){
            this.tablero = juego;

            try {
                port = new Socket("127.0.0.1",genport);
                dataout = new DataOutputStream(port.getOutputStream());
                inputStreamReader = new InputStreamReader(port.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);

            } catch (IOException e) {

            }
        }
        public void mandarMensaje(String message){
            try {
                dataout.write(("Cliente1,"+message).getBytes());
            } catch (IOException e) {
            }
    }
        @Override
         public void run() {
            try{
                while(true){
                    message = bufferedReader.readLine();
                    String[] separacion = message.split(",");
                    System.out.println(Arrays.asList(separacion));
                    accion(separacion);
                }
            }catch(IOException e){
            }

        }
         
         private void accion(String[] cadena){
             switch(cadena[0]){
                 case "Enemigo":

                     switch(cadena[1]){
                         case "Shadow":
                             System.out.println("Crear Shadow");
                             addFantasma("rojo");
                             break;
                         case "Speedy":
                             System.out.println("Crear Speedy");
                             addFantasma("rosado");
                             break;
                         case "Bashful":
                             System.out.println("Crear Bashful");
                             addFantasma("celeste");
                             break;   
                         case "Pokey":
                             System.out.println("Crear Pokey");
                             addFantasma("naranja");
                             break;
                     }
                     break;
                 case "Fruta":
                     System.out.println("Crear fruta");
                     addFruta(cadena);
                     break;
                 case "Pastilla":
                     System.out.println("Crear pastilla");
                     addPastilla();
                     break;
                case "Puntuacion":
                     System.out.println("Setear Puntuacion");
                     int puntaje = Integer.parseInt(cadena[1]);
                     tablero.setPuntaje(puntaje);
                     break;
                case "Vida":
                     System.out.println("Setear Vidas");
                     int vidas = Integer.parseInt(cadena[1]);
                     CTablero.setVidas(vidas);
                     break;
                default:
                    break;
             }
         }
        
    private void addFantasma(String color){
        int i = getRandom(22);
        int j = getRandom(22);
        while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
            i = getRandom(22);
            j = getRandom(22);
        }
        String datos = "";
        switch(color){    
            case "rojo":
                datos = "Fantasma;rojo;"+i+";"+j;
                mandarMensaje(datos);
                CFantasma fantasma = new CFantasma(Color.RED, i*25, j*25);
                fantasma.setDireccion(1);
                tablero.fantasmitas.add(fantasma);
                tablero.getiMatrizObj()[i][j] = 2;
                break;

            case "rosado":
                datos = "Fantasma;rosado;"+i+";"+j;
                mandarMensaje(datos);           
                CFantasma fantasma2 = new CFantasma(Color.PINK, i*25, j*25);
                fantasma2.setDireccion(1);
                tablero.fantasmitas.add(fantasma2);
                tablero.getiMatrizObj()[i][j] = 2;

                break;

            case "celeste":
                datos = "Fantasma;celeste;"+i+";"+j;
                mandarMensaje(datos);           
                CFantasma fantasma3 = new CFantasma(Color.cyan,i*25, j*25);
                fantasma3.setDireccion(1);
                tablero.fantasmitas.add(fantasma3);
                tablero.getiMatrizObj()[i][j] = 2;

                break;

            case "naranja":
                datos = "Fantasma;naranja;"+i+";"+j;
                mandarMensaje(datos);
                CFantasma fantasma4 = new CFantasma(Color.ORANGE, i*25, j*25);
                fantasma4.setDireccion(1);
                tablero.fantasmitas.add(fantasma4);
                tablero.getiMatrizObj()[i][j] = 2;
                break;

        }
    }

    
    private void addFruta(String[] cadena){
        int i = getRandom(22);
        int j = getRandom(22);
        while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
            i = getRandom(22);
            j = getRandom(22);
        }
        String datos = "";
        switch(cadena[1]){
            case "Cereza":
               tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.RED,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                datos = "FObservador;Cereza;"+i+";"+j;
                mandarMensaje(datos);
                break;

            case "Limon":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.GREEN,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                datos = "FObservador;Limon;"+i+";"+j;
                mandarMensaje(datos);
                break;
            case "Naranja":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.ORANGE,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                datos = "FObservador;Naranja;"+i+";"+j;
                mandarMensaje(datos);
                break;   
         }
         
    }
    private void addPastilla(){
        int i = getRandom(22);
        int j = getRandom(22);
        while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
            i = getRandom(22);
            j = getRandom(22);
        }
        tablero.pastillas.add(tablero.getnPills(), new CPastilla(j*25,i*25));
        tablero.getiMatrizObj()[i][j] = 6;
        String datos = "";
        datos = "GenPastilla;"+i+";"+j;
        mandarMensaje(datos);
    
    }

    private int getRandom(int num){
        Random random = new Random();
        int numX = random.nextInt(num);
        return numX;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Ventana w1 = new Ventana();
        w1.PintarElementos();
        w1.setVisible(true);
    }
}
