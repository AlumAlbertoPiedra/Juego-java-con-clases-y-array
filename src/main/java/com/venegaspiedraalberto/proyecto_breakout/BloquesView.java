
package com.venegaspiedraalberto.proyecto_breakout;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BloquesView extends GridPane {
    
    public BloquesView(Bloques bloques) {  
        this.setStyle("-fx-grid-lines-visible: true");
        for(int y=0; y<bloques.filas; y++) {
            for(int x=0; x<bloques.columnas; x++) {
                Rectangle rectangleObstaculo = new Rectangle();
                rectangleObstaculo.setWidth(bloques.anchoBloque);
                rectangleObstaculo.setHeight(bloques.altoBloque);
                rectangleObstaculo.setFill(Color.GREEN); 
                this.add(rectangleObstaculo, x, y);
            }
        }
    }
}