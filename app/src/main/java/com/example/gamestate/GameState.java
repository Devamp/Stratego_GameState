package com.example.gamestate;

/**
 * @author Gareth Rice
 *
 * @version 3/21
 *
 * Notes:
 *
 */

public class GameState {
    //Number of characters of each color
    private int[] blueCharacter;
    private int[] redCharacter;
    //turn indicator
    private int turn;
    //Board
    private int[][] board;
    //Game Timer
    private float timer;
    //Phase Indicator
    private int phase;
    /**
     * ctor
     *
     */
    public GameState(){
        redCharacter = new int[12];
        blueCharacter = new int[12];
        //Initialize red values
        redCharacter[0] = 1;
        redCharacter[1] = 1;
        redCharacter[2] = 1;
        redCharacter[3] = 2;
        redCharacter[4] = 3;
        redCharacter[5] = 4;
        redCharacter[6] = 4;
        redCharacter[7] = 4;
        redCharacter[8] = 5;
        redCharacter[9] = 8;
        redCharacter[10] = 6;
        redCharacter[11] = 1;
        //Initialize blue values
        blueCharacter[0] = 1;
        blueCharacter[1] = 1;
        blueCharacter[2] = 1;
        blueCharacter[3] = 2;
        blueCharacter[4] = 3;
        blueCharacter[5] = 4;
        blueCharacter[6] = 4;
        blueCharacter[7] = 4;
        blueCharacter[8] = 5;
        blueCharacter[9] = 8;
        blueCharacter[10] = 6;
        blueCharacter[11] = 1;
        //initialize other values
        turn = 0;
        board = new int[10][10];
        timer = 0;
        phase = 0;

    }

    public GameState(GameState original){


    }
}
