package com.example.eliezerwohl.rockspaperscissor;

import android.view.animation.AlphaAnimation;
import android.widget.TextView;

/**
 * Created by Elie on 11/13/2016.
 */

public class ComputerAnimate {
    public void animate (String computerMove, TextView iconRock, TextView iconPaper, TextView iconScissor) {
        AlphaAnimation compyAnimate = new AlphaAnimation(0.2f, 1.0f);
        compyAnimate.setDuration(500);
        compyAnimate.setRepeatCount(6);
        if (computerMove.equals("rock")) {
            iconRock.startAnimation(compyAnimate);
        } else if (computerMove.equals("paper")) {
            iconPaper.startAnimation(compyAnimate);
        } else {
            iconScissor.startAnimation(compyAnimate);
        }
    }

}

