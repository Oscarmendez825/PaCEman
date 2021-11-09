
package com.Server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

/**
 * This class is in charge to make the connection with the clients
 * @author Oscar Méndez Granados
 * @version 0.5
 */
public class Server {//clase
    private final int genport =1201;//Set the port that comunicates clients and server----encapsulamiento--atributo
    private final int userscant=10;//Set the max quantity of users that will be connected at the same time----encapsulamiento--atributo

    private LinkedList<Socket> users = new LinkedList<Socket>();//make a list with the active users----encapsulamiento
    
    /**
     * This method accept the client connection and add the new client into a list
     */
    public void startserver(){//metodo
        try {
            ServerSocket server = new ServerSocket(genport,userscant);//Set the port and the limit of users
            //Make the connection Server-Client
            while(true){
                System.out.println("Server Started....");
                System.out.println("Waiting for users...");
                
                Socket client = server.accept();//accept the client connection
                users.add(client);//add the user into the users list
                
                Runnable  run = new ServerCreator(client,users);//make a new thread to execute the new client
                Thread thread = new Thread(run);
                thread.start();//start the thread
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    } 
    /**
     * Main method of the Server class
     * @param args 
     */
    public static void main(String[] args) {//metodo
        Server server = new Server();
        server.startserver();
    }
}
