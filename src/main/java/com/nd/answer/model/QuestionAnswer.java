package com.nd.answer.model;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/15 0015.
 */
public class QuestionAnswer {
    private String questionId;
    private List<String> answer;
    private Boolean isCorrect;

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
