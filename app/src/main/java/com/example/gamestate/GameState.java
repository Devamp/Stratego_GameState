package com.example.gamestate;

import java.util.ArrayList;

/**
 * @author Gareth Rice
 * @author Devam Patel
 *
 * @version 3/21
 *
 * Notes:
 *
 */

public class GameState {
    //Number of characters of each color
    //0 - spy, 10 - bomb, 11 - flag
    private int[] blueCharacter;
    private int[] redCharacter;
    //turn indicator // red = 0, blue = 1
    private int turn;
    //Board: -1 = empty space, -2 = impassable space (lake), -3 = invisible character (other army)
    private Piece[][] board;
    //Game Timer
    private float timer;
    //Phase Indicator
    private int phase;

    ArrayList<Piece> redBench = new ArrayList<Piece>();
    ArrayList<Piece> blueBench = new ArrayList<Piece>();

    /**
     * ctor
     *
     */
    public GameState(){
        //character array for each color
        redCharacter = new int[12];
        blueCharacter = new int[12];

        //Initialize red values
        redCharacter[0] = 1; //flag
        redCharacter[1] = 1; //marshall
        redCharacter[2] = 1; //general
        redCharacter[3] = 2; //colonel
        redCharacter[4] = 3; //major
        redCharacter[5] = 4; //captain
        redCharacter[6] = 4; //Lieutenant
        redCharacter[7] = 4; //sergeant
        redCharacter[8] = 5; //miner
        redCharacter[9] = 8; //scout
        redCharacter[10] = 6; //bomb
        redCharacter[11] = 1; //spy

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
        board = new Piece[10][10];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                //Make the lakes in the center of the board equal -2. Spaces with -2 can't
                //be crossed/moved into
                if((i == 4 || i == 5) && (j == 2 || j == 3 || j == 6 || j == 7)){
                    board[i][j] = new Piece("Lake",-1,-1);
                }else{
                    board[i][j] = null;
                }
            }
        }

        timer = 0;
        phase = 0; // 0 = placement, 1 = game, 2 = end play

    }

    /**
     * copy constructor displays only the half of the pieces that belong to the given character
     *
     * @param original
     */
    public GameState(GameState original){

        //make new array for the board
        board = new Piece[10][10];

        //set the new board state equal to original board state
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){

                //If it's blue's turn and the piece is red, make red piece invisible
                if(board[i][j].getPlayer() == 1 && turn == 0){
                    board[i][j].setVisible(false);
                }
                //if it's red's turn and the piece is blue, make blue piece invisible
                else if(board[i][j].getPlayer() == 0 && turn == 1){
                    board[i][j].setVisible(false);
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

    /**
     * Add instantiated pieces to array lists for both red and blue
     *
     * @param player
     * @return
     */
    public boolean instancePieces(int player){
        ArrayList<Piece> assign;
        int[] numberPieces;
        String name;

        if(player == 0){
            assign = redBench;
            numberPieces = redCharacter;
        }else{
            assign = blueBench;
            numberPieces = blueCharacter;
        }

        //iterate over the 12 types of pieces
        for(int i = 0; i < 12; i++){
            //need to instance all of the pieces
            name = setName(i);

            //go over the number of each particular piece and add an instanced piece to
            //an array list
            while(numberPieces[i] > 0){
                //if the piece is flag or bomb, create special piece
                if(i == 0 || i == 10){
                    assign.add(new SpecialPiece(name, i, player));
                }else{
                    //we can add conditions to add spy, miner, and scout special pieces
                    assign.add(new Piece(name, i, player));
                }

                numberPieces[i]--;
            }
        }

        return true;
    }

    /**
     * setName sets the name of the piece about to be instantiated
     *
     * @param whichName
     * @return
     */
    public String setName(int whichName){
        String returnName;

        //probably a poor way to get the name? Can use hashtable?
        switch(whichName){
            case 0:
                returnName = "Flag";
                break;
            case 1:
                returnName = "Marshall";
                break;
            case 2:
                returnName = "General";
                break;
            case 3:
                returnName = "Colonel";
                break;
            case 4:
                returnName = "Major";
                break;
            case 5:
                returnName = "Captain";
                break;
            case 6:
                returnName = "Lieutenant";
                break;
            case 7:
                returnName = "Sergeant";
                break;
            case 8:
                returnName = "Miner";
                break;
            case 9:
                returnName = "scout";
                break;
            case 10:
                returnName = "bomb";
                break;
            case 11:
                returnName = "spy";
                break;
            default:
                returnName = "";
                break;
        }

        return returnName;
    }

    /**
     * place: Place the pieces from their respective arrayLists
     *
     * @return
     */
    public boolean place(int player){
        int start;
        int randomIndex;
        ArrayList<Piece> currentArmy;

        //place red pieces on bottom of board
        if(player == 0){
            start = 0;
            currentArmy = redBench;
        } else{
            start = 6;
            currentArmy = blueBench;
        }

        //iterate over the first 4, or last 4 rows depending on blue or red player
        for(int i = start; i < start + 4; i++){
            for(int j = 0; j < board[i].length; j++){
                //set the index i j to a random piece from specific players arrayList of
                //instantiated pieces
                randomIndex = (int) (Math.random() * currentArmy.size());

                //set that board index to the random index
                board[i][j] = currentArmy.get(randomIndex);
                //once placed from bench it should be removed as its now on the board
                currentArmy.remove(randomIndex);
            }
        }

        return true;
    }

    public boolean action(int fromX, int fromY, int toX, int toY) {
        boolean success = false;
        //check to make sure movement is not greater than 1 and not diagnal
        if(Math.abs(fromX - toX) > 1 ||  Math.abs(fromY- toY) > 1 || Math.abs(fromY- toY) >= 1 && Math.abs(fromX - toX) >= 1 ){
            return false;
        }
        else {
            if (board[fromX][fromY].move(board[toX][toY])) {
                //Attack
                if (board[fromX][fromY].getPlayer() != board[toX][toY].getPlayer() && board[toX][toY].getPlayer() != -1) {
                    //If piece attacking is successful
                    if (board[fromX][fromY].attack(board[toX][toY])) {
                        board[toX][toY] = new Piece(board[fromX][fromY].getName(), board[fromX][fromY].getValue(), board[fromX][fromY].getPlayer());
                        board[fromX][fromY] = null;
                        success = true;
                    }
                    //If piece defending is successful
                    else {
                        board[fromX][fromY] = null;
                    }
                    success = true;
                }
                //Move
                else {
                    board[toX][toY] = new Piece(board[fromX][fromY].getName(), board[fromX][fromY].getValue(), board[fromX][fromY].getPlayer());
                    board[fromX][fromY] = null;
                    success = true;
                }
            }
        }
            return success;
    }




/*
    public int[][] move(int sCol, int sRow, int fCol, int fRow){
        //Check initial spot

        //Check if is movable
        if(board[sCol][sRow]%12 > 9  || board[sCol][sRow]%12 <0 ){
            //INVALID
            return null;
        }
        //Check if is your piece
        else if(board[sCol][sRow] > 11 && turn == 0 || board[sCol][sRow] < 12 && turn == 1){
            //INVALID
            return null;
        }
        //Check if on board
        else if(sCol > 9 || sRow > 9 || fCol > 9 || fRow > 9 || sCol < 0 || sRow < 0 || fCol < 0 || fRow < 0){
            //INVALID
            return null;
        }

        //Check final spot

        //if not scout
        if(board[sCol][sRow]%12  != 9){
            //Check to make sure only one spot away
            if(Math.abs(sCol - fCol) != 1 || Math.abs(sRow - fRow) != 1){
                //INVALID
                return null;
            }
            //Check to make sure not diagonal
            else if(sCol != fCol && sRow != fRow){
                //INVALID
                return null;
            }
            //Check if empty
            else if(board[fCol][fRow] != -1 ){
                //INVALID
                return null;
            }
        }
        //if scout
        else{
            //Check to make sure not diagonal
            if(sCol != fCol && sRow != fRow){
                //INVALID
                return null;
            }
            //Check if empty
            else if(board[fCol][fRow] != -1 ){
                //INVALID
                return null;
            }

            //Check in between spots
            if(fCol > sCol){
                for(int i = sCol + 1; i <= fCol; i++) {
                    if(board[i][sRow] != -1){
                        //Invalid
                        return null;
                    }
                }

            }
            else if(fCol < sCol){
                for(int i = sCol -1; i >= fCol; i--){
                    if(board[i][sRow] != -1){
                        //Invalid
                        return null;
                    }

                }

            }
            else if(fRow < sRow){
                for(int i = sRow - 1; i >= fRow; i--){
                    if(board[sCol][i] != -1){
                        //Invalid
                        return null;
                    }

                }

            }
            else if( fRow > sRow){
                for(int i = sRow + 1; i <= fRow; i++){
                    if(board[sCol][i] != -1){
                        //Invalid
                        return null;
                    }
                }
            }
        }

        //Make Move
        int temp =  board[sCol][sRow];
        board[sCol][sRow] = -1;
        board[fCol][fRow] = temp;

        return board;
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
*/

    //toString method print current state of the game as a String
    @Override
    public String toString(){
        String finalMessage;

        //print whos turn
        String player;
        if(turn == 0){
            player = "Red";
        } else {
            player = "Blue";
        }

        //print game phase
        String gamePhase;
        if(phase == 0){
            gamePhase = "Placement";
        } else if (phase == 1){
            gamePhase = "Play Phase";
        } else if(phase == 2){
            gamePhase = "End Game";
        } else {
            gamePhase = ""; //error
        }

        //print board
        for(int row = 0; row < board.length; row++){
            for(int col = 0; col < board[row].length; col++){
                System.out.print("["+ board[row][col] + "]" + "\t");
            }
            System.out.println();
        }

        //print blueCharacter count
        finalMessage =
                "********** GAME STATE INFO ********** " +
                "\n Player Turn: " + player +
                "\n Game Time: " + timer +
                "\n Current Game Phase: " + gamePhase +
                "\n\n" + "Red Player Characters: " +
                "\n" + "Flag: " + redCharacter[0] +
                "\n" + "Bomb: " + redCharacter[10] +
                "\n" + "Spy: " + redCharacter[11] +
                "\n" + "Scout: " + redCharacter[9] +
                "\n" + "Miner: " + redCharacter[8] +
                "\n" + "Sergeant: " + redCharacter[7] +
                "\n" + "Lieutenant: " + redCharacter[6] +
                "\n" + "Captain: " + redCharacter[5] +
                "\n" + "Major: " + redCharacter[4] +
                "\n" + "Colonel: " + redCharacter[3] +
                "\n" + "General: " + redCharacter[2] +
                "\n" + "Marshall: " + redCharacter[1] +

                "\n\n" + "Blue Player Characters: " +
                "\n" + "Flag: " + blueCharacter[0] +
                "\n" + "Bomb: " + blueCharacter[10] +
                "\n" + "Spy: " + blueCharacter[11] +
                "\n" + "Scout: " + blueCharacter[9] +
                "\n" + "Miner: " + blueCharacter[8] +
                "\n" + "Sergeant: " + blueCharacter[7] +
                "\n" + "Lieutenant: " + blueCharacter[6] +
                "\n" + "Captain: " + blueCharacter[5] +
                "\n" + "Major: " + blueCharacter[4] +
                "\n" + "Colonel: " + blueCharacter[3] +
                "\n" + "General: " + blueCharacter[2] +
                "\n" + "Marshall: " + blueCharacter[1] +
                "\n\n";

        return finalMessage;
    }
}
