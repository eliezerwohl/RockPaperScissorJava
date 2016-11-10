package com.example.eliezerwohl.rockspaperscissor;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private String userInput;
    private ArrayList<String> computerChoice = new ArrayList<>();
    private int roundCount = 0;
    private int userScore = 0;
    private int computerScore = 0;
    private TextView computerScoreDisplay;
    private TextView userScoreDisplay;
    private TextView roundCountDisplay;
    private TextView iconRock;
    private TextView iconPaper;
    private TextView iconScissor;
    private TextView verdict;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        final TextView iconRock = (TextView) findViewById(R.id.iconRock);
        final TextView iconPaper = (TextView) findViewById(R.id.iconPaper);
        final TextView iconScissor = (TextView) findViewById(R.id.iconScissor);
        final TextView verdict = (TextView) findViewById(R.id.verdict);
        iconRock.setTypeface(fontFamily);
        iconRock.setText("\uf255");
        iconPaper.setTypeface(fontFamily);
        iconPaper.setText("\uf256");
        iconScissor.setTypeface(fontFamily);
        iconScissor.setText("\uf257");
        computerChoice.add("rock");
        computerChoice.add("paper");
        computerChoice.add("scissor");
        computerScoreDisplay = (TextView) findViewById(R.id.computerScoreDisplay);
        userScoreDisplay = (TextView) findViewById(R.id.userScoreDisplay);
        roundCountDisplay = (TextView) findViewById(R.id.roundCountDisplay);
        final Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissor = (Button) findViewById(R.id.buttonScissor);
        View.OnClickListener userSelect = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlphaAnimation compyAnimate = new AlphaAnimation(0.2f, 1.0f);
                compyAnimate.setDuration(500);
                compyAnimate.setRepeatCount(4);
                roundCount++;
                Button value = (Button) view;
                String playerMove = value.getText().toString();
                Random rand = new Random();
                int randomIndex = rand.nextInt(3);
                String computerMove = computerChoice.get(randomIndex);
                if (computerMove.equals("rock")) {
                    iconRock.startAnimation(compyAnimate);
                } else if (computerMove.equals("paper")) {
                    iconPaper.startAnimation(compyAnimate);
                } else {
                    iconScissor.startAnimation(compyAnimate);
                }
                if (playerMove.equals(computerMove)) {
                    verdict.setText("A WINNER IS NONE!");
                } else if ((playerMove.equals("rock") && computerMove.equals("scissor"))
                        || (playerMove.equals("paper") && computerMove.equals("rock"))
                        || (playerMove.equals("scissor") && computerMove.equals("paper"))) {
                    verdict.setText("YOU ARE VICTORY!!");
                    userScore++;
                    userScoreDisplay.setText(Integer.toString(userScore));
                } else {
                    verdict.setText("So defeated...");
                    computerScore++;
                    computerScoreDisplay.setText(Integer.toString(computerScore));
                }
                int currentRound = roundCount - 1;
                Log.d("l", "the round count is : " + currentRound + " your score is: " + userScore + "the computer score is: " + computerScore);
                roundCountDisplay.setText(Integer.toString(roundCount));
            }
        };
        buttonRock.setOnClickListener(userSelect);
        buttonPaper.setOnClickListener(userSelect);
        buttonScissor.setOnClickListener(userSelect);
    }
}
