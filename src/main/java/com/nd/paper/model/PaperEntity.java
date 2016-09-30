package com.nd.paper.model;

import com.nd.question.model.QuestionEntity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 试卷实体类
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
@Document
public class PaperEntity implements Serializable {
    private static final long serialVersionUID = 1918110277487872614L;

    @Id
    private String paperId;

    private String paperName;

    private List<QuestionEntity> questionEntityItems;

    private List<String> userItems;

    private String knowledgeCode;

    private String teacherId;

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

    public List<QuestionEntity> getQuestionEntityItems() {
        return questionEntityItems;
    }

    public void setQuestionEntityItems(List<QuestionEntity> questionEntityItems) {
        this.questionEntityItems = questionEntityItems;
    }

    public List<String> getUserItems() {
        return userItems;
    }

    public void setUserItems(List<String> userItems) {
        this.userItems = userItems;
    }

    public String getKnowledgeCode() {
        return knowledgeCode;
    }

    public void setKnowledgeCode(String knowledgeCode) {
        this.knowledgeCode = knowledgeCode;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}

