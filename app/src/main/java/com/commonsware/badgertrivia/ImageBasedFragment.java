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
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.Activity;

/**
 * Created by connerhuff on 2/13/16.
 */
public class ImageBasedFragment extends Fragment {

    private Button submitButton;
    private EditText answerBox;
    private ImageView image;
    private TextView questionText;
    private Question q;
    private OnQuestionAnsweredListener mListener;

    //these are the callback methods
    public interface OnQuestionAnsweredListener {
        public boolean onQuestionAnswered(boolean answerCorrect);
        public String onGameOverGetFinalScore();
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

    public static ImageBasedFragment newInstance(Question q) {
        ImageBasedFragment fragment = new ImageBasedFragment();
        fragment.q = q;
        //no bundle info for now
        return fragment;
    }

    public ImageBasedFragment() {
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
        view = inflater.inflate(R.layout.fragment_imagebased, container, false);


        submitButton = (Button) view.findViewById(R.id.submitButton);
        answerBox = (EditText) view.findViewById(R.id.answerBox);
        image = (ImageView) view.findViewById(R.id.questionImage);
        questionText = (TextView) view.findViewById(R.id.questionText);

        image.setBackgroundResource(q.getImageResourceId());
        questionText.setText(q.getqText());
        return view;

        // End TA Implementation
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //game logic goes here
        //TA Implementation

        //different way of implementing click interaction.
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rightAnswer = q.getqAnswer();
                String playersAnswer = answerBox.getText().toString();
                //if the player got the question right
                boolean showDialogNow;
                if (playersAnswer.equalsIgnoreCase(rightAnswer)){
                    //implement call backs so we can get score information
                    showDialogNow = mListener.onQuestionAnswered(true);
                }
                else {
                    showDialogNow = mListener.onQuestionAnswered(false);
                }

                if (showDialogNow){
                    String finalScore = mListener.onGameOverGetFinalScore();
                    Toast.makeText(getActivity(), finalScore, Toast.LENGTH_SHORT).show();
                    //TODO put dialog stuff here
                }
            }
        });

    }


    private void displayWinner(String winner){
        //do a prompt about the winner
        new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle("Winner is:")
                .setMessage(winner)
                .setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .show();

    }

}
