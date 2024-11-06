package com.example.grawkosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView[] diceViews = new TextView[5];
    private TextView rollResult, gameResult, rollCount;
    private int totalScore = 0;
    private int rollCounter = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diceViews[0] = findViewById(R.id.dice1);
        diceViews[1] = findViewById(R.id.dice2);
        diceViews[2] = findViewById(R.id.dice3);
        diceViews[3] = findViewById(R.id.dice4);
        diceViews[4] = findViewById(R.id.dice5);
        rollResult = findViewById(R.id.rollResult);
        gameResult = findViewById(R.id.gameResult);
        rollCount = findViewById(R.id.rollCount);

        Button rollButton = findViewById(R.id.rollButton);
        Button resetButton = findViewById(R.id.resetButton);

        rollButton.setOnClickListener(v -> rollDice());
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void rollDice() {
        int[] diceResults = new int[5];
        int currentRollScore = 0;

        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1;
            currentRollScore += diceResults[i];
        }

        displayDiceResults(diceResults);

        rollResult.setText("Wynik tego losowania: " + currentRollScore);
        totalScore += calculateScore(diceResults);
        gameResult.setText("Wynik gry: " + totalScore);

        rollCounter++;
        rollCount.setText("Liczba rzutów: " + rollCounter);
    }

    private void resetGame() {
        totalScore = 0;
        rollCounter = 0;
        for (TextView diceView : diceViews) {
            diceView.setText("?");
        }
        rollResult.setText("Wynik tego losowania: ");
        gameResult.setText("Wynik gry: 0");
        rollCount.setText("Liczba rzutów: 0");
    }

    private void displayDiceResults(int[] diceResults) {
        for (int i = 0; i < 5; i++) {
            diceViews[i].setText(String.valueOf(diceResults[i]));
        }
    }

    private int calculateScore(int[] diceResults) {
        int score = 0;
        int[] counts = new int[6];

        for (int die : diceResults) {
            counts[die - 1]++;
        }

        for (int i = 0; i < 6; i++) {
            if (counts[i] >= 2) {
                score += (i + 1) * counts[i];
            }
        }
        return score;
    }
}
