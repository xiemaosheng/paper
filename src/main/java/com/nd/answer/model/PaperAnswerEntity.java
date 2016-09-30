package com.nd.answer.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/15 0015.
 */
@IdClass(PaperAnswerEntity.UserAndPaper.class)
@Entity
@Table(name = "t_paper_answer")
public class PaperAnswerEntity implements Serializable {
    private String userId;
    private String paperId;
    private String result;
    private String totalScore;
    private Date answerTime;
    private String resource;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setTotalScore(String totalScore) {
        this.totalScore = totalScore;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    @Id
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    @Id
    @Column(name = "paper_id")
    public String getPaperId() {
        return paperId;
    }

    @Column(name = "result")
    public String getResult() {
        return result;
    }

    @Column(name = "total_score")
    public String getTotalScore() {
        return totalScore;
    }

    @Column(name = "answer_time")
    public Date getAnswerTime() {
        return answerTime;
    }

    @Column(name = "resource")
    public String getResource() {
        return resource;
    }

    public PaperAnswerEntity() {
    }

    public PaperAnswerEntity(String userId, String paperId,String result, String totalScore, Date answerTime,String resource) {
        this.userId = userId;
        this.paperId = paperId;
        this.result = result;
        this.totalScore = totalScore;
        this.answerTime = answerTime;
        this.resource = resource;
    }

    @Embeddable
    static class UserAndPaper implements Serializable {
        @Column(name = "user_id")
        private String userId;

        @Column(name = "paper_id")
        private String paperId;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof UserAndPaper)) return false;
            UserAndPaper that = (UserAndPaper) o;
            return Objects.equals(userId, that.userId) &&
                    Objects.equals(paperId, that.paperId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(userId, paperId);
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getPaperId() {
            return paperId;
        }

        public void setPaperId(String paperId) {
            this.paperId = paperId;
        }
    }
}

