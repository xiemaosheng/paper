package com.nd.question.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/6 0006.
 */
@Document
public class QuestionEntity implements Serializable {
    private static final long serialVersionUID = 7163206564555650050L;
    @Id
    private String questionId;

    private String questionTitle;

    private LinkedHashMap<String, String> chioceItems;

    private List<String> answer;

    private String knowledge;

    private String analysis;

    private String questionType;

    private Date createDate;

    private Date updateDate;

    private String createBy;


    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
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

    public LinkedHashMap<String, String> getChioceItems() {
        return chioceItems;
    }

    public void setChioceItems(LinkedHashMap<String, String> chioceItems) {
        this.chioceItems = chioceItems;
    }

    public List<String> getAnswer() {
        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

    public String getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(String knowledge) {
        this.knowledge = knowledge;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
