package com.nd.knowledge.model;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
public class KnowledgeResp {
    private String knowledgeId;
    private String knowledgeName;
    private List<KnowledgeChild> childKnowledge;

    public KnowledgeResp() {
    }

    public KnowledgeResp(String knowledgeId, List<KnowledgeChild> childKnowledge, String knowledgeName) {
        this.knowledgeId = knowledgeId;
        this.childKnowledge = childKnowledge;
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

    public List<KnowledgeChild> getChildKnowledge() {
        return childKnowledge;
    }

    public void setChildKnowledge(List<KnowledgeChild> childKnowledge) {
        this.childKnowledge = childKnowledge;
    }
}
