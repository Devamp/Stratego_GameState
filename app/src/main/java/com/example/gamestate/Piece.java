package com.example.gamestate;

/**
 * @author Gareth Rice
 * @author Caden Deutscher
 *
 * @version 3/21
 */
public class Piece {

    //player number (0 = red, 1 = blue)
    //Lake = -1
    private int player;

    private String name;
    //if the value is -1, it is a flag
    //if the value is -2, it is a bomb
    //if the value is 10, it is the spy
    private int value;

    private boolean isVisible = true;

    public Piece(String name, int val, int player){
        this.name = name;
        this.value = val;

        this.player = player;
    }

    public String toString(){
        String toReturn;
        if(isVisible){
            toReturn = "P:" + player + ", N:" + name + ", V:" + value;
        }else{
            toReturn = "INV";
        }

        return toReturn;
    }

    public void setVisible(boolean visible){
        isVisible = visible;
    }

    public boolean getVisible(){
        return isVisible;
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

    public void setPlayer(int player){
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }

    public boolean move(Piece toPlace){
        boolean toReturn = true;
        //if a piece tries to move onto a space that is occupied by a friendly piece, can't move
        if(this.getPlayer() == toPlace.getPlayer()){
            toReturn = false;
        }
        //If lake cannot move there
        else if(toPlace.getPlayer() == -1){
            toReturn = false;
        }
        return toReturn;
    }

    public boolean attack(Piece toAttack){
        boolean success = true;
        if(this.getValue() == 8 && toAttack.getValue() == -2){ //miner attacks bomb
        }
        else if(toAttack.getValue() == -2){
            success =false;
        }
        else if(this.getValue() == 10  && toAttack.getValue() == 1){

        }
        else if(this.getValue() < toAttack.getValue()){
        }
        else if(this.getValue() > toAttack.getValue()){
            success = false;
        }
        return success;
    }

}

