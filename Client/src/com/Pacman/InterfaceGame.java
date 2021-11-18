package com.Pacman;

/**
 * Interface que crea las direcciones de los elementos y  el metodo de moverlos
 * @author Gabriel Gonzalez
 * @author  Daniela Brenes
 * @author  Oscar Mendez
 */
public interface InterfaceGame {
    int PAB=1;
    int PAR=2;
    int IZQ=3;
    int DER=4;
    int Quiet=0;
    void moverElemento(int iEstado);
}
