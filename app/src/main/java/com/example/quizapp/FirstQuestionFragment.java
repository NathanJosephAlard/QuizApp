package com.example.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FirstQuestionFragment extends Fragment {

    private CheckBox optionA, optionB, optionC;
    private Button nextButton;

    public FirstQuestionFragment() {
        // Required empty public constructor
    }

    public interface OnFragmentInteractionListener {
        void onAnswerSelected(int score);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.question_checkboxes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        optionA = view.findViewById(R.id.option_a);
        optionB = view.findViewById(R.id.option_b);
        optionC = view.findViewById(R.id.option_c);
        nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = calculateScore();
                ((MainActivity) getActivity()).onAnswerSelected(score);
            }
        });
    }

    private int calculateScore() {
        // Correct answers are Option A and Option B
        if (optionA.isChecked() && optionB.isChecked() && !optionC.isChecked()) {
            return 4;  // full marks for correct answer
        } else if ((optionA.isChecked() || optionB.isChecked()) && !optionC.isChecked()) {
            return 2;  // partial marks if only one correct answer is selected
        } else {
            return 0;  // no marks for incorrect or no answers
        }
    }
}
