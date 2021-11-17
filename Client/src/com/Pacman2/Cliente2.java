package com.Pacman2;

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
import java.util.Random;

/**
 * 
 * @author Oscar Mendez
 * @author Gabriel Gonzalez
 * @author Daniela Brenes
 * Clase cliente de comunicacion por sockets del jugador 2
 * 
 */
public class Cliente2 implements Runnable{
    
    private Socket port;
    private int genport = 8080;
    private DataOutputStream dataout;
    private String message = "";
    private CTablero2 tablero;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    
    /**
    * Constructor de la clase Cliente
    * @param juego:CTablero2
    */    
    
    public Cliente2(CTablero2 juego){
        this.tablero = juego;

        try {
            port = new Socket("127.0.0.1",genport);
            dataout = new DataOutputStream(port.getOutputStream());
            inputStreamReader = new InputStreamReader(port.getInputStream());
            bufferedReader = new BufferedReader(inputStreamReader);
        } catch (IOException e) {

        }
    }
    /**
    * Envia mensaje del cliente al servidor
    * @param message:String
    */
    public void mandarMensaje(String message){
        try {
            dataout.write(("Cliente2,"+message).getBytes());
        } catch (IOException e) {
        }
}
    /**
     * 
     * Responsable de la comunicacion continua entre el servidor y cliente
     * 
     */
    @Override
     public void run() {
        try{
            while(true){
                message = bufferedReader.readLine();
                String[] separacion = message.split(",");
                if(separacion[0].equals("Cliente2")){
                    accion(eliminar(separacion));System.out.println(Arrays.asList(eliminar(separacion)));
                    accion(eliminar(separacion));
                    }
            }
        }catch(IOException e){
        }

    }
     /**
     * Decide que accion realizar segun lo que el servidor le indique
     * @param cadena:String[]
     * 
     */    
     private void accion(String[] cadena){
         switch(cadena[0]){
             case "Enemigo":
                 switch(cadena[1]){
                     case "Shadow":
                         addFantasma("rojo");
                         break;
                     case "Speedy":
                         addFantasma("rosado");
                         break;
                     case "Bashful":
                         addFantasma("celeste");
                         break;   
                     case "Pokey":
                         addFantasma("naranja");
                         break;
                 }
                 break;
             case "Fruta":
                 addFruta(cadena);
                 break;
             case "Pastilla":
                 addPastilla();
                 break;
            case "Puntuacion":
                 int puntaje = Integer.parseInt(cadena[1]);
                 tablero.setPuntaje(puntaje);
                 break;
             case "Vida":
                 int vidas = Integer.parseInt(cadena[1]);
                 CTablero2.setVidas(vidas);
                 break;
         }
     }
    /**
     * Agrega un fantasma en la interfaz del jugador
     * @param color:String
     */         
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
                datos = "Fantasma,rojo,"+i+","+j;
                mandarMensaje(datos);
                CFantasma fantasma = new CFantasma(Color.RED, i*25, j*25);
                fantasma.setDireccion(1);
                tablero.fantasmitas.add(fantasma);
                tablero.getiMatrizObj()[i][j] = 2;
                break;

            case "rosado":
                datos = "Fantasma,rosado,"+i+","+j;
                mandarMensaje(datos);           
                CFantasma fantasma2 = new CFantasma(Color.PINK, i*25, j*25);
                fantasma2.setDireccion(1);
                tablero.fantasmitas.add(fantasma2);
                tablero.getiMatrizObj()[i][j] = 2;

                break;

            case "celeste":
                datos = "Fantasma,celeste,"+i+","+j;
                mandarMensaje(datos);           
                CFantasma fantasma3 = new CFantasma(Color.cyan,i*25, j*25);
                fantasma3.setDireccion(1);
                tablero.fantasmitas.add(fantasma3);
                tablero.getiMatrizObj()[i][j] = 2;

                break;

            case "naranja":
                datos = "Fantasma,naranja,"+i+","+j;
                mandarMensaje(datos);
                CFantasma fantasma4 = new CFantasma(Color.ORANGE, i*25, j*25);
                fantasma4.setDireccion(1);
                tablero.fantasmitas.add(fantasma4);
                tablero.getiMatrizObj()[i][j] = 2;
                break;

        }
    }

    /**
     * Agrega una fruta en la interfaz del jugador
     * @param cadena:String
     */    
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
                datos = "FObservador,Cereza,"+i+","+j;
                mandarMensaje(datos);
                break;

            case "Limon":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.GREEN,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                datos = "FObservador,Limon,"+i+","+j;
                mandarMensaje(datos);
                break;
            case "Naranja":
                tablero.frutas.add(tablero.getnFruits(), new CFruta(Color.ORANGE,j*25,i*25));
                tablero.getiMatrizObj()[i][j] = 6;
                datos = "FObservador,Naranja,"+i+","+j;
                mandarMensaje(datos);
                break;   
         }
         
    }
    /**
     * Agrega una pastilla en la interfaz del jugador
     */
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
        datos = "GenPastilla,"+i+","+j;
        mandarMensaje(datos);
    
    }
    /**
     * Elimina elemento de un array
     * @param arr:String[]
     * @return String[]
     */    
    private String[] eliminar(String[] arr){
    String[] newArr = null;
        
        for (int i = 0; i < arr.length-1; i++) {
            if(arr[i].equals("Cliente2")){
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
    /**
     * Brinda un numero random en un rango definido
     * @param num:Integer
     * @return Integer
     */    
    private int getRandom(int num){
        Random random = new Random();
        int numX = random.nextInt(num);
        return numX;
    }

}

    

