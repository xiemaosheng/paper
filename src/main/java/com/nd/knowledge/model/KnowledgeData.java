package com.nd.knowledge.model;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
public class KnowledgeData {
    private String knowledgeId;
    private String knowledgeName;
    private String sonKnowledgeId;
    private String sonKnowledgeName;
    public KnowledgeData(){

    }
    public KnowledgeData(String knowledgeId, String knowledgeName, String sonKnowledgeId, String sonKnowledgeName) {
        this.knowledgeId = knowledgeId;
        this.knowledgeName = knowledgeName;
        this.sonKnowledgeId = sonKnowledgeId;
        this.sonKnowledgeName = sonKnowledgeName;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getKnowledgeId() {
        return knowledgeId;
    }

    public void setKnowledgeId(String knowledgeId) {
        this.knowledgeId = knowledgeId;
    }

    public String getSonKnowledgeId() {
        return sonKnowledgeId;
    }

    public void setSonKnowledgeId(String sonKnowledgeId) {
        this.sonKnowledgeId = sonKnowledgeId;
    }

    public String getSonKnowledgeName() {
        return sonKnowledgeName;
    }

    public void setSonKnowledgeName(String sonKnowledgeName) {
        this.sonKnowledgeName = sonKnowledgeName;
    }
}
