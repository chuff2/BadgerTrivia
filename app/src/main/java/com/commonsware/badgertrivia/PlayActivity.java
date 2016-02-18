package com.commonsware.badgertrivia;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import android.util.AttributeSet;

/**
 * Created by connerhuff on 2/13/16.
 */
public class PlayActivity extends AppCompatActivity implements ImageBasedFragment.OnQuestionAnsweredListener, TextBasedFragment.OnQuestionAnsweredListener {

    //this should be where I hold the array of info for all the data for the questions
    private Queue<Question> questions;
    private int numCorrect;
    private int numWrong;
    private int totalNumQuestions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //initialize numbers and questions
        prepareAllQuestions();
        numCorrect = 0;
        numWrong = 0;

        //if our first question is image based then we need to start an ImageBasedFragment
        Question currQuestion = questions.poll();
        if (currQuestion.isImageBased()){
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment_container, ImageBasedFragment.newInstance(currQuestion))
                    .commit();
        }
        //otherwise we know it is text based so we can do this instead
        else{
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_fragment_container, TextBasedFragment.newInstance(currQuestion))
                    .commit();
        }
        hideKeyboard(this);
    }
    /*
    @Override
    protected view onCreateView(String name, Context context, AttributeSet attrs){
        super.onCreateView(name, context, attrs);
        hideKeyboard(this);
        return inflater.inflate(R.layout.left, container, false);
    }
    */

    //here we prepare all the question objects with predetermined questions
    private void prepareAllQuestions(){
        questions = new LinkedList<Question>();
        totalNumQuestions = 5;
        //String[] answers = {"The dog", "The frog", "The horse", "The cricket"};
        //new ArrayList<String>(Arrays.asList(answers))

        //q1 image type
        Question q1 = new Question();
        q1 = q1.newInstance(true, "What was this Badger halfback's nickname?", "The horse", null, R.drawable.alan_ameche);
        questions.add(q1);

        //q2 image type
        Question q2 = new Question();
        q2 = q2.newInstance(true, "Who is this former Badger basketball player?", "Devin Harris", null, R.drawable.devin_harris);
        questions.add(q2);

        //q3 image type
        Question q3 = new Question();
        String[] answers3 = {"234", "393", "408", "432"};
        q3 = q3.newInstance(false, "How many yards did MGIII get against Nebraska in 2014?", "408", new ArrayList<String>(Arrays.asList(answers3)), 0);
        questions.add(q3);

        //q4 image type
        Question q4 = new Question();
        q4 = q4.newInstance(true, "What do the JJ in JJ Watt stand for?", "Justin James", null, R.drawable.jj_watt);
        questions.add(q4);

        //q5 image type
        Question q5 = new Question();
        String[] answers5 = {"Frank Kaminsky", "Alando Tucker", "Jordan Taylor", "Michael Finley"};
        q5 = q5.newInstance(false, "Who is the all-time leading scorer for Badger mens basketball?", "Alando Tucker", new ArrayList<String>(Arrays.asList(answers5)), 0);
        questions.add(q5);
    }

    public boolean onQuestionAnswered(boolean correctAnswer){
        if (correctAnswer){
            numCorrect++;
        }
        else{
            numWrong++;
        }

        //if we are now done with questions, let the fragment know so we can queue a dialog
        if (numWrong + numCorrect == totalNumQuestions){
            return true;
        }
        return false;
    }

    public String onGameOverGetFinalScore(){
        String returnString = "Final Score: " + this.numCorrect + "/" + this.totalNumQuestions + ". Play again?";
        return returnString;
    }

    public Queue<Question> getRemainingQuestions(){
        return questions;
    }

    public Queue<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Queue<Question> questions) {
        this.questions = questions;
    }

    public int getNumCorrect() {
        return numCorrect;
    }

    public void setNumCorrect(int numCorrect) {
        this.numCorrect = numCorrect;
    }

    public int getNumWrong() {
        return numWrong;
    }

    public void setNumWrong(int numWrong) {
        this.numWrong = numWrong;
    }

    public int getTotalNumQuestions() {
        return totalNumQuestions;
    }

    public void setTotalNumQuestions(int totalNumQuestions) {
        this.totalNumQuestions = totalNumQuestions;
    }

    public static void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
