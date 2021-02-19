package com.venegaspiedraalberto.proyecto_breakout; 

import java.util.Random;

public final class Bloques{ 
    char [] [] pos;
    int columnas= 5;
    int filas;
    int i = 3;
    int p =3;
    int puntuacion = 0;
    int ballCurrentSpeedX = 3;
    int ballCurrentSpeedY = 3;
    int tamañoPala =70;
    
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
        especialVelX = getNumAleatorio(0,4);
        especialVelY =getNumAleatorio(0,19);
        this.bloqueEspecialVelocidad(especialVelX,especialVelY);
        
        especialPalaX =getNumAleatorio(0,4);
        especialPalaY =getNumAleatorio(0,19);
        this.bloqueEspecialPala(especialPalaX,especialPalaY);
        
        this.mostrarPorConsola();  
    }
    
    public void eliminarBloque(int posX, int posY){
        char bloqueEliminado = ' ';
        char caracter= getchar(posX, posY);
        if (caracter == '+'){
            ballCurrentSpeedX *= 1.5;
            ballCurrentSpeedY *= 1.5;
        } else if (caracter == '-') {
            tamañoPala /= 2;
        }
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
    
    public boolean comprobarFilaVacia(int fila){
        for (int x=0; x<20; x++){
            char caracter = getchar(fila,x);
            if (caracter != ' '){
                System.out.println("La fila no esta vacia");
                System.out.println(caracter);
                return false;
            }            
        }
        System.out.println("La fila esta vacia");
        puntuacion += 100;
        return true;
    }
    
    public char getchar(int i, int p){
        char caracter = pos [i] [p];
        return caracter;
    }

    public void mostrarPorConsola() {
        for(int x=0; x<columnas; x++){
            for (int y=0; y<filas; y++){
                System.out.print(pos[x][y] + "");
            }
            System.out.println();
        }    
    }
    public void mostrarPuntuacion() {
        System.out.println(puntuacion);
    }
}