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
        // spy can kill marshall
        // 1 and spy 0
        //miner 8
        // bomb 10
        // flag 11
    }

    public GameState(GameState original){


    }

    public int[][] attack(int sCol, int sRow, int fCol, int fRow) {
        // CONDITION CHECKS
        // check player turn
        if (board[sCol][sRow] > 11 && turn == 0 || board[sCol][sRow] < 12 && turn == 1) {
            return null;
        }

        // check out of bounds
        else if(sCol > 9 || sRow > 9 || fCol > 9 || fRow > 9) {
            return null;
        }

        //check friendly pieces
        else if (board[sCol][sRow] > 11 && turn == 0) {
            if (board[fCol][fRow] > 11) {
                return null;
            }
        }
        else if (board[sCol][sRow] < 12 && turn == 1) {
            if (board[fCol][fRow] < 12) {
                return null;
            }
        }

        // ATTACK
        // Bomb and flag cannot attack
        if (board[sCol][sRow]%12 == 10 || board[sCol][sRow]%12 == 11) {
            return null; //invalid
        }
        // Bomb Case with Miner
        if (board[sCol][sRow]%12 == 8 && board[fCol][fRow]%12 == 10) {
            board [sCol][sRow] = board[fCol][fRow];
            board[fCol][fRow] = -1;
        }
        // Bomb case without Miner
        if (board[sCol][sRow]%12 != 8 && board[fCol][fRow]%12 == 10) {
            board[sCol][sRow] = -1;
        }
        // Spy attack Marshall
        if (board[sCol][sRow]%12 == 0 && board[fCol][fRow]%12 == 1) {
            board [sCol][sRow] = board[fCol][fRow];
            board[fCol][fRow] = -1;
        }
        // Marshall attack Spy
        if (board[sCol][sRow]%12 == 1 && board[fCol][fRow]%12 == 0) {
            board [sCol][sRow] = board[fCol][fRow];
            board[fCol][fRow] = -1;
        }

        //spy tries attacking other pieces
        if (board[sCol][sRow]%12 == 0 && board[fCol][fRow]%12 != 1) {
            board[sCol][sRow] = -1;
        }

        // Other pieces try attacking Spy
        if (board[sCol][sRow]%12 != 0 && board[fCol][fRow]%12 == 1) {
            board[sCol][sRow] = board[fCol][fRow];
            board[fCol][fRow] = -1; ;
        }

        // pieces are the same
        if (board[sCol][sRow]%12 == board[fCol][fRow]%12) {
            board[sCol][sRow] = -1;
            board[fCol][fRow] = -1;
        }

        // all movable pieces capture flag
        if (board[fCol][fRow]%12 == 11) {
            board [sCol][sRow] = board[fCol][fRow];
            board[fCol][fRow] = -1;
        }

        // successful attack (negate spy)
        if (board[sCol][sRow]%12 < board[fCol][fRow]%12 && board[sCol][sRow]%12 != 0) {
            board[sCol][sRow] = board[fCol][fRow];
            board[fCol][fRow] = -1;
        }
        // unsuccessful attack (negate spy)
        if (board[sCol][sRow]%12 > board[fCol][fRow]%12 && board[fCol][fRow]%12 != 0) {
            board[sCol][sRow] = -1;
        }
        return board;
    }

}
