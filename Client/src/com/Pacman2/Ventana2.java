/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Pacman2;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Oscar
 */
public class Ventana2 extends JFrame{
     public CPanelPrincipal2 PanelMain; 
    private JPanel panel;
    private JButton JBInicio;
    private boolean b_Iniciado = false ;
    
    
    public Ventana2()
    { 
        super("Pacman");
        setSize(600,660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        PanelMain = new CPanelPrincipal2();
        panel     = new JPanel();
        
        JBInicio = new JButton("Iniciar");
       
       
        panel.add(JBInicio);
        PanelMain.addKeyListener(PanelMain);
        PanelMain.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
//                JOptionPane.showMessageDialog(null, "Gane el foco", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }

            @Override
            public void focusLost(FocusEvent arg0) {
 //JOptionPane.showMessageDialog(null, "perdi el foco", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
    }
   
    
     public void PintarElementos()
     {           
       Container Content = getContentPane();
       
       JBInicio.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) 
            {
               if(!b_Iniciado)
               {
                  
                  JBInicio.setText("Pausar");
                  PanelMain.iniciar();
                  JBInicio.setEnabled(false);
                 // JBInicio.setEnabled(false);
                 
                  b_Iniciado = true;
               }
               else
               {
                  PanelMain.pausar();
                  JBInicio.setEnabled(true);
                  JBInicio.setText("Continuar");
                
                  b_Iniciado = false;
               }
            }
        });
      
       Content.add(PanelMain,BorderLayout.CENTER);
       Content.add(panel,BorderLayout.SOUTH);
       
    }
}
    
