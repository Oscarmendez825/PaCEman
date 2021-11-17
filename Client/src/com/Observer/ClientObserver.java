package com.Observer;

import com.Pacman.CFantasma;
import com.Pacman.CFruta;
import com.Pacman.CPastilla;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Arrays;
/**
 *
 * @author Oscar
 */
public class ClientObserver implements Runnable{
    private Socket port;
    private int genport = 8080;
    private InputStreamReader inputStreamReader;
    private DataOutputStream dataout;
    private BufferedReader bufferedReader;
    private String message = "";
    private TableroObservador tablero;
    private static String jugObs = ""; 
    
    
    public ClientObserver(TableroObservador juego){
        this.tablero = juego;

        try {
            port = new Socket("127.0.0.1",genport);
            dataout = new DataOutputStream(port.getOutputStream());
            inputStreamReader = new InputStreamReader(port.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {

        }
    }
    @Override
     public void run() {
        try{
            while(true){
                message = bufferedReader.readLine();
                String[] separacion = message.split(",");
                if(separacion[0].equals(jugObs)){
                    System.out.println(Arrays.asList(eliminar(separacion)));
                    accion(eliminar(separacion));
                }
            }
        }catch(IOException e){
        }

    }
         
    private void accion(String[] cadena){
        switch(cadena[0]){
            case "GenPastilla":
                agregarPastila(cadena);
                break;               
            case "PacmanDireccion":
                switch(cadena[1]){
                    case "1":
                        tablero.Pacman.setDireccion(1);
                        break;
                    case "2":
                        tablero.Pacman.setDireccion(2);
                        break;
                    case "3":
                        tablero.Pacman.setDireccion(3);
                        break;   
                    case "4":
                        tablero.Pacman.setDireccion(4);
                        break;   
                }
                break;
            case "FDireccion":
                System.out.println(Arrays.asList(cadena));
                moverFantasma(cadena);
                break;
            case "Fantasma":
                System.out.println(Arrays.asList(cadena));
                agregarFantasma(cadena);
                break;
            case "FObservador":
                genFrutas(cadena);
                break;

    }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        VentObservador w1 = new VentObservador();
        w1.PintarElementos();
        w1.setVisible(true);
        
    }

    private void moverFantasma(String[] valores) {
        int posicion = Integer.parseInt(valores[2]);
        switch(valores[1]){
        case "1":
            tablero.fantasmitas.get(posicion).setDireccion(1);
            break;
        case "2":
            tablero.fantasmitas.get(posicion).setDireccion(2);
            break;
        case "3":
            tablero.fantasmitas.get(posicion).setDireccion(3);
            break;
        case "4":
            tablero.fantasmitas.get(posicion).setDireccion(4);
            break;
            
    }
    }

    private void agregarFantasma(String[] propiedades) {
        int i = Integer.parseInt(propiedades[2]);
        int j = Integer.parseInt(propiedades[3]);
        switch(propiedades[1]){
            case "rojo":
                CFantasma fantasma = new CFantasma(Color.RED, i*25, j*25);
                fantasma.setDireccion(1);
                tablero.fantasmitas.add(fantasma);
                tablero.getiMatrizObj()[i][j] = 2;
                break;
            case "rosado":
                CFantasma fantasma2 = new CFantasma(Color.PINK, i*25, j*25);
                fantasma2.setDireccion(1);
                tablero.fantasmitas.add(fantasma2);
                tablero.getiMatrizObj()[i][j] = 2;
                break;
            case "celeste":
                CFantasma fantasma3 = new CFantasma(Color.cyan,i*25, j*25);
                fantasma3.setDireccion(1);
                tablero.fantasmitas.add(fantasma3);
                tablero.getiMatrizObj()[i][j] = 2;
                break;
            case "naranja":
                CFantasma fantasma4 = new CFantasma(Color.ORANGE, i*25, j*25);
                fantasma4.setDireccion(1);
                tablero.fantasmitas.add(fantasma4);
                tablero.getiMatrizObj()[i][j] = 2;
                break;
        }
    }

    private void genFrutas(String[] cadena) {
        int i = Integer.parseInt(cadena[2]);
        int j = Integer.parseInt(cadena[3]);
        switch(cadena[1]){
            case "Cereza":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.RED,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                break;
            case "Limon":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.GREEN,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                break;
            case "Naranja":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.ORANGE,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                break;   
         }
        
    }

    private void agregarPastila(String[] cadena) {
       int i = Integer.parseInt(cadena[1]);
       int j = Integer.parseInt(cadena[2]);
       tablero.pastillas.add(tablero.getnPills(), new CPastilla(j*25,i*25));
       tablero.getiMatrizObj()[i][j] = 6;
    }

    public static void setJugObs(String jugObs) {
        ClientObserver.jugObs = jugObs;
    }
    
        private String[] eliminar(String[] arr){
        String[] newArr = null;
        
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i].equals(jugObs)){
                newArr = new String[arr.length - 1];
                for(int index = 0; index < i; index++){
                    newArr[index] = arr[index];
                }
                for(int j = i; j < arr.length - 1; j++){
                    newArr[j] = arr[j+1];
                }
                break;
            }
        }
        return newArr;
    }
}

