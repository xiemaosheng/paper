package com.nd.paper.model;

/**
 * 老师查看试卷的作答详情响应实体
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public class PaperResp {
    PaperAnwserDetail answerDetail;
    private String paperName;
    private String knowledgeId;
    private String knowledgeName;
    private String teacherId;
    private String teacherName;
    private String questionTotal;

    public PaperAnwserDetail getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(PaperAnwserDetail answerDetail) {
        this.answerDetail = answerDetail;
    }

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

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getQuestionTotal() {
        return questionTotal;
    }

    public void setQuestionTotal(String questionTotal) {
        this.questionTotal = questionTotal;
    }
}
