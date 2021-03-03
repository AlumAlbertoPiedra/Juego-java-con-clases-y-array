package com.venegaspiedraalberto.proyecto_breakout; 

import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


// Tengo que crear un array para los rectangulos y poder diferenciarlos entre si


public final class Bloques{ 
    char [] [] pos;
    int columnas= 20;
    int filas;
    int i =3;
    int p =3;
    int puntuacion = 0;
    int ballCurrentSpeedX = 3;
    int ballCurrentSpeedY = 3;
    int tamañoPala =80;
    int score = 0;
    final int ANCHOBLOQUE = 40;
    final int ALTOBLOQUE = 40;
    
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
        especialVelX =getNumAleatorio(0,19);
        especialVelY =getNumAleatorio(0,4);
        this.bloqueEspecialVelocidad(especialVelX,especialVelY);
        
        especialPalaX =getNumAleatorio(0,19);
        especialPalaY =getNumAleatorio(0,4);
        this.bloqueEspecialPala(especialPalaX,especialPalaY);
        
        this.mostrarPorConsola();  
    }
    
    public char eliminarBloque(int posX, int posY){
        char caracter= getchar(posX, posY);
        if (caracter == '+'){
            ballCurrentSpeedX = 11;
            ballCurrentSpeedY = 11;
        } else if (caracter == '-') {
            tamañoPala = 50;
        }
        char bloqueEliminado = ' ';
        pos [posX] [posY] = bloqueEliminado;
        this.mostrarPorConsola(); 
        return caracter;
 
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
        score += 100;
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