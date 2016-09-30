package com.nd.knowledge.model;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
public class KnowledgeChild {
    private String knowledgeId;
    private String knowledgeName;

    public KnowledgeChild() {
    }

    public KnowledgeChild(String knowledgeId, String knowledgeName) {
        this.knowledgeId = knowledgeId;
        this.knowledgeName = knowledgeName;
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
}
