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
                secondInstance.action(6,0,5,0);
                mainText.append("Player 1 tries to move piece from (6,0) to (5,0)\n");
                mainText.append("\n");
                mainText.append(secondInstance.printBoard());
                mainText.append("\n");

                secondInstance.action(5,0,5,1);
                mainText.append("Player 1 tries to move piece from (5,0) to (5,1)\n");
                mainText.append("\n");
                mainText.append(secondInstance.printBoard());
                mainText.append("\n");

                secondInstance.action(5,1,5,2);
                mainText.append("Player 1 tries to move piece from (5,1) to (5,2)\n");
                mainText.append("But fails to do so because it moving onto a lake is an INVALID move.\n\n");
                mainText.append(secondInstance.printBoard());
                mainText.append("\n");

                mainText.append("\n");
                mainText.append("[SECOND INSTANCE]");
                mainText.append("\n");
                mainText.append(secondInstance.toString());

                mainText.append("\n");
                mainText.append("[FOURTH INSTANCE]");
                mainText.append("\n");
                mainText.append(fourthInstance.toString());


            }
        });
    }


}