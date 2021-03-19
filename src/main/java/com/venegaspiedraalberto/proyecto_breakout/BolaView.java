package com.venegaspiedraalberto.proyecto_breakout;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BolaView {
    double ballCenterY = 450;
    double ballCenterX = 500;
    double ballCurrentSpeedX = -5;
    double ballCurrentSpeedY = -5;
    Circle circleBall;
    
    public BolaView(){
        circleBall = new Circle(ballCenterX, ballCenterY, 8, Color.LIGHTGRAY);
    
    }
    
    
}