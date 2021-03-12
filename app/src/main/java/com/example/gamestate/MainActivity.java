package com.example.gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.nio.charset.MalformedInputException;

/**
 * @author Gareth Rice
 * @author Caden Deutscher
 * @author Hewlett De Lara
 * @author Devam Patel
 *
 * @version 3/21
 *
 * Notes:
 *
 * ToDo:
 *
 */

public class MainActivity extends AppCompatActivity {

    /**
     * onCreate: setup the project
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText mainText = (EditText) findViewById(R.id.mainText);

        //Create a test piece
        Piece spy = new Piece("Spy", 0, 1);

        //Setup runTestBtn and it's onClick method
        Button runTestBtn = (Button) findViewById(R.id.runTestButton);
        runTestBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //clear the edit text
                mainText.setText("");

                GameState firstInstance = new GameState();

                GameState secondInstance = new GameState(firstInstance);

                GameState thirdInstance = new GameState();

                GameState fourthInstance = new GameState(thirdInstance);

                //call toString
                mainText.append(firstInstance.movePrint(6,0,5,0));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");

                mainText.append(firstInstance.movePrint(5,0,5,1));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");

                mainText.append(firstInstance.movePrint(5,1,5,2));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");
                //Diagonal test should never work
                mainText.append(firstInstance.movePrint(6,9,5,8));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");
                //Multi-space move test should work for scout
                mainText.append(firstInstance.movePrint(6,8,4,8));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");
                //take test(should work unless bomb or flag)
                mainText.append(firstInstance.movePrint(6,5,5,5));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");
                mainText.append(firstInstance.movePrint(5,5,4,5));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");
                mainText.append(firstInstance.movePrint(4,5,3,5));
                mainText.append("\n\n");
                mainText.append(firstInstance.printBoard());
                mainText.append("\n");

                //print second instance
                mainText.append("\n");
                mainText.append("[SECOND INSTANCE]");
                mainText.append("\n");
                mainText.append(secondInstance.toString());
                mainText.append("\n");
                mainText.append(secondInstance.printBoard());

                //print fourth instance
                mainText.append("\n");
                mainText.append("\n");
                mainText.append("[FOURTH INSTANCE]");
                mainText.append("\n");
                mainText.append(fourthInstance.toString());
                mainText.append("\n");
                mainText.append(fourthInstance.printBoard());
                mainText.append("\n");

                //testing endTurn method
                mainText.append("******* Player 1 ends their turn. Re-print the game state *******\n");
                mainText.append("\n");
                mainText.append("Before player 1 ends turn:\n");
                mainText.append("\n");
                mainText.append(secondInstance.printBoard());
                mainText.append("\n");
                secondInstance.endTurn(secondInstance);
                mainText.append("After player 1 has ended their turn: \n");
                mainText.append("\n");
                GameState tester = new GameState(secondInstance);
                mainText.append(tester.printBoard());
                mainText.append("\n");
                mainText.append(tester.toString());


            }
        });
    }


}