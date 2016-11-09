package com.example.eliezerwohl.rockspaperscissor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

import static android.R.attr.button;
import static android.media.CamcorderProfile.get;
import static com.example.eliezerwohl.rockspaperscissor.R.id.buttonPaper;
import static com.example.eliezerwohl.rockspaperscissor.R.id.buttonRock;
import static com.example.eliezerwohl.rockspaperscissor.R.id.buttonScissor;

public class MainActivity extends AppCompatActivity {
    private String userInput;
    private ArrayList<String> computerChoice = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        computerChoice.add("rock");
        computerChoice.add("paper");
        computerChoice.add("scissor");
        Button buttonRock = (Button) findViewById(R.id.buttonRock);
        Button buttonPaper = (Button) findViewById(R.id.buttonPaper);
        Button buttonScissor = (Button) findViewById(R.id.buttonScissor);
        View.OnClickListener userSelect = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button value = (Button) view;
                String playerMove = value.getText().toString();
//                Log.d("this", value.getText().toString());
                Random rand = new Random();
                int randomIndex = rand.nextInt(3);
                String computerMove = computerChoice.get(randomIndex);
                Log.d("this computer picks", computerMove);
                if (playerMove.equals(computerMove)) {
                    Log.d("this game is", "tied");
                } else if ((playerMove.equals("rock") && computerMove.equals("scissor"))
                        || (playerMove.equals("paper") && computerMove.equals("rock"))
                        || (playerMove.equals("scissor") && computerMove.equals("paper"))) {
                    Log.d("", "win");
                } else {
                    Log.d("", "you lose");
                }

            }
        };
        buttonRock.setOnClickListener(userSelect);
        buttonPaper.setOnClickListener(userSelect);
        buttonScissor.setOnClickListener(userSelect);
    }
}
