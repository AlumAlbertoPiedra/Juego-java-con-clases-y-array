
package com.venegaspiedraalberto.proyecto_breakout;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BloquesView extends GridPane {
        //Rectangle rectangleObstaculo = new Rectangle();
        Rectangle [] [] rect;
        Bloques bloques;
        BolaView bolaView;
        
        
        
    
    public BloquesView(Bloques bloques) { 
        this.bloques = bloques;
        rect = new Rectangle [bloques.columnas] [bloques.filas];
        for (int a= 0; a<bloques.columnas; a++){
            for(int b=0; b<bloques.filas; b++){
                Rectangle rectangulo = new Rectangle(bloques.ANCHOBLOQUE, bloques.ALTOBLOQUE);
                rect [a] [b] = rectangulo;
                this.add(rect [a] [b], a, b);
            }
        }
        this.setStyle("-fx-padding: 140 0 0 0;-fx-grid-lines-visible: true");
        this.actualizarBloque();
        
        //this.inicializar();
        
        
    }
    public void inicializar(){
        for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.columnas; x++) {
                rect [x] [y].setTranslateX(0);
            }
        }
    }
    public void actualizarBloque(){
    for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.columnas; x++) {
                char caracter= bloques.getchar(x, y);
                rect [x] [y].setWidth(bloques.ANCHOBLOQUE);
                rect [x] [y].setHeight(bloques.ALTOBLOQUE);
                switch (caracter) {
                    case '+':
                        rect [x] [y].setFill(Color.BLUE);
                        break;
                    case '-':
                        rect [x] [y].setFill(Color.GREEN);
                        break;
                    case '*':
                        rect [x] [y].setFill(Color.YELLOW);
                        break;
                    default:
                        break;
                }  
            }
        }
    }
    public void colisionObjeto(BolaView bolaView){
        for(int x=0; x<bloques.columnas; x++) {
            for(int y=0; y<bloques.filas; y++) {
                Shape shapeColision = Shape.intersect(bolaView.circleBall, rect [x] [y]);
                boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                if (colisionVacia == false) {
                    bolaView.ballCurrentSpeedX = -bolaView.ballCurrentSpeedX;
                    bolaView.ballCurrentSpeedY = -bolaView.ballCurrentSpeedY;
                    //System.out.println("Hola");
                    bloques.eliminarBloque(x, y, bolaView);
                    rect[x] [y].setTranslateX(2000);
                }    
            }
        }      
}
    public void resetGame() {
        
        this.getChildren().removeAll();
        this.inicializar();
        //bloques.mostrarPorConsola();
    }
}