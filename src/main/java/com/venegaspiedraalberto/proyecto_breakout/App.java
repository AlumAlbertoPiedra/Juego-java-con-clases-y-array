package com.venegaspiedraalberto.proyecto_breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.LEFT;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    int SCENE_TAM_X = 800;
    int SCENE_TAM_Y = 600;
    int ANCHURA_PALA = 70;
    int ALTURA_PALA = 10;
    int ballCenterY = 350;
    int ballCurrentSpeedY = 3;
    int ballCenterX = 400;
    int ballCurrentSpeedX = 3;
    int stickCurrentSpeed = 0;
    int stickPosX = (SCENE_TAM_X - ANCHURA_PALA) / 2;
    int velocidadJefe = 5;
    int vida = 1;
    int vidaJefe = 3;
   
    Rectangle rectangleObstaculo1 = new Rectangle(5, 200, 193, 50);
    Rectangle rectangleObstaculo2 = new Rectangle(203, 200, 193, 50);
    Rectangle rectangleObstaculo3 = new Rectangle(403, 200, 193, 50);
    Rectangle rectangleObstaculo4 = new Rectangle(603, 200, 193, 50);
    
    Group groupPerson= new Group();;

    @Override
    public void start(Stage stage) {
       

        //Creación de personaje
        Rectangle rectangleCuerpoInferior = new Rectangle(0, 50, 50, 70);
        Rectangle rectangleMano1 = new Rectangle(-15, 40, 15, 30);
        Rectangle rectangleMano2 = new Rectangle(50, 40, 15, 30);
        Rectangle rectangleCuerpoSuperior = new Rectangle(0, 35, 50, 20);
        Polygon polygonCuello = new Polygon(10.0, 35.0, 40.0, 35.0, 32.0, 15.0, 17.0, 15.0);
        polygonCuello.setStrokeWidth(5.0);
        Circle circleCabeza = new Circle(24, 12, 10);
        Polygon polygonCuerno1 = new Polygon(13.0, 5.0, 5.0, 5.0, 5.0, 12.0);
        polygonCuerno1.setLayoutX(25);
        polygonCuerno1.setLayoutY(-4);
        Polygon polygonCuerno2 = new Polygon(5.0, 5.0, 13.0, 5.0, 5.0, 12.0);
        polygonCuerno2.setLayoutX(3);
        polygonCuerno2.setLayoutY(-4);
        Polygon polygonOjo = new Polygon(5.0, 10.0, 15.0, 10.0, 10.0, 5.0);
        polygonOjo.setLayoutX(14);
        polygonOjo.setLayoutY(2);
        Rectangle zonaContacto1 = new Rectangle(-20,0,100,120);
        zonaContacto1.setVisible(false);

        //Creación de obstaculos

        //Background del juego
        Image img = new Image(getClass().getResourceAsStream("/images/bg_1_1.png"));
        ImageView imgView = new ImageView(img);

        //Color personaje
        rectangleMano1.setFill(Color.GREY);
        rectangleMano2.setFill(Color.GREY);
        rectangleCuerpoInferior.setFill(Color.PURPLE);
        rectangleCuerpoSuperior.setFill(Color.DARKGREY);
        polygonCuello.setFill(Color.PURPLE);
        circleCabeza.setFill(Color.PURPLE);
        polygonCuerno1.setFill(Color.GREY);
        polygonCuerno1.setRotate(270);
        polygonCuerno2.setFill(Color.GREY);
        polygonCuerno2.setRotate(180);
        polygonOjo.setFill(Color.RED);

        //Color Obstaculos
        rectangleObstaculo1.setFill(Color.BLUE);
        rectangleObstaculo2.setFill(Color.RED);
        rectangleObstaculo3.setFill(Color.YELLOW);
        rectangleObstaculo4.setFill(Color.GREEN);

        //Unión partes del personaje
        
        groupPerson.getChildren().add(rectangleCuerpoInferior);
        groupPerson.getChildren().add(rectangleCuerpoSuperior);
        groupPerson.getChildren().add(rectangleMano1);
        groupPerson.getChildren().add(rectangleMano2);
        groupPerson.getChildren().add(polygonCuello);
        groupPerson.getChildren().add(circleCabeza);
        groupPerson.getChildren().add(polygonCuerno1);
        groupPerson.getChildren().add(polygonCuerno2);
        groupPerson.getChildren().add(polygonOjo);
        groupPerson.getChildren().add(zonaContacto1);

        groupPerson.setLayoutX(50);
        groupPerson.setLayoutY(50);

        Pane root = new Pane();
        Scene scene = new Scene(root, SCENE_TAM_X, SCENE_TAM_Y, Color.LIGHTSLATEGREY);
        root.getChildren().add(imgView);
        root.getChildren().add(groupPerson);
        root.getChildren().add(rectangleObstaculo1);
        root.getChildren().add(rectangleObstaculo2);
        root.getChildren().add(rectangleObstaculo3);
        root.getChildren().add(rectangleObstaculo4);
        stage.setResizable(false);
        stage.setTitle("BreakoutFX");
        stage.setScene(scene);
        stage.show();

        //Creación de la pala del jugador
        Rectangle rectPala = new Rectangle(SCENE_TAM_X / 2, SCENE_TAM_Y - 70, ANCHURA_PALA, ALTURA_PALA);
        rectPala.setFill(Color.YELLOW);
        root.getChildren().add(rectPala);

        Circle circleBall = new Circle(ballCenterX, ballCenterY, 8, Color.LIGHTGRAY);
        root.getChildren().add(circleBall);

        Timeline animationBall = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (var ae) -> {
                    System.out.println(vidaJefe);
                    circleBall.setCenterY(ballCenterY);
                    ballCenterY += ballCurrentSpeedY;
                    circleBall.setCenterX(ballCenterX);
                    ballCenterX += ballCurrentSpeedX;
                    stickPosX += stickCurrentSpeed;
                    rectPala.setX(stickPosX);
                    groupPerson.setLayoutX(groupPerson.getLayoutX()+ velocidadJefe);
                    if (groupPerson.getLayoutX() > (SCENE_TAM_X -70)) {
                        velocidadJefe= -velocidadJefe;
                    }
                    if (groupPerson.getLayoutX() < 50 ) {
                        velocidadJefe= -velocidadJefe;
                    }
                    if (stickPosX < 0) {
                        stickPosX = 0;
                    } else {
                        if (stickPosX > (SCENE_TAM_X - ANCHURA_PALA)) {
                            stickPosX = (SCENE_TAM_X - ANCHURA_PALA);
                        }
                    }
                    if (ballCenterX >= SCENE_TAM_X) {
                        ballCurrentSpeedX = -7;
                    }
                    if (ballCenterX <= 0) {
                        ballCurrentSpeedX = 7;
                    }
                    if (ballCenterY >= SCENE_TAM_Y) {
                        ballCurrentSpeedX = 0;
                        ballCurrentSpeedY = 0;
                        ballCenterY = 400;
                        ballCenterX = 400;
                        vida = 0;
                    }
                    if (ballCenterY <= 0) {
                        ballCurrentSpeedY = 7;
                    }
                    Shape shapeColision = Shape.intersect(circleBall, rectPala);
                    boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                    if (colisionVacia == false) {
                        ballCurrentSpeedY = -4;
                    }
                    Shape shapeColision1 = Shape.intersect(circleBall, rectangleObstaculo1);
                    boolean colisionVacia1 = shapeColision1.getBoundsInLocal().isEmpty();
                    if (colisionVacia1 == false) {
                        ballCurrentSpeedY = -ballCurrentSpeedY;
                        rectangleObstaculo1.setLayoutX(-305);
                        rectangleObstaculo1.setLayoutY(-200);
                    }
                    Shape shapeColision2 = Shape.intersect(circleBall, rectangleObstaculo2);
                    boolean colisionVacia2 = shapeColision2.getBoundsInLocal().isEmpty();
                    if (colisionVacia2 == false) {
                        ballCurrentSpeedY = -ballCurrentSpeedY;
                        rectangleObstaculo2.setLayoutX(-603);
                        rectangleObstaculo2.setLayoutY(-200);
                    }
                    Shape shapeColision3 = Shape.intersect(circleBall, rectangleObstaculo3);
                    boolean colisionVacia3 = shapeColision3.getBoundsInLocal().isEmpty();
                    if (colisionVacia3 == false) {
                        ballCurrentSpeedY = -ballCurrentSpeedY;
                        rectangleObstaculo3.setLayoutX(-803);
                        rectangleObstaculo3.setLayoutY(-200);
                    }
                    Shape shapeColision4 = Shape.intersect(circleBall, rectangleObstaculo4);
                    boolean colisionVacia4 = shapeColision4.getBoundsInLocal().isEmpty();
                    if (colisionVacia4 == false) {
                        ballCurrentSpeedY = -ballCurrentSpeedY;
                        //root.getChildren().remove(rectangleObstaculo4);
                        rectangleObstaculo4.setLayoutX(-1003);
                        rectangleObstaculo4.setLayoutY(-200);
                    }
                   
                    Shape shapeColision5 = Shape.intersect(circleBall, zonaContacto1);
                    boolean colisionVacia5 = shapeColision5.getBoundsInLocal().isEmpty();
                    if (colisionVacia5 == false) {
                        ballCurrentSpeedY = -ballCurrentSpeedY;
                        /*ballCenterY = (ballCenterY + (ballCurrentSpeedY*2));
                        circleBall.setCenterY(ballCenterY);*/
                        if (groupPerson.getLayoutX()>= 690){
                            groupPerson.setLayoutX(groupPerson.getLayoutX() -110);
                        } else if (groupPerson.getLayoutY() <= 110){
                            groupPerson.setLayoutX(groupPerson.getLayoutX() +110);
                        }
                        
                        
                        vidaJefe -= 1;
                        if (vidaJefe == 0){
                          winGame();  
                        }
                    }
                   
                    calculateBallSpeed(getStickCollisionZone(circleBall, rectPala));
                })
        );
        animationBall.setCycleCount(Timeline.INDEFINITE);
        animationBall.play();

        //Movimiento de la paleta del jugador
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    stickCurrentSpeed = -8;
                    break;
                case RIGHT:
                    stickCurrentSpeed = 8;
                    break;
                case ENTER:
                    if (vida == 0) {
                        resetGame();
                    }
            }
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            stickCurrentSpeed = 0;
        });
    }

    private int getStickCollisionZone(Circle ball, Rectangle stick) {
        if (Shape.intersect(ball, stick).getBoundsInLocal().isEmpty()) {
            return 0;
        } else {
            double offsetBallStick = ball.getCenterX() - stick.getX();
            if (offsetBallStick < stick.getWidth() * 0.1) {
                return 1;
            } else if (offsetBallStick < stick.getWidth() / 2) {
                return 2;
            } else if (offsetBallStick <= stick.getWidth() / 2 && offsetBallStick < stick.getWidth() * 0.9) {
                return 3;
            } else {
                return 4;
            }
        }
    }

    private void calculateBallSpeed(int collisionZone) {
        switch (collisionZone) {
            case 0:
                break;
            case 1:
                ballCurrentSpeedX = -7;
                ballCurrentSpeedY = -7;
                break;
            case 2:
                ballCurrentSpeedX = -5;
                ballCurrentSpeedY = -7;
                break;
            case 3:
                ballCurrentSpeedX = 5;
                ballCurrentSpeedY = -7;
                break;
            case 4:
                ballCurrentSpeedX = 7;
                ballCurrentSpeedY = -7;
                break;
        }
    }

    private void resetGame() {
        ballCurrentSpeedX = 3;
        ballCurrentSpeedY = 3;
        rectangleObstaculo1.setLayoutX(0);
        rectangleObstaculo1.setLayoutY(0);
        rectangleObstaculo2.setLayoutX(0);
        rectangleObstaculo2.setLayoutY(0);
        rectangleObstaculo3.setLayoutX(0);
        rectangleObstaculo3.setLayoutY(0);
        rectangleObstaculo4.setLayoutX(0);
        rectangleObstaculo4.setLayoutY(0);
        vida = 1;
        vidaJefe = 3;
        groupPerson.setLayoutX(50);
        groupPerson.setLayoutY(50);
       
    }
    
    private void winGame() {
        groupPerson.setLayoutX(-1200);
        groupPerson.setLayoutY(-1200);
        ballCurrentSpeedX = 0;
        ballCurrentSpeedY = 0;
        ballCenterY = 400;
        ballCenterX = 400;
        rectangleObstaculo4.setLayoutX(-1003);
        rectangleObstaculo4.setLayoutY(-200);
        rectangleObstaculo3.setLayoutX(-803);
        rectangleObstaculo3.setLayoutY(-200);
        rectangleObstaculo2.setLayoutX(-603);
        rectangleObstaculo2.setLayoutY(-200);
        rectangleObstaculo1.setLayoutX(-305);
        rectangleObstaculo1.setLayoutY(-200);
    }

    public static void main(String[] args) {
        launch();
    }

}
