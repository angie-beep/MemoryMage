package de.memorymage.entity;


public class Page {

    String question;
    String answer;
    int pageNumber;
    boolean isCorrect;


    public Page(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String toString() {
        return question + " " + answer;
    }
}
