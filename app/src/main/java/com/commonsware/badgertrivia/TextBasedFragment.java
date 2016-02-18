package com.commonsware.badgertrivia;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;
import java.util.Queue;
import java.util.ArrayList;
import android.content.Intent;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by connerhuff on 2/13/16.
 */
public class TextBasedFragment extends Fragment {

    private Button submitButton;
    private TextView questionText;
    private RadioButton radio1;
    private RadioButton radio2;
    private RadioButton radio3;
    private RadioButton radio4;
    private RadioGroup radioGroup;

    private Question q;
    private String playersAnswer;
    private OnQuestionAnsweredListener mListener;

    //these are the callback methods
    public interface OnQuestionAnsweredListener {
        public boolean onQuestionAnswered(boolean answerCorrect);
        public String onGameOverGetFinalScore();
        public Queue<Question> getRemainingQuestions();
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnQuestionAnsweredListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnQuestionAnsweredListener");
        }
    }


    public static TextBasedFragment newInstance(Question q) {
        TextBasedFragment fragment = new TextBasedFragment();
        fragment.q = q;
        //no bundle info for now
        return fragment;
    }

    public TextBasedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //TA Implementation DON'T CHANGE
        View view = null;
        view = inflater.inflate(R.layout.fragment_textbased, container, false);

        submitButton = (Button) view.findViewById(R.id.submitButton);
        questionText = (TextView) view.findViewById(R.id.questionText);
        radio1 = (RadioButton) view.findViewById(R.id.radio1);
        radio2 = (RadioButton) view.findViewById(R.id.radio2);
        radio3 = (RadioButton) view.findViewById(R.id.radio3);
        radio4 = (RadioButton) view.findViewById(R.id.radio4);
        radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        ArrayList<String> candidates = q.getqCandidates();
        radio1.setText(candidates.get(0));
        radio2.setText(candidates.get(1));
        radio3.setText(candidates.get(2));
        radio4.setText(candidates.get(3));
        questionText.setText(q.getqText());

        return view;

        // End TA Implementation
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        //game logic goes here
        //TA Implementation
        //TODO work on stuff in here
        //different way of implementing click interaction.
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rightAnswer = q.getqAnswer();
                //TODO get players answer
                int playersAnswerId = radioGroup.getCheckedRadioButtonId();
                String playersAnswer;
                if (playersAnswerId != -1){
                    RadioButton radioButtonThatIsChecked = (RadioButton) radioGroup.findViewById(playersAnswerId);
                    playersAnswer = (String) radioButtonThatIsChecked.getText();
                }
                else{
                    playersAnswer = "";
                }


                //if the player got the question right

                boolean showDialogNow;
                if (playersAnswer.equalsIgnoreCase(rightAnswer)) {
                    //implement call backs so we can get score information
                    showDialogNow = mListener.onQuestionAnswered(true);
                } else {
                    showDialogNow = mListener.onQuestionAnswered(false);
                }

                //if the questions have been exhausted
                if (showDialogNow) {
                    String finalScore = mListener.onGameOverGetFinalScore();
                    //Toast.makeText(getActivity(), finalScore, Toast.LENGTH_SHORT).show();
                    //TODO put dialog stuff here
                    displayScore(finalScore);
                }
                //there are more questions to go
                else {
                    Queue<Question> remainingQs = mListener.getRemainingQuestions();
                    Question currQuestion = remainingQs.poll();
                    if (currQuestion.isImageBased()) {
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_fragment_container, ImageBasedFragment.newInstance(currQuestion))
                                .commit();
                    }
                    //otherwise we know it is text based so we can do this instead
                    else {
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.main_fragment_container, TextBasedFragment.newInstance(currQuestion))
                                .commit();
                    }
                }
            }
        });

    }


    private void displayScore(String winner){

        //TODO finish implementing this AlertDialog

        //do a prompt about the winner
        new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle("Play again?")
                .setMessage(winner)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO start a rematch!
                        replay();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO back out the the start screen
                        backToStartScreen();
                    }
                })
                .show();

    }

    private void backToStartScreen(){
        Intent welcomeActivity = new Intent(getActivity(), WelcomeActivity.class);
        startActivity(welcomeActivity);
    }

    private void replay(){
        Intent playActivity = new Intent(getActivity(), PlayActivity.class);
        startActivity(playActivity);
    }

}