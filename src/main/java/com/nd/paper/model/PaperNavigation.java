package com.nd.paper.model;

import java.util.Date;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/15 0015.
 */
public class PaperNavigation {
    private String paperId;

    private String paperName;

    private Integer distribute;

    private Integer answered;

    private Boolean isSend;

    private Date createDate;

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

    public Integer getDistribute() {
        return distribute;
    }

    public void setDistribute(Integer distribute) {
        this.distribute = distribute;
    }

    public Integer getAnswered() {
        return answered;
    }

    public void setAnswered(Integer answered) {
        this.answered = answered;
    }

    public Boolean getSend() {
        return isSend;
    }

    public void setSend(Boolean send) {
        isSend = send;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
