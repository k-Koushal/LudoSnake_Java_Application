package com.example.ludosnake;

import javafx.scene.paint.Paint;
import javafx.util.Pair;

import java.util.ArrayList;

public class GameBoard {
    static int  tileSize = 40;
    static int height = 10;
    static int width = 10;
    static ArrayList<Pair<Integer,Integer>>positionCoordinates;
    static ArrayList<Integer>snakeLadderPosition;

    public GameBoard(){
        populateSnakeLadderPosition();
        populatePositionCoordinate();
    }

    public int getXValue(int piecePosition){
        if(piecePosition<=100 && piecePosition>=1){
            return positionCoordinates.get(piecePosition).getKey();
        }
        else return positionCoordinates.get(1).getKey();
    }

    public int getYValue(int piecePosition){
        if(piecePosition<=100 && piecePosition>=1){
            return positionCoordinates.get(piecePosition).getValue();
        }
        else return positionCoordinates.get(1).getValue();
    }

    public int playerPositionAtSnakeOrLadder(int piecePosition){
        if(piecePosition != snakeLadderPosition.get(piecePosition)){
            return snakeLadderPosition.get(piecePosition);
        }
        else return -1;
    }

   static private void populatePositionCoordinate(){
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<Integer,Integer>(20,380));
        for (int i = height-1; i>=0 ; i--) {
            for (int j = width-1; j>=0 ; j--) {
                int xTilePos, yTilePos, pos;
                if(i%2 != 0){
                    xTilePos = tileSize*width - (tileSize/2 + j*tileSize);
                }
                else{
                    xTilePos = tileSize/2 + j*tileSize;
                }
                yTilePos = tileSize/2 + i*tileSize;
                positionCoordinates.add(new Pair<>(xTilePos,yTilePos));
            }
        }

//        for (int i = 0; i <positionCoordinates.size(); i++) {
//            System.out.println(i + " x: " + positionCoordinates.get(i).getKey() + " y: " + positionCoordinates.get(i).getValue());
//        }
    }

    private void populateSnakeLadderPosition(){
       snakeLadderPosition = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            snakeLadderPosition.add(i);
        }
        snakeLadderPosition.set(2,23);
        snakeLadderPosition.set(8,34);
        snakeLadderPosition.set(20,77);
        snakeLadderPosition.set(29,9);
        snakeLadderPosition.set(32,68);
        snakeLadderPosition.set(38,15);
        snakeLadderPosition.set(41,79);
        snakeLadderPosition.set(47,5);
        snakeLadderPosition.set(53,33);
        snakeLadderPosition.set(62,37);
        snakeLadderPosition.set(74,88);
        snakeLadderPosition.set(82,100);
        snakeLadderPosition.set(88,95);
        snakeLadderPosition.set(86,54);
        snakeLadderPosition.set(92,70);
        snakeLadderPosition.set(97,25);
    }


//    public static void main(String[] args) {
//        populatePositionCoordinate();
//    }

}
