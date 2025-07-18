package model;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private int correctIndex;
    private int givenIndex;
    String correctValue;
    String givenValue;

    public Question(String text, List<String> options, int correctIndex) {
        this.text = text;
        this.options = options;
        this.correctIndex = correctIndex;
        
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }

    public boolean isCorrect() {
        return givenIndex == correctIndex;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    public void setGivenIndex(int givenIndex) {
        this.givenIndex = givenIndex;
    }

    public String getCorrectValue() {
        return options.get(correctIndex);
    }

    public String getGivenValue() {
        return options.get(givenIndex);
    }

    @Override
    public String toString() {
        return "Question{" + "text=" + text + ", options=" + options + ", correctIndex=" + correctIndex + ", givenIndex=" + givenIndex + '}';
    }
    
    
}
