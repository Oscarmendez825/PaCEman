package com.Pacman;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

public class Cliente implements Runnable {

        private Socket port;
        private int genport = 1201;
        private DataInputStream datain;
        private DataOutputStream dataout;
        private String message = "";
        private CTablero tablero;
        

        
        public Cliente(CTablero juego){
            this.tablero = juego;

            try {
                port = new Socket("127.0.0.1",genport);
                datain = new DataInputStream(port.getInputStream());
                dataout = new DataOutputStream(port.getOutputStream());
            } catch (IOException e) {

            }
        }
        public void mandarMensaje(String message){
            try {
                dataout.writeUTF(message);
            } catch (IOException e) {
            }
    }
        @Override
         public void run() {
            try{
                while(true){
                    message = datain.readUTF();
                    String[] separacion = message.split(";");
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
                             //AGREGAR FANTASMA (ERROR A VECES)
                             addFantasma();
                             break;
                         case "Speedy":
                             System.out.println("Crear Speedy");
                             break;
                         case "Bashful":
                             System.out.println("Crear Bashful");
                             break;   
                         case "Pokey":
                             System.out.println("Crear Pokey");
                             break;
                     }
                     break;
                 case "Fruta":
                     switch(cadena[1]){
                         case "Cereza":
                             System.out.println("Crear Cereza");
                             break;
                         case "Fresa":
                             System.out.println("Crear Fresa");
                             addFruta();
                             System.out.println(tablero.getPuntaje());
                             System.out.println(tablero.getVidas());
                             System.out.println(tablero.getPoder());
                             break;
                         case "Naranja":
                             System.out.println("Crear Naranja");
                             break;   
                     }
                     break;
                 case "Pastilla":
                     System.out.println("Crear Pastilla");
                     addPastilla();
                     break;
             }
         }
        
private void addFantasma(){
        int i = getRandom(22);
        int j = getRandom(22);
        while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
            i = getRandom(22);
            j = getRandom(22);
        }
        CFantasma fantasma = new CFantasma(tablero.getRandomColor(), i*25, j*25);
        fantasma.setDireccion(1);
         tablero.fantasmitas.add(fantasma);
         tablero.getiMatrizObj()[i][j] = 2;
}
private void addFruta(){
    int i = getRandom(22);
    int j = getRandom(22);
    while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
        i = getRandom(22);
        j = getRandom(22);
    }
     tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.RED,j*25,i*25));
     tablero.getiMatrizObj()[i][j] = 6;
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
}

private int getRandom(int num){
    Random random = new Random();
    int numX = random.nextInt(num);
    return numX;
}
}
