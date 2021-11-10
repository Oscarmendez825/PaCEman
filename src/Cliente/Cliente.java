package Cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Cliente implements Runnable {//clase
    //Atributes
        private Socket port;
        private int genport = 1201;
        private DataInputStream datain;
        private DataOutputStream dataout;
        private String message="";

    public Cliente(){

        try {
            port = new Socket("127.0.0.1",genport);//make the connection to the server
            datain = new DataInputStream(port.getInputStream());//read the input data
            dataout = new DataOutputStream(port.getOutputStream());//read the output data
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * this method take and read the messages and show the messages on screen
     */
     public void run() {//metodo
        try{
            while(true){
                message += datain.readUTF();//read message and save it in a string variable
                System.out.println(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

     public void sendData(String message){//metodo
        try {
            dataout.writeUTF(message);//send messages
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
