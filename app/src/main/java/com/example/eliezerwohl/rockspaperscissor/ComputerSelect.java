package com.example.eliezerwohl.rockspaperscissor;

import android.util.Log;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Elie on 11/13/2016.
 */

public class ComputerSelect {
    private ArrayList<String> computerChoice = new ArrayList<>();
    public String computerMove(){
        computerChoice.add("rock");
        computerChoice.add("paper");
        computerChoice.add("scissor");
        Random rand = new Random();
        int randomIndex = rand.nextInt(3);
        String computerMove = computerChoice.get(randomIndex);
        Log.d("computer", "mputer move is: "+ computerMove);
        return computerMove;
    }
}
