package com.Pacman;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



public class Ventana extends JFrame{
    
    public CPanelPrincipal PanelMain; 
    private JPanel panel;
    private JButton JBInicio;
    private boolean b_Iniciado = false ;
    
    JPanel scorePanel = new JPanel();
    

    
   
    public Ventana()
    { 
        super("Pacman");
        setSize(600,660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        PanelMain = new CPanelPrincipal();
        panel     = new JPanel();
        
        JBInicio = new JButton("Iniciar");
        
        
       
        //lblVidas.setText(Integer.toString(PanelMain.getPuntos()));
        
        panel.add(JBInicio);
        PanelMain.addKeyListener(PanelMain);
        PanelMain.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent arg0) {
            }

            @Override
            public void focusLost(FocusEvent arg0) {
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