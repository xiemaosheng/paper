package com.nd.answer.model;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class QuestionResp {
    private String questionId;
    private String questionTitle;
    private LinkedHashMap<String, String> choiceItems;

    public QuestionResp() {
    }

    public QuestionResp(String questionId, String questionTitle, LinkedHashMap<String, String> choiceItems) {
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.choiceItems = choiceItems;
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
}
