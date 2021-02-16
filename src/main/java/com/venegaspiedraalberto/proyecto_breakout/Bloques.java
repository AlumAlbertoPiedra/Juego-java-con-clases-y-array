package com.venegaspiedraalberto.proyecto_breakout; 

public final class Bloques{ 
    char [] [] pos;
    int columnas= 5;
    int filas;
    
    public Bloques(int filas){
        this.filas = filas;
        char bloque = '*';
        pos = new char [columnas] [filas];
        for(int c=0; c<columnas; c++){
            for(int f=0; f<filas; f++){
                pos [c] [f] = bloque;
            }
        }
        this.mostrarPorConsola();  
    }
    
    public void eliminarBloque(int posX, int posY){
        char bloqueEliminado = '-';
        pos [posX] [posY] = bloqueEliminado;
        this.mostrarPorConsola();
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