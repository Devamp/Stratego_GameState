package com.example.gamestate;

/**
 * @author Gareth Rice
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
        String toReturn = "P:" + player + ", N:" + name + ", V:" + value;
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

        //add if conditional for center lakes

        //if the place where the piece is moving is null, it is empty
        //and can't move there
        if(toPlace == null){
            toReturn = true;
        }
        //if a piece tries to move onto a space that is occupied by a friendly piece, can't move
        else if(this.getPlayer() == toPlace.getPlayer()){
            toReturn = false;
        }
        else if((this.getPlayer()+1)%2 == toPlace.getPlayer()){
            this.attack(toPlace);
        }
        else if(toPlace.getPlayer() == -1){
            toReturn = false;
        }
        else{
            toReturn = true;
        }

        return toReturn;
    }

    public boolean attack(Piece toAttack){

        return true;
    }

}

