package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private TextView tv_header;
    private TextView show_winner;

    private Button btn0;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;

    private Button play_again;

    //  Player O: 0
    //  Player X: 1
    int active_player = 0;

    // Possible winning positions
    int[][] winning_pos = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}
    };

    // To avoid changing of 'O' and 'X' multiple times
    int[] filled_pos = {-1, -1, -1, -1, -1, -1, -1, -1, -1};

    // To check if the game is over or not
    boolean game_over = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view){
        Button clickedBtn = findViewById(view.getId());
        show_winner = findViewById(R.id.result);

        // get Tag for the button clicked
        int clickedTag = Integer.parseInt(clickedBtn.getTag().toString());


        // Check if the game is over or not
        if(!game_over){
            // check if the btn is already marked with 'O' or 'X'
            if (filled_pos[clickedTag] == -1) {
                if (active_player == 0) {
                    clickedBtn.setText("O");
                    active_player = 1;
                    show_winner.setText("X's Turn");
                    filled_pos[clickedTag] = 0;
                } else {
                    clickedBtn.setText("X");
                    active_player = 0;
                    show_winner.setText("O's Turn");
                    filled_pos[clickedTag] = 1;
                }

            }
        }

        // Check for the winner
        checkForWinner();

    }

    private void checkForWinner() {
        for(int[] pos: winning_pos){
            int val1 = pos[0];
            int val2 = pos[1];
            int val3 = pos[2];

            if(filled_pos[val1] == filled_pos[val2] && filled_pos[val2] == filled_pos[val3] &&
                    filled_pos[val1] != -1){
                show_winner.setText(filled_pos[val1] == 0 ? "O is the winner!!" : "X is the winner");
                game_over = true;
            }

        }
    }

}