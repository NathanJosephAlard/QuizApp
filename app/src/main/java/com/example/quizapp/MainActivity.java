package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.quizapp.FirstQuestionFragment.OnFragmentInteractionListener;


public class MainActivity extends AppCompatActivity implements FirstQuestionFragment.OnFragmentInteractionListener,
        SecondQuestionFragment.OnFragmentInteractionListener, ThirdQuestionFragment.OnFragmentInteractionListener,
        FourthQuestionFragment.OnFragmentInteractionListener, FifthQuestionFragment.OnFragmentInteractionListener {

    private int currentQuestion = 1;
    private int totalScore = 0;
    private static final int TOTAL_QUESTIONS = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Load the first question fragment
        if (savedInstanceState == null) {
            loadFragment(new FirstQuestionFragment());
        }
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onAnswerSelected(int score) {
        totalScore += score;
        currentQuestion++;

        if (currentQuestion <= TOTAL_QUESTIONS) {
            Fragment nextFragment;
            switch (currentQuestion) {
                case 2:
                    nextFragment = new SecondQuestionFragment();
                    break;
                case 3:
                    nextFragment = new ThirdQuestionFragment();
                    break;
                case 4:
                    nextFragment = new FourthQuestionFragment();
                    break;
                case 5:
                    nextFragment = new FifthQuestionFragment();
                    break;
                default:
                    // Log unexpected value and handle gracefully
                    currentQuestion--; // Reset currentQuestion
                    Toast.makeText(this, "An unexpected error occurred", Toast.LENGTH_SHORT).show();
                    return;
            }
            loadFragment(nextFragment);
        } else {
            showResults();
        }
    }

    public void showResults() {
        Toast.makeText(this, "Your score is: " + totalScore + " out of 20", Toast.LENGTH_LONG).show();
        // Optionally reset quiz or close activity
    }
}
