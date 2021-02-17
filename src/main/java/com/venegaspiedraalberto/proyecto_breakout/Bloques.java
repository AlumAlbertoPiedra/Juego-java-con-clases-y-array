package com.venegaspiedraalberto.proyecto_breakout; 

import java.util.Random;

public final class Bloques{ 
    char [] [] pos;
    int columnas= 5;
    int filas;
    
    public Bloques(int filas){
        this.filas = filas;
        char bloque = '*';
        int especialVelX;
        int especialVelY;
        int especialPalaX;
        int especialPalaY;
        pos = new char [columnas] [filas];
        for(int c=0; c<columnas; c++){
            for(int f=0; f<filas; f++){
                pos [c] [f] = bloque;
            }
        }
        
        /*especialPalaX =getNumAleatorio(0,9);
        especialPalaY =getNumAleatorio(0,9);
        this.bloqueEspecialPala(especialPalaX,especialPalaY);*/
        
        especialVelX =getNumAleatorio(0,9);
        especialVelY =getNumAleatorio(0,9);
        this.bloqueEspecialVelocidad(especialVelX,especialVelY);
        
        this.mostrarPorConsola();  
    }
    
    public void eliminarBloque(int posX, int posY){
        char bloqueEliminado = ' ';
        pos [posX] [posY] = bloqueEliminado;
        this.mostrarPorConsola();
    }
    
    public void bloqueEspecialVelocidad(int especialVelX, int especialVelY){
        char bloqueEspecialVelocidad = '+';
        pos [especialVelX] [especialVelY] = bloqueEspecialVelocidad;
    }
    public void bloqueEspecialPala(int especialPalaX, int especialPalaY){
        char bloqueEspecialPala = '-';
        pos [especialPalaX] [especialPalaY] = bloqueEspecialPala;
    }
    public int getNumAleatorio(int min, int max) {
        Random random = new Random();
        int num = random.nextInt(max-min+1) + min;
        return num;
    }

    public void mostrarPorConsola() {
        for(int x=0; x<columnas; x++){
            for (int y=0; y<filas; y++){
                System.out.print(pos[x][y] + "");
            }
            System.out.println();
        }    
    }
}