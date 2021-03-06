package com.example.gamestate;

/**
 * @author Gareth Rice
 *
 * @version 3/21
 */
public class Piece {

    //player number (0 = red, 1 = blue)
    private int player;

    private String name;

    //if the value is -1, it is a flag
    //if the value is -2, it is a bomb
    private int value;

    public Piece(String name, int val, int player){
        this.name = name;
        this.value = val;

        this.player = player;
    }

    public String toString(){
        String toReturn = "P:" + player + ", N:" + name + ", V:" + value;
        return toReturn;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

}

