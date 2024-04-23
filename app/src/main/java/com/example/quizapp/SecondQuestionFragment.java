package com.example.quizapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondQuestionFragment extends Fragment {

    private RadioGroup radioGroup;
    private RadioButton optionTrue;
    private Button nextButton;

    public SecondQuestionFragment() {
        // Required empty public constructor
    }

    public interface OnFragmentInteractionListener {
        void onAnswerSelected(int score);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.question_radiobuttons, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        radioGroup = view.findViewById(R.id.radio_group);
        optionTrue = view.findViewById(R.id.option_a);  // Assuming 'option_a' is the 'True' button
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
        // Check which radio button is checked and award score
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == optionTrue.getId()) {
            return 4; // Full marks for correct answer
        } else {
            return 0; // No marks for incorrect answer
        }
    }
}
