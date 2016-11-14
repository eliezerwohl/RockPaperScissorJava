package com.example.eliezerwohl.rockspaperscissor;

import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int roundCount = 1;
    private int userScore = 0;
    private int computerScore = 0;
    private TextView computerScoreDisplay;
    private TextView userScoreDisplay;
    private TextView roundCountDisplay;
    private TextView iconRock;
    private TextView iconPaper;
    private TextView iconScissor;
    private TextView verdict;
    private static String currentVerdict;
    private long mLastClickTime = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (currentVerdict != null) {
            outState.putString("VERDICT_DATA", currentVerdict);
        }
        outState.putInt("COMPYSCORE_DATA", computerScore);
        outState.putInt("USERSCORE_DATA", userScore);
        outState.putInt("ROUND_DATA", roundCount);
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        verdict.setText(savedInstanceState.getString("VERDICT_DATA"));
        int restoreCompy = savedInstanceState.getInt("COMPYSCORE_DATA");
        computerScore = restoreCompy;
        computerScoreDisplay.setText(Integer.toString(restoreCompy));
        int restoreUser = savedInstanceState.getInt("USERSCORE_DATA");
        userScore = restoreUser;
        userScoreDisplay.setText(Integer.toString(restoreUser));
        int restoreRound = savedInstanceState.getInt("ROUND_DATA");
        roundCount = restoreRound;
        roundCountDisplay.setText(Integer.toString(restoreRound));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        iconRock = (TextView) findViewById(R.id.iconRock);
        iconPaper = (TextView) findViewById(R.id.iconPaper);
        iconScissor = (TextView) findViewById(R.id.iconScissor);
        verdict = (TextView) findViewById(R.id.verdict);
        iconRock.setTypeface(fontFamily);
        iconRock.setText("\uf255");
        iconPaper.setTypeface(fontFamily);
        iconPaper.setText("\uf256");
        iconScissor.setTypeface(fontFamily);
        iconScissor.setText("\uf257");
        computerScoreDisplay = (TextView) findViewById(R.id.computerScoreDisplay);
        userScoreDisplay = (TextView) findViewById(R.id.userScoreDisplay);
        roundCountDisplay = (TextView) findViewById(R.id.roundCountDisplay);
        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissor = (Button) findViewById(R.id.buttonScissor);
        final ComputerAnimate computerAnimate = new ComputerAnimate();
        View.OnClickListener userSelect = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //prevent the player from clicking before animation is done
                if (SystemClock.elapsedRealtime() - mLastClickTime < 3000) {
                    return;
                }
                roundCount++;
                mLastClickTime = SystemClock.elapsedRealtime();
                Button value = (Button) view;
                String playerMove = value.getText().toString();
                ComputerSelect computerSelect = new ComputerSelect();
                String computerMove = computerSelect.computerMove();
                computerAnimate.animate(computerMove, iconRock, iconPaper, iconScissor);
                if (playerMove.equals(computerMove)) {
                    currentVerdict = ("A WINNER IS NONE!");
                } else if ((playerMove.equals("rock") && computerMove.equals("scissor"))
                        || (playerMove.equals("paper") && computerMove.equals("rock"))
                        || (playerMove.equals("scissor") && computerMove.equals("paper"))) {
                    currentVerdict = ("YOU ARE VICTORY!!");
                    userScore++;
                    userScoreDisplay.setText(Integer.toString(userScore));
                } else {
                    currentVerdict = ("So defeated...");
                    computerScore++;
                    computerScoreDisplay.setText(Integer.toString(computerScore));
                }
                verdict.setText(currentVerdict);
                roundCountDisplay.setText(Integer.toString(roundCount));
            }
        };
        buttonRock.setOnClickListener(userSelect);
        buttonPaper.setOnClickListener(userSelect);
        buttonScissor.setOnClickListener(userSelect);
    }
}