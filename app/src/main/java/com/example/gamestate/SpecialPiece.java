package com.example.gamestate;

/**
 * @author Gareth Rice
 * @author Caden Deutscher
 * @author Hewlett De Lara
 * @author Devam Patel
 *
 * @version 3/21
 */


public class SpecialPiece extends Piece {


    public SpecialPiece(String name, int value, int player){
        super(name, value, player);
    }

    @Override
    public boolean move(Piece toPlace){
        return false;
    }

    @Override
    public boolean attack( Piece toAttack){
        return false;
    }


}
