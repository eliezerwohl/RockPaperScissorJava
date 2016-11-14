package com.example.eliezerwohl.rockspaperscissor;

import android.graphics.Typeface;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Elie on 11/14/2016.
 */

public class Display {
    private int roundCount = 0;
    private int userScore = 0;
    private int compyScore= 0;
    private String currentVerdict;

    public int getRoundCount() {
        return roundCount;
    }

    public void setRoundCount(int roundCount) {
        this.roundCount = roundCount;
    }
    public void setRoundCount(int roundCount, TextView roundCountDisplay) {
        this.roundCount += roundCount;
        roundCountDisplay.setText(Integer.toString(this.roundCount));
    }

    public void displayVerdict(String judge, TextView verdict){
        Log.d("this", "This is judge: " + judge);
        if (judge.equals("user")) {

           this.currentVerdict = "You are VICTORY!!";
        }
        else if(judge.equals("computer")){
          this.currentVerdict = "So defeated...";
        }
        else if (judge.equals("none")){
          this.currentVerdict = "A WINNER IS NONE!";
        }
        verdict.setText(this.currentVerdict);
    }
    public void restoreVerdict(String text, TextView verdict){
        verdict.setText(text);
    }
    public void restore(int userScore, TextView userDisplay, int compyScore, TextView compyDisplay, int roundCount, TextView roundDisplay){
        this.userScore = userScore;
        this.compyScore = compyScore;
        this.roundCount = roundCount;
        userDisplay.setText(Integer.toString(userScore));
        compyDisplay.setText(Integer.toString(compyScore));
        roundDisplay.setText(Integer.toString(roundCount));
    }
    public void setScore(String judge, TextView userScoreDisplay, TextView computerScoreDisplay){
        if (judge.equals("user")){
            this.userScore +=1;
            userScoreDisplay.setText(Integer.toString(this.userScore));

        }
        else if (judge.equals("computer")){
            this.compyScore +=1;
            computerScoreDisplay.setText(Integer.toString(this.compyScore));
        }
    }
    public void icon(TextView iconRock, TextView iconPaper, TextView iconScissor, Typeface fontFamily){
        iconRock.setTypeface(fontFamily);
        iconRock.setText("\uf255");
        iconPaper.setTypeface(fontFamily);
        iconPaper.setText("\uf256");
        iconScissor.setTypeface(fontFamily);
        iconScissor.setText("\uf257");

    }
    public int getUserScore() {
        return userScore;
    }

    public int getCompyScore() {
        return compyScore;
    }

    public String getCurrentVerdict() {
        return currentVerdict;
    }

}

