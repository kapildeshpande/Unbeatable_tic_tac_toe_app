package com.example.kapil.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private GameLogic gameLogic;
    LinearLayout linearLayout;
    TextView label;

    public void imageTapped (View view) {
        ImageView image = (ImageView) view;
        label = findViewById(R.id.label);
        String pos = image.getTag().toString();

        //if button is already tapped
        if (! gameLogic.humanMove(Integer.valueOf(pos)))
            return;
        image.setImageResource(R.drawable.cross);

        //return 1 for win 2 for draw otherwise 0
        int  win = gameLogic.logic();

        if (win == 1) {
            //Never gona happen :)
            label.setText("You Win!!");
            gameLogic.gameOver();
            return;
        } else if (win == 2) {
            label.setText("Game Draws!!");
            return;
        }

        aiturn();

    }

    public void aiturn () {
        int move = gameLogic.aiMove();//return best move between 0 to 8

        int rowNum = move/3;
        int colNum = move%3;

        if (rowNum == 0) {
            linearLayout = findViewById(R.id.line1);
        } else if (rowNum == 1) {
            linearLayout = findViewById(R.id.line2);
        } else if (rowNum == 2) {
            linearLayout = findViewById(R.id.line3);
        }
        ((ImageView) linearLayout.getChildAt(colNum)).setImageResource(R.drawable.circle);
        label = findViewById(R.id.label);

        //return 1 for win 2 for draw otherwise 0
        int win = gameLogic.logic();
        if (win == 1) {
            label.setText("AI wins!!");
            gameLogic.gameOver();
        } else if (win == 2) {
            label.setText("Game Draws!!");
        }
    }

    //if play again button is pressed
    public void Reset (View view) {
        gameLogic.reset();
        TextView label = findViewById(R.id.label);
        label.setText("");

        int ids[] = {R.id.line1,R.id.line2,R.id.line3};

        for (int k=0;k<ids.length;k++) {
            LinearLayout linearLayout = findViewById(ids[k]);
            for (int i = 0; i < linearLayout.getChildCount(); i++) {
                ((ImageView) linearLayout.getChildAt(i)).setImageResource(R.mipmap.ic_launcher);
            }
        }
        aiturn();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameLogic = new GameLogic();
        aiturn();
    }
}
