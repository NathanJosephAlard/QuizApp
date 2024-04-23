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

public class FourthQuestionFragment extends Fragment {

    private CheckBox optionDatabase, optionNetwork;
    private Button nextButton;

    public FourthQuestionFragment() {
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
        optionDatabase = view.findViewById(R.id.option_b);  // Assuming 'option_b' is "Database Management"
        optionNetwork = view.findViewById(R.id.option_c);  // Assuming 'option_c' is "Network Security"
        nextButton = view.findViewById(R.id.next_button);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int score = calculateScore();
                if (getActivity() instanceof MainActivity) {
                    ((MainActivity) getActivity()).onAnswerSelected(score);
                }
            }
        });
    }

    private int calculateScore() {
        // Correct answers are Option B and Option C
        if (optionDatabase.isChecked() && optionNetwork.isChecked()) {
            return 4;  // Full marks if both correct options are selected
        } else if (optionDatabase.isChecked() || optionNetwork.isChecked()) {
            return 2;  // Partial marks if only one of the correct options is selected
        } else {
            return 0;  // No marks if no correct options are selected
        }
    }
}