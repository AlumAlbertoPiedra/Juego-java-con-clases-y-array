package com.venegaspiedraalberto.proyecto_breakout;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.LEFT;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * JavaFX App
 */
public class App extends Application {

    //Creación e inicialización de variables
    
    BorderPane root = new BorderPane();
    Bloques bloques = new Bloques(5);
    PalaView palaView= new PalaView(bloques); 
    Personaje personaje= new Personaje();
    BloquesView bloquesView = new BloquesView(bloques);
    BolaView bolaView= new BolaView();
    
    
    
 
    int stickCurrentSpeed = 0;
    int stickPosX = (bloques.SCENE_TAM_X - palaView.tamañoPala) / 2;
    
    int highScore;
    int TEXT_SIZE = 24;
    char tipoBloque;
    ImageView imagenGameOverView;
    

    
    

    @Override
    public void start(Stage stage) {
       

    
        Rectangle zonaContacto1 = new Rectangle(-20,0,90,120);
        zonaContacto1.setVisible(true);

        //Creación de obstaculos

        //Background del juego
        Image img = new Image(getClass().getResourceAsStream("/images/bg_1_1.png"));
        ImageView imgView = new ImageView(img);

        

        // Añadir los objetos a la escena
        Scene scene = new Scene(root, bloques.SCENE_TAM_X, bloques.SCENE_TAM_Y, Color.LIGHTSLATEGREY);
        root.getChildren().add(imgView);
        root.getChildren().add(personaje);
        
        stage.setResizable(false);
        stage.setTitle("BreakoutFX");
        stage.setScene(scene);
        stage.show();
        
        root.setCenter(bloquesView);
        
        
        
        System.out.println();
        System.out.println("");
        System.out.println("");
        bloques.mostrarPuntuacion();
        
        
        //Creación de la puntuación
        HBox paneScores = new HBox();
        paneScores.setTranslateY(20);
        paneScores.setMinWidth(bloques.SCENE_TAM_X);
        paneScores.setAlignment(Pos.CENTER);
        paneScores.setTranslateX(400);
        paneScores.setSpacing(200);
        root.getChildren().add(paneScores);
        
        HBox paneCurrentScore = new HBox();
        paneCurrentScore.setSpacing(10);
        paneScores.getChildren().add(paneCurrentScore);
        
        HBox paneHighScore = new HBox();
        paneHighScore.setSpacing(10);
        paneScores.getChildren().add(paneHighScore);
        
        Text textTitleScore = new Text("Puntuación:");
        textTitleScore.setFont(Font.font(TEXT_SIZE));
        textTitleScore.setFill(Color.BURLYWOOD);
        
        Text textScore = new Text("0");
        textScore.setFont(Font.font(TEXT_SIZE));
        textScore.setFill(Color.BURLYWOOD);
        
        Text textTitleHighScore = new Text("Max.Puntuación:");
        textTitleHighScore.setFont(Font.font(TEXT_SIZE));
        textTitleHighScore.setFill(Color.BURLYWOOD);
        
        Text textHighScore = new Text ("0");
        textHighScore.setFont (Font.font(TEXT_SIZE));
        textHighScore.setFill(Color.BURLYWOOD);
        
        paneCurrentScore.getChildren().add(textTitleScore);
        paneCurrentScore.getChildren().add(textScore);
        paneHighScore.getChildren().add(textTitleHighScore);
        paneHighScore.getChildren().add(textHighScore);
        
      
        

        //Creación de la pala del jugador
       
        
        root.getChildren().add(palaView.rectPala);

        //Creación de la pelota
        root.getChildren().add(bolaView.circleBall);

        Timeline animationBall = new Timeline(
                new KeyFrame(Duration.seconds(0.017), (var ae) -> {
                    
                    personaje.colisionPersonaje(bolaView.circleBall, bloques, bolaView);
                    palaView.rectPala.setWidth(palaView.tamañoPala);
                                                           
                    //Movimiento de la pelota
                    bolaView.circleBall.setCenterY(bolaView.ballCenterY);
                    bolaView.ballCenterY += bolaView.ballCurrentSpeedY;
                    bolaView.circleBall.setCenterX(bolaView.ballCenterX);
                    bolaView.ballCenterX += bolaView.ballCurrentSpeedX;
                    textScore.setText(String.valueOf(bloques.score));
                    
                    //Movimiento de la pala
                    stickPosX += stickCurrentSpeed;
                    palaView.rectPala.setX(stickPosX);
                    //System.out.println(palaView.tamañoPala);
                    
                    //Movimiento del jefe
                    personaje.setLayoutX(personaje.getLayoutX()+ personaje.velocidadJefe);
                    if (personaje.getLayoutX() > (bloques.SCENE_TAM_X -70)) {
                        personaje.velocidadJefe= -personaje.velocidadJefe;
                    }
                    if (personaje.getLayoutX() < 50 ) {
                        personaje.velocidadJefe= -personaje.velocidadJefe;
                    }
                    if (stickPosX < 0) {
                        stickPosX = 0;
                    } else {
                        if (stickPosX > (bloques.SCENE_TAM_X - palaView.tamañoPala)) {
                            stickPosX = (bloques.SCENE_TAM_X - palaView.tamañoPala);
                        }
                    }
                    if (bolaView.ballCenterX >= bloques.SCENE_TAM_X-10) {
                        bolaView.ballCurrentSpeedX = -bolaView.ballCurrentSpeedX;
                    }
                    if (bolaView.ballCenterX <= 0) {
                        bolaView.ballCurrentSpeedX = -bolaView.ballCurrentSpeedX;
                    }
                    if (bolaView.ballCenterY >= bloques.SCENE_TAM_Y) {
                        bolaView.ballCurrentSpeedX = 0;
                        bolaView.ballCurrentSpeedY = 0;
                        bolaView.ballCenterY = 400;
                        bolaView.ballCenterX = 400;
                        bloques.vida = 0;
                        if(bloques.score > highScore){
                            highScore= bloques.score;
                            textHighScore.setText(String.valueOf(highScore));
                        }
                        bloques.score = 0;
                        textScore.setText(String.valueOf(bloques.score));
                        var imagenGameOver = new Image(getClass().getResourceAsStream("/images/game-over.png"));
                        imagenGameOverView = new ImageView(imagenGameOver);
                        root.getChildren().add(imagenGameOverView);
                        imagenGameOverView.setLayoutX(0);
                        imagenGameOverView.setLayoutY(-100);
                    }
                    if (bolaView.ballCenterY <= 0) {
                        bolaView.ballCurrentSpeedY = -bolaView.ballCurrentSpeedY;
                    }
                    Shape shapeColision = Shape.intersect(bolaView.circleBall, palaView.rectPala);
                    boolean colisionVacia = shapeColision.getBoundsInLocal().isEmpty();
                    if (colisionVacia == false) {
                        bolaView.ballCurrentSpeedY = -bolaView.ballCurrentSpeedY;
                    }
                    
                    //System.out.println("Ejemplo reinicio");
                    /*Shape shapeColision5 = Shape.intersect(bloquesView.circleBall, zonaContacto1);
                    boolean colisionVacia5 = shapeColision5.getBoundsInLocal().isEmpty();
                    if (colisionVacia5 == false) {
                        bloques.ballCurrentSpeedY = -bloques.ballCurrentSpeedY;
                        /*ballCenterY = (ballCenterY + (ballCurrentSpeedY*2));
                        circleBall.setCenterY(ballCenterY);
                        if (personaje.getLayoutX()>= 540){
                            personaje.setLayoutX(personaje.getLayoutX() -200);
                            personaje.velocidadJefe = -personaje.velocidadJefe;
                        } else if (personaje.getLayoutY() <= 260){
                            personaje.setLayoutX(personaje.getLayoutX() +200);
                            personaje.velocidadJefe = -personaje.velocidadJefe;
                        }
                        bloques.score += 50;
                        textScore.setText(String.valueOf(bloques.score));
                        bloques.vidaJefe -= 1;
                        if (bloques.vidaJefe == 0){
                          winGame(); 
                          bloques.vida = 0;
                        }
                    }*/
                    bloquesView.colisionObjeto(bolaView, palaView);
                   
                    calculateBallSpeed(getStickCollisionZone(bolaView.circleBall, palaView.rectPala));
                })
        );
        animationBall.setCycleCount(Timeline.INDEFINITE);
        animationBall.play();

        //Movimiento de la paleta del jugador
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case LEFT:
                    stickCurrentSpeed = -12;
                    break;
                case RIGHT:
                    stickCurrentSpeed = 12;
                    break;
                case ENTER:
                    if (bloques.vida == 0) {
                        bloquesView.resetGame();
                        bloques = new Bloques(5);
                        bloquesView.actualizarBloque(bloques);
                        personaje.resetGame(); 
                        //bloques.resetGame();
                        bolaView.ballCurrentSpeedX = -5;
                        bolaView.ballCurrentSpeedY = -5;
                        palaView.tamañoPala = 80;
                        bloques.vida = 1;
                        bloques.vidaJefe = 3;
                        root.getChildren().remove(imagenGameOverView);
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
            if (offsetBallStick < stick.getWidth() * 0.2) {
                return 1;
            } else if (offsetBallStick < stick.getWidth() / 2) {
                return 2;
            } else if (offsetBallStick <= stick.getWidth() / 2 && offsetBallStick < stick.getWidth() * 0.8) {
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
                if (bolaView.ballCurrentSpeedX >0){
                    bolaView.ballCurrentSpeedX = 9;
                    
                } else if (bolaView.ballCurrentSpeedX <0){
                    bolaView.ballCurrentSpeedX = -9;
                }
                bolaView.ballCurrentSpeedY = -7;
                break;
            case 2:
                if (bolaView.ballCurrentSpeedX >0){
                    bolaView.ballCurrentSpeedX = 5;
                    
                } else if (bolaView.ballCurrentSpeedX <0){
                    bolaView.ballCurrentSpeedX = -5;
                }
                bolaView.ballCurrentSpeedY = -9;
                break;
            case 3:
                if (bolaView.ballCurrentSpeedX >0){
                    bolaView.ballCurrentSpeedX = 5;
                    
                } else if (bolaView.ballCurrentSpeedX <0){
                    bolaView.ballCurrentSpeedX = -5;
                }
                bolaView.ballCurrentSpeedY = -9;
                break;
            case 4:
                if (bolaView.ballCurrentSpeedX >0){
                    bolaView.ballCurrentSpeedX = 9;
                    
                } else if (bolaView.ballCurrentSpeedX <0){
                    bolaView.ballCurrentSpeedX = -9;
                };
                bolaView.ballCurrentSpeedY = -7;
                break;
        }
    }

    //Reiniciar el juego tras acabar partida o perder
    /*private void resetGame() {
        bloques.ballCurrentSpeedX = 3;
        bloques.ballCurrentSpeedY = 3;
        bloquesView.vida = 1;
        bloquesView.vidaJefe = 3;
        personaje.setLayoutX(50);
        personaje.setLayoutY(0); 
    }*/
    
    //Eliminas todos los bloques
    /*private void winGame() {
        personaje.setLayoutX(-1200);
        personaje.setLayoutY(-1200);
        bolaView.ballCurrentSpeedX = 0;
        bolaView.ballCurrentSpeedY = 0;
        bolaView.ballCenterY = 400;
        bolaView.ballCenterX = 400;
    }*/

    public static void main(String[] args) {
        launch();
    }

}