package com.nd.knowledge.model;

import javax.persistence.*;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
@Entity
@Table(name = "t_knowledge")
public class KnowledgeEntity {
    private String knowledgeId;
    private String knowledgeName;
    private String parentId;
    private String course;
    private String createDate;

    @Id
    @Column(name = "knowledge_id")
    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    @Basic
    @Column(name = "knowledge_name")
    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    @Basic
    @Column(name = "parent_id")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "course")
    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Basic
    @Column(name = "create_date")
    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}







