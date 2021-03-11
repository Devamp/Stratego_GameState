package com.example.gamestate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                secondInstance.action(5,0,5,1);
                secondInstance.action(5,1,5,2);

                String ogBoard = firstInstance.toString();
                String toString = secondInstance.toString();
                mainText.setText(ogBoard);
                mainText.append(toString);
            }
        });
    }


}