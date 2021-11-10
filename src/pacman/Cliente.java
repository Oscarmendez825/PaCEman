package pacman;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

public class Cliente implements Runnable {

        private Socket port;
        private int genport = 1201;
        private DataInputStream datain;
        private DataOutputStream dataout;
        String message = "";

        public Cliente(){

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
                 case "Fruta":
                     switch(cadena[1]){
                         case "Cereza":
                             System.out.println("Crear Cereza");
                             break;
                         case "Fresa":
                             System.out.println("Crear Fresa");
                             break;
                         case "Naranja":
                             System.out.println("Crear Naranja");
                             break;   
                     }
                 case "Pastilla":
                     System.out.println("Crear Pastilla");
                     break;
             }
         }
        

         
}
