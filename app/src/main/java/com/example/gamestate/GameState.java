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
    //Board: -1 = empty space, -2 = impassable space (lake), -3 = invisible character (other army)
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

        //initialize board array with -1 in all empty slots
        board = new int[10][10];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j <board[i].length; i++){
                //Make the lakes in the center of the board equal -2. Spaces with -2 can't
                //be crossed/moved into
                if((i == 4 || i == 5) && (j == 2 || j == 3 || j == 6 || j == 7)){
                    board[i][j] = -2;
                }else{
                    board[i][j] = -1;
                }
            }
        }

        timer = 0;
        phase = 0;

    }

    /**
     * copy constructor displays only the half of the pieces that belong to the given character
     *
     * @param original
     */
    public GameState(GameState original){
        //make new array for the board
        board = new int[10][10];

        //set the new board state equal to original board state
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                if(board[i][j] > 11 && turn == 0){ //If it's blue's piece on red's turn, value is -3
                    board[i][j] = -3;
                }else if(board[i][j] < 12 && turn == 1){ //If it's red's piece on blue's turn,
                                                         // value is -3
                    board[i][j] = -3;
                }else{ //print the rest of the board state
                    board[i][j] = original.board[i][j];
                }
            }
        }

        //copy over information (not deep copied)
        turn = original.turn;
        timer = original.timer;
        phase = original.phase;
    }
}
