package com.nd.paper.model;

import java.util.List;

/**
 * 试卷分发请求参数列表
 * Created by Administrator on
 * 2016/9/15 0015.
 */
public class PaperReq {
    private String paperName;
    private String knowledgeId;
    private List<String> questionId;

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public List<String> getQuestionId() {
        return questionId;
    }

    public void setQuestionId(List<String> questionId) {
        this.questionId = questionId;
    }

}
