
package com.venegaspiedraalberto.proyecto_breakout;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BloquesView extends GridPane {
        Rectangle rectangleObstaculo = new Rectangle();
        int ballCenterY = 350;
        int ballCenterX = 400;
        Circle circleBall = new Circle(ballCenterX, ballCenterY, 8, Color.LIGHTGRAY);
    
    public BloquesView(Bloques bloques) {  
        this.setStyle("-fx-padding: 140 0 0 0;-fx-grid-lines-visible: true");
        for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.columnas; x++) {
                char caracter= bloques.getchar(x, y);
                switch (caracter) {
                    case '+':
                        rectangleObstaculo.setWidth(bloques.ANCHOBLOQUE);
                        rectangleObstaculo.setHeight(bloques.ALTOBLOQUE);
                        rectangleObstaculo.setFill(Color.BLUE);
                        this.add(rectangleObstaculo, x, y);
                        break;
                    case '-':
                        rectangleObstaculo.setWidth(bloques.ANCHOBLOQUE);
                        rectangleObstaculo.setHeight(bloques.ALTOBLOQUE);
                        rectangleObstaculo.setFill(Color.GREEN);
                        this.add(rectangleObstaculo, x, y);
                        break;
                    case '*':
                        rectangleObstaculo.setWidth(bloques.ANCHOBLOQUE);
                        rectangleObstaculo.setHeight(bloques.ALTOBLOQUE);
                        rectangleObstaculo.setFill(Color.YELLOW);
                        this.add(rectangleObstaculo, x, y);
                        break;
                    default:
                        break;
                }  
            }
        }
    }
    public void colisionObjeto(Bloques bloques){
        Shape shapeColision = Shape.intersect(circleBall, rectangleObstaculo);
        boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
        if (colisionVacia == false) {
            bloques.ballCurrentSpeedX = -bloques.ballCurrentSpeedX;
            bloques.ballCurrentSpeedY = -bloques.ballCurrentSpeedY;
        }          
    }
    
        
}