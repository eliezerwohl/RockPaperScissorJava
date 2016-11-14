package com.example.eliezerwohl.rockspaperscissor;

import static com.example.eliezerwohl.rockspaperscissor.R.id.computerScoreDisplay;
import static com.example.eliezerwohl.rockspaperscissor.R.id.userScoreDisplay;

/**
 * Created by Elie on 11/14/2016.
 */

public class Judgement {
    public String judge(String playerMove, String computerMove){
        String verdict = null;
        if (playerMove.equals(computerMove)) {
            verdict = "none";
        } else if ((playerMove.equals("rock") && computerMove.equals("scissor"))
                || (playerMove.equals("paper") && computerMove.equals("rock"))
                || (playerMove.equals("scissor") && computerMove.equals("paper"))) {
            verdict = "user";
        } else {
            verdict = "computer";
        }
        return verdict;
    }
}
