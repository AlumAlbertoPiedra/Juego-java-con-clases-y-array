
package com.venegaspiedraalberto.proyecto_breakout;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BloquesView extends GridPane {
        //Rectangle rectangleObstaculo = new Rectangle();
        int ballCenterY = 350;
        int ballCenterX = 400;
        Circle circleBall = new Circle(ballCenterX, ballCenterY, 8, Color.LIGHTGRAY);
        Rectangle [] [] rect;
        
        
    
    public BloquesView(Bloques bloques) {  
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
                switch (caracter) {
                    case '+':
                        rect [x] [y].setWidth(bloques.ANCHOBLOQUE);
                        rect [x] [y].setHeight(bloques.ALTOBLOQUE);
                        rect [x] [y].setFill(Color.BLUE);
                        this.add(rect [x] [y], x, y);
                        break;
                    case '-':
                        rect [x] [y].setWidth(bloques.ANCHOBLOQUE);
                        rect [x] [y].setHeight(bloques.ALTOBLOQUE);
                        rect [x] [y].setFill(Color.GREEN);
                        this.add(rect [x] [y], x, y);
                        break;
                    case '*':
                        rect [x] [y].setWidth(bloques.ANCHOBLOQUE);
                        rect [x] [y].setHeight(bloques.ALTOBLOQUE);
                        rect [x] [y].setFill(Color.YELLOW);
                        this.add(rect [x] [y], x, y);
                        break;
                    default:
                        break;
                }  
            }
        }
    }
    public void colisionObjeto(Bloques bloques){
        for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.filas; x++) {
                Shape shapeColision = Shape.intersect(circleBall, rect [x] [y]);
                boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                if (colisionVacia == false) {
                    bloques.ballCurrentSpeedX = -bloques.ballCurrentSpeedX;
                    bloques.ballCurrentSpeedY = -bloques.ballCurrentSpeedY;
                }    
            }
        }      
    }
    
        
}