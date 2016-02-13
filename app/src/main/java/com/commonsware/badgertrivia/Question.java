package com.commonsware.badgertrivia;


import java.util.ArrayList;
/**
 * Created by connerhuff on 2/13/16.
 */
public class Question {

    private boolean imageBased;
        private String qText;
        private int imageResourceId;
        private String qAnswer;
        private ArrayList<String> qCandidates;

    public  Question newInstance(boolean imageType, String query, String answer, ArrayList<String> candidates, int imageId){
        //Question question = new Question();
        imageBased = imageType;
        qText = query;
        if (imageType){
            qAnswer = answer;
            imageResourceId = imageId;
        }
        else if (!imageType){
            qAnswer = answer;
            qCandidates = candidates;
        }
        return this;
    }

    public Question(){

    }

    public boolean isImageBased() {
        return imageBased;
    }

    public void setImageBased(boolean imageBased) {
        this.imageBased = imageBased;
    }

    public String getqText() {
        return qText;
    }

    public void setqText(String qText) {
        this.qText = qText;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getqAnswer() {
        return qAnswer;
    }

    public void setqAnswer(String qAnswer) {
        this.qAnswer = qAnswer;
    }

    public ArrayList<String> getqCandidates() {
        return qCandidates;
    }

    public void setqCandidates(ArrayList<String> qCandidates) {
        this.qCandidates = qCandidates;
    }

}
