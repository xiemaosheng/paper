package com.nd.answer.model;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/9/17 0017.
 */
public class QuestionResultResp {
    private String questionId;
    private String questionTitle;
    private LinkedHashMap<String, String> choiceItems;
    private List<String> rightAnswer;
    private List<String> answer;
    private String analysis;


    public QuestionResultResp(String questionId, String questionTitle, LinkedHashMap<String, String> choiceItems, List<String> rightAnswer, List<String> answer, String analysis) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.choiceItems = choiceItems;
        this.rightAnswer = rightAnswer;
        this.answer = answer;
        this.analysis = analysis;
    }

    public QuestionResultResp() {
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public LinkedHashMap<String, String> getChoiceItems() {
        return choiceItems;
    }

    public void setChoiceItems(LinkedHashMap<String, String> choiceItems) {
        this.choiceItems = choiceItems;
    }

    public List<String> getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(List<String> rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
