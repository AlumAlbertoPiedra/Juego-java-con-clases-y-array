
package com.venegaspiedraalberto.proyecto_breakout;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BloquesView extends GridPane {
        //Rectangle rectangleObstaculo = new Rectangle();
        int ballCenterY = 450;
        int ballCenterX = 500;
        Circle circleBall = new Circle(ballCenterX, ballCenterY, 8, Color.LIGHTGRAY);
        Rectangle [] [] rect;
        Bloques bloques;
        
        
        
    
    public BloquesView(Bloques bloques) { 
        this.bloques =bloques;
        rect = new Rectangle [bloques.columnas] [bloques.filas];
        for (int a= 0; a<bloques.columnas; a++){
            for(int b=0; b<bloques.filas; b++){
                Rectangle rectangulo = new Rectangle(bloques.ANCHOBLOQUE, bloques.ALTOBLOQUE);
                rect [a] [b] = rectangulo;
            }
        }
        this.setStyle("-fx-padding: 140 0 0 0;-fx-grid-lines-visible: true");
        for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.columnas; x++) {
                char caracter= bloques.getchar(x, y);
                rect [x] [y].setWidth(bloques.ANCHOBLOQUE);
                rect [x] [y].setHeight(bloques.ALTOBLOQUE);
                this.add(rect [x] [y], x, y);
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
        this.inicializar();
        
        
    }
    public void inicializar(){
        for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.columnas; x++) {
                rect [x] [y].setTranslateX(0);
            }
        }
    }
    public void colisionObjeto(){
        for(int x=0; x<bloques.columnas; x++) {
            for(int y=0; y<bloques.filas; y++) {
                Shape shapeColision = Shape.intersect(circleBall, rect [x] [y]);
                boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                if (colisionVacia == false) {
                    bloques.ballCurrentSpeedX = -bloques.ballCurrentSpeedX;
                    bloques.ballCurrentSpeedY = -bloques.ballCurrentSpeedY;
                    System.out.println("Hola");
                    bloques.eliminarBloque(x, y);
                    rect[x] [y].setTranslateX(2000);
                }    
            }
        }      
}
    public void resetGame() {
        this.getChildren().removeAll();
        this.inicializar();
        bloques.mostrarPorConsola();
    }
}