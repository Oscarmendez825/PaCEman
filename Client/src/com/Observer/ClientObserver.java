/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Observer;

import com.Pacman.CFantasma;
import com.Pacman.CFruta;
import com.Pacman.CPastilla;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Oscar
 */
public class ClientObserver implements Runnable{
       private Socket port;
    private int genport = 1201;
    private DataInputStream datain;
    private DataOutputStream dataout;
    private String message = "";
    private TableroObservador tablero;
        
    public ClientObserver(TableroObservador juego){
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
                        //addFantasma();
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
                        //addFruta();
                        break;
                    case "Naranja":
                        System.out.println("Crear Naranja");
                        break;   
                }
                break;
            case "Pastilla":
                System.out.println("Crear Pastilla");
                //addPastilla();
                break;
                
            case "PacmanDireccion":
                switch(cadena[1]){
                    case "1":
                        System.out.println("1");
                        tablero.Pacman.setDireccion(1);
                        break;
                    case "2":
                        System.out.println("2");
                        tablero.Pacman.setDireccion(2);
                        break;
                    case "3":
                        System.out.println("3");
                        tablero.Pacman.setDireccion(3);
                        break;   
                    case "4":
                        System.out.println("4");
                        tablero.Pacman.setDireccion(4);
                        break;   
                }
        }
    }
        
//    private void addFantasma(){
//            int i = getRandom(22);
//            int j = getRandom(22);
//            while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
//                i = getRandom(22);
//                j = getRandom(22);
//            }
//            CFantasma fantasma = new CFantasma(tablero.getRandomColor(), i*25, j*25);
//            fantasma.setDireccion(1);
//             tablero.fantasmitas.add(fantasma);
//             tablero.getiMatrizObj()[i][j] = 2;
//    }
//    private void addFruta(){
//        int i = getRandom(22);
//        int j = getRandom(22);
//        while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
//            i = getRandom(22);
//            j = getRandom(22);
//        }
//         tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.RED,j*25,i*25));
//         tablero.getiMatrizObj()[i][j] = 6;
//    }
//    private void addPastilla(){
//        int i = getRandom(22);
//        int j = getRandom(22);
//        while(tablero.getiMatrizObj()[i][j] == 1 || tablero.getiMatrizObj()[i][j] == 2 || tablero.getiMatrizObj()[i][j] == 3 || tablero.getiMatrizObj()[i][j] == 5 || tablero.getiMatrizObj()[i][j] == 6){
//            i = getRandom(22);
//            j = getRandom(22);
//        }
//         tablero.pastillas.add(tablero.getnPills(), new CPastilla(j*25,i*25));
//         tablero.getiMatrizObj()[i][j] = 6;
//    }

    private int getRandom(int num){
        Random random = new Random();
        int numX = random.nextInt(num);
        return numX;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        VentObservador w1 = new VentObservador();
        w1.PintarElementos();
        w1.setVisible(true);
        
    }
}
