
package com.Client;

import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
/**
 * This class is based on the graphical interface that the user will have to chat, as well as some functions related to reading messages
 * @author Oscar Méndez Granados
 * @version 0.5
 */
public class ClientExec extends javax.swing.JFrame {//herencia--clase
//instancias
    

    Client client;

    /**
     * Is responsible for initializing some functions in the class
     * 
     */
    public ClientExec() {
       
        initComponents();
        client = new Client(screenbox);//send the screenbox to the ClientClass Consturctor
        Thread thread = new Thread(client);//make a new thread--instancia
        thread.start();//start thread
        screenbox.setEditable(false);//restrict that the box where the message is displayed cannot be edited
       

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        messagebox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        sendbutton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        screenbox = new javax.swing.JEditorPane();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(200, 150));
        setMinimumSize(new java.awt.Dimension(935, 580));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Orbitron", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ServMess(Messenger Service)");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 10, 870, 40);

        messagebox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                messageboxKeyTyped(evt);
            }
        });
        getContentPane().add(messagebox);
        messagebox.setBounds(20, 370, 650, 130);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/LOGO.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(550, 320, 280, 200);

        sendbutton.setText("SEND");
        sendbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendbuttonActionPerformed(evt);
            }
        });
        getContentPane().add(sendbutton);
        sendbutton.setBounds(830, 370, 80, 40);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Write your message here.....");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(20, 330, 610, 40);

        jScrollPane2.setViewportView(screenbox);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(10, 50, 900, 270);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/images/30413hd.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 930, 560);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
 * It is in charge of sending the message to the Client class once the button is pressed
 * @param evt 
 */
    //metodo
    private void sendbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendbuttonActionPerformed
       
        try{
            String outmessage = "";//Set the variable that save the message that will be send
            outmessage = messagebox.getText().trim();//Take the text that was wrote on the messagebox
            messagebox.setText("");//Clean messagebox
            client.sendmessage(outmessage+"\n");//call sendmessage method and share it the message
  
        }catch (Exception e){

        }

    }//GEN-LAST:event_sendbuttonActionPerformed
/**
 * Receive an event in case the "Enter" key is pressed and thus send the message to the Client class as the "sendbutton" button does
 * @param evt 
 */
    //metodo
    private void messageboxKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_messageboxKeyTyped
        char teclaenter = evt.getKeyChar();
        if (teclaenter==KeyEvent.VK_ENTER){
            sendbutton.doClick();//allow to send the message if enter is pulsed
        }
    }//GEN-LAST:event_messageboxKeyTyped
    /**
     * Main Method of the ClientExec class
     * @param args 
     */
    public static void main(String args[]) {//metodo
        


        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientExec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientExec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientExec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientExec.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
  


        

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientExec().setVisible(true);
                
            }
        });
                 
            

                
                
            }
        

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField messagebox;
    private javax.swing.JEditorPane screenbox;
    private javax.swing.JButton sendbutton;
    // End of variables declaration//GEN-END:variables
}
