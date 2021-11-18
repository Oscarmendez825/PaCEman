/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Observer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Oscar
 */
public class VentObservador extends JFrame{
    public PanelObservador PanelMain; 
    private JPanel panel;
    private JButton JBInicio;
    private boolean b_Iniciado = true ;
    private static JLabel puntos;
    private JLabel textP;
    private static JLabel vidas;
    private JLabel textV;
    
    public VentObservador()
    { 
        super("Pacman");
        setSize(600,660);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        PanelMain = new PanelObservador();
        panel     = new JPanel();
        puntos = new JLabel();
        textP = new JLabel();
        vidas = new JLabel();
        textV = new JLabel();
        JBInicio = new JButton("Iniciar");
       
       
        panel.add(JBInicio);
        panel.add(textP);
        panel.add(puntos);
        panel.add(textV);
        panel.add(vidas);
        textP.setText("       PUNTUACION");
        textP.setForeground(Color.BLACK);
        textP.setFont(new Font("Serif", Font.PLAIN, 14));

        puntos.setText("0");
        puntos.setForeground(Color.BLACK);
        puntos.setFont(new Font("Serif", Font.PLAIN, 14));
        
        
        textV.setText("      VIDAS");
        textV.setForeground(Color.BLACK);
        textV.setFont(new Font("Serif", Font.PLAIN, 14));
        
        vidas.setText("3");
        vidas.setForeground(Color.BLACK);
        vidas.setFont(new Font("Serif", Font.PLAIN, 14));
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

    public static JLabel getPuntos() {
        return puntos;
    }

    public static JLabel getVidas() {
        return vidas;
    }
     
    
}
