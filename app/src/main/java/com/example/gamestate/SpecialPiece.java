package com.example.gamestate;



public class SpecialPiece extends Piece {


    public SpecialPiece(String name, int value, int player){
        super(name, value, player);
    }

    @Override
    public boolean move(Piece toPlace){
        return false;
    }

    @Override
    public boolean attack(Piece toAttack){
        return false;
    }


}
