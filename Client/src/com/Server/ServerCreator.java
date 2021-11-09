
package com.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

/**
 * This class is in charge to receive the information and comunicate it to the other clients
 * @author Oscar Méndez Granados
 * @version 0.5
 */
public class ServerCreator implements Runnable {//clase
    private Socket port;//set the socket port----encapsulamiento--atributo
    private DataInputStream datain;//input data----encapsulamiento--atributo
    private DataOutputStream dataout;//output data----encapsulamiento--atributo

    private LinkedList<Socket> users = new LinkedList<Socket>();//active users list--encapsulamiento
    
    /**
     * 
     * @param port socket port
     * @param users list
     */
    public ServerCreator(Socket port,LinkedList users){//Costructor that manage the socket and the users list--metodo
        this.port = port;
        this.users = users;
    }
    
    
    /**
     * Receive the information and comunicate it to the other users
     */
    public void run() {//metodo
        try {
            //start the input and output channels
            datain = new DataInputStream(port.getInputStream());
            dataout = new DataOutputStream(port.getOutputStream());
            
            
            while(true){//Receive de clients messages
               String received = datain.readUTF();
               
                for (int i = 0; i < users.size(); i++) {
                    dataout = new DataOutputStream(users.get(i).getOutputStream());//send the message to all the active users
                    dataout.writeUTF(received);//send the message
                }
            }
        } catch (IOException e) {
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i) == port){
                    users.remove(i);//Remove user if is disconnected
                    break;
                } 
            }
        }
    }
}
