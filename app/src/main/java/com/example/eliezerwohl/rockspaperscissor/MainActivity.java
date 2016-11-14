package com.example.eliezerwohl.rockspaperscissor;

import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView computerScoreDisplay;
    private TextView userScoreDisplay;
    private TextView roundCountDisplay;
    private TextView iconRock;
    private TextView iconPaper;
    private TextView iconScissor;
    private TextView verdict;
    private long mLastClickTime = 0;
    private Display display = new Display();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
       String temp = display.getCurrentVerdict();
        if (display.getCurrentVerdict() != null) {
            outState.putString("VERDICT_DATA", display.getCurrentVerdict());
        }
        outState.putInt("COMPYSCORE_DATA", display.getCompyScore());
        outState.putInt("USERSCORE_DATA", display.getUserScore());
        outState.putInt("ROUND_DATA", display.getRoundCount());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);;
        display.restoreVerdict(savedInstanceState.getString("VERDICT_DATA"), verdict);
        int restoreCompy = savedInstanceState.getInt("COMPYSCORE_DATA");
        int restoreUser = savedInstanceState.getInt("USERSCORE_DATA");
        int restoreRound = savedInstanceState.getInt("ROUND_DATA");
        display.restore(restoreUser, userScoreDisplay,restoreCompy, computerScoreDisplay, restoreRound,roundCountDisplay);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("this is on", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface fontFamily = Typeface.createFromAsset(getAssets(), "fonts/fontawesome.ttf");
        iconRock = (TextView) findViewById(R.id.iconRock);
        iconPaper = (TextView) findViewById(R.id.iconPaper);
        iconScissor = (TextView) findViewById(R.id.iconScissor);
        display.icon(iconRock, iconPaper, iconScissor, fontFamily);
        verdict = (TextView) findViewById(R.id.verdict);
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
                mLastClickTime = SystemClock.elapsedRealtime();
                Button value = (Button) view;
                String playerMove = value.getText().toString();
                ComputerSelect computerSelect = new ComputerSelect();
                String computerMove = computerSelect.computerMove();
                computerAnimate.animate(computerMove, iconRock, iconPaper, iconScissor);
                Judgement judgement = new Judgement();
                String judge = judgement.judge(playerMove, computerMove);
                display.displayVerdict(judge, verdict);
                display.setRoundCount(1, roundCountDisplay);
                display.setScore(judge, userScoreDisplay, computerScoreDisplay);
            }
        };
        buttonRock.setOnClickListener(userSelect);
        buttonPaper.setOnClickListener(userSelect);
        buttonScissor.setOnClickListener(userSelect);
    }
}