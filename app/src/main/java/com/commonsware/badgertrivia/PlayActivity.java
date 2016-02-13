package com.commonsware.badgertrivia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

/**
 * Created by connerhuff on 2/13/16.
 */
public class PlayActivity extends AppCompatActivity{

    //this should be where I hold the array of info for all the data for the questions
    private Queue<Question> questions;
    private int numCorrect;
    private int numWrong;
    private int totalNumQuestions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        prepareAllQuestions();
        numCorrect = 0;
        numWrong = 0;


        //if our first question is image based then we need to start an ImageBasedFragment
        Question currQuestion = questions.poll();
        if (currQuestion.isImageBased()){
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, ImageBasedFragment.newInstance(currQuestion))
                    .addToBackStack(null)
                    .commit();
        }
        //otherwise we know it is text based so we can do this instead
        else{
            /*getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_fragment_container, TextBasedFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        */
        }
    }

    private void prepareAllQuestions(){
        questions = new LinkedList<Question>();
        Question q = new Question();
        totalNumQuestions = 1;
        String[] answers = {"The dog", "The frog", "The horse", "The cricket"};
        //new ArrayList<String>(Arrays.asList(answers))
        //q1 image type
        q = q.newInstance(true, "What was this Badger halfback's nickname?", "The horse", null, R.drawable.alan_ameche);
        questions.add(q);

        //q2 image type
        q = q.newInstance(true, "Who is this former Badger basketball player?", "Devin Harris", null, R.drawable.devin_harris);
        questions.add(q);


        //(boolean imageType, String query, String answer, ArrayList<String> candidates, int imageId)
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
}
