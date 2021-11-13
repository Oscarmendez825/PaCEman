package com.Pacman;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                     addFruta(cadena);
                     break;
                 case "Pastilla":
                     System.out.println("Crear Pastilla");
                     addPastilla();
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
        switch(color){
            case "rojo":
                CFantasma fantasma = new CFantasma(Color.RED, i*25, j*25);
                fantasma.setDireccion(1);
            {
                try {
                    dataout.writeUTF("Fantasma;rojo;"+i+";"+j);
                } catch (IOException ex) {
          
                }
            }
                tablero.fantasmitas.add(fantasma);
                tablero.getiMatrizObj()[i][j] = 2;
                break;

            case "rosado":
                CFantasma fantasma2 = new CFantasma(Color.PINK, i*25, j*25);
                fantasma2.setDireccion(1);
            {
                try {
                    dataout.writeUTF("Fantasma;rosado;"+i+";"+j);
                } catch (IOException ex) {
                
                }
            }
                tablero.fantasmitas.add(fantasma2);
                tablero.getiMatrizObj()[i][j] = 2;

                break;

            case "celeste":
                CFantasma fantasma3 = new CFantasma(Color.cyan,i*25, j*25);
                fantasma3.setDireccion(1);
            {
                try {
                    dataout.writeUTF("Fantasma;celeste;"+i+";"+j);
                } catch (IOException ex) {
                 
                }
            }
                tablero.fantasmitas.add(fantasma3);
                tablero.getiMatrizObj()[i][j] = 2;

                break;

            case "naranja":
                CFantasma fantasma4 = new CFantasma(Color.ORANGE, i*25, j*25);
                fantasma4.setDireccion(1);
            {
                try {
                    dataout.writeUTF("Fantasma;naranja;"+i+";"+j);
                } catch (IOException ex) {
         
                }
            }
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
        switch(cadena[1]){
            case "Cereza":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.RED,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
            {
                try {
                    dataout.writeUTF("FObservador;Cereza;"+i+";"+j);
                } catch (IOException ex) {
                }
            }
                break;

            case "Limon":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.GREEN,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                {
                try {
                    dataout.writeUTF("FObservador;Limon;"+i+";"+j);
                } catch (IOException ex) {
                }
            }
                break;
            case "Naranja":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.ORANGE,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                {
                try {
                    dataout.writeUTF("FObservador;Naranja;"+i+";"+j);
                } catch (IOException ex) {
                }
            }
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
         {
        try {
            dataout.writeUTF("GenPastilla;"+i+";"+j);
        }catch (IOException ex) {
        }
    }
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
