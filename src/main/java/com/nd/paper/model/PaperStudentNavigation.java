package com.nd.paper.model;

import java.util.Date;

/**
 * 学生试卷列表的响应实体
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public class PaperStudentNavigation {
    private String paperId;
    private String paperName;
    private Date startTime;
    private Date answerTime;
    private Boolean isSend;
    private String score;

    public PaperStudentNavigation() {

    }

    public PaperStudentNavigation(String paperId, String paperName, Date startTime, Date answerTime, Boolean isSend, String score) {
        this.paperId = paperId;
        this.paperName = paperName;
        this.startTime = startTime;
        this.answerTime = answerTime;
        this.isSend = isSend;
        this.score = score;
    }

    public String getPaperId() {
        return paperId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean send) {
        isSend = send;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
