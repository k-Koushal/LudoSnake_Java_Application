package com.example.ludosnake;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class LudoSnake extends Application {

    Group tileGroup = new Group();

    public static final int tileSize = 40;
    int height = 10;
    int width = 10;

    int yLine = 430;
    int xLine = 40;

    int diceValue=1;

    Label randResult;
    Button gameButton;

    Player playerOne, playerTwo;
    boolean gameStart = false, playerOneTurn=true, playerTwoTurn=false;
    public Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize,height*tileSize+80);
        root.getChildren().addAll(tileGroup);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tileSize,tileSize);
                tile.setTranslateX(j*tileSize);
                tile.setTranslateY(i*tileSize);
                tileGroup.getChildren().addAll(tile);
            }

        }

        playerOne = new Player(tileSize, Color.WHITE);
        playerTwo = new Player(tileSize-10, Color.BLACK);


        randResult = new Label("Game not started");
        randResult.setTranslateX(150);
        randResult.setTranslateY(410);

        Button player1Button = new Button("Player One");
        player1Button.setTranslateX(10);
        player1Button.setTranslateY(yLine);
        player1Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerOneTurn){
                        getDiceValue();
                        randResult.setText( "PlayerOne - " + String.valueOf(diceValue));
                        //move the player
                        playerOne.movePlayer(diceValue);
                        playerOneTurn = false;
                        playerTwoTurn = true;
//                        playerOne.playerAtSnakeOrLadder();
                        gameOver();

                    }
                }
            }
        });

        Button player2Button = new Button("Player Two");
        player2Button.setTranslateX(300);
        player2Button.setTranslateY(yLine);
        player2Button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gameStart){
                    if(playerTwoTurn){
                        getDiceValue();
                        randResult.setText("PlayerTwo - " + String.valueOf(diceValue));
                        //move the player
                        playerTwo.movePlayer(diceValue);
                        playerOneTurn = true;
                        playerTwoTurn = false;
//                        playerTwo.playerAtSnakeOrLadder();
                        gameOver();
                    }
                }
            }
        });

        gameButton = new Button("Start Game");
        gameButton.setTranslateX(150);
        gameButton.setTranslateY(yLine);
        gameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                randResult.setText("Started");
                gameStart = true;
                gameButton.setText("Game Going");

            }
        });


         //img = new Image("C:\\Users\\angaddubey\\IdeaProjects\\LudoSnake\\src\\snakeLadderBoardNO.jpg");
        Image img = new Image("C:\\Users\\Koushal_K\\Desktop\\SnakeLudo-main\\src\\snakeLadderBoard.jpg");
        ImageView boardImage = new ImageView();
        boardImage.setImage(img);
        boardImage.setFitHeight(tileSize*height);
        boardImage.setFitWidth(tileSize*width);


        tileGroup.getChildren().addAll(boardImage, randResult, playerOne.getGamePiece(), playerTwo.getGamePiece(), player1Button,player2Button,gameButton);

        return root;
    }

    void gameOver(){
        if(playerOne.getWinningStatus()==true){
            randResult.setText("Player One Won");
            gameButton.setText("Start Again");
            gameStart = false;
        }
        else if(playerTwo.getWinningStatus()==true){
            randResult.setText("Player Two Won");
            gameButton.setText("Start Again");
            gameStart = false;
        }
    }

    private void getDiceValue(){
        diceValue = (int)(Math.random()*6+1);
    }

    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(LuodSnake.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(createContent());
        stage.setTitle("Ludo Snake");
        stage.setScene(scene);
        stage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                long currentTime =  System.currentTimeMillis();
                long dt = currentTime -  Player.lastMovementTime;
//                System.out.println(currentTime + "  " + Player.lastMovementTime + "   " + dt);

                if(dt>1e3){
                    Player.lastMovementTime = currentTime;
//                    System.out.println(currentTime + "  " + Player.lastMovementTime);
//
                    playerOne.playerAtSnakeOrLadder();
                    playerTwo.playerAtSnakeOrLadder();
                }
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch();
    }
}