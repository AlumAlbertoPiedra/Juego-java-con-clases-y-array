
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
        int vida = 1;
        int vidaJefe = 3;
        
        
    
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
        bloques.ballCurrentSpeedX = 3;
        bloques.ballCurrentSpeedY = 3;
        vida = 1;
        vidaJefe = 3;
        /*groupPerson.setLayoutX(50);
        groupPerson.setLayoutY(0);
        root.getChildren().remove(imagenGameOverView); */
        
    }
}