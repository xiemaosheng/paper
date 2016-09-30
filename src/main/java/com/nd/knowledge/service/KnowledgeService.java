package com.nd.knowledge.service;

import com.nd.knowledge.model.KnowledgeResp;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
public interface KnowledgeService {
    List<KnowledgeResp> findKnowledges(String courseId);
}
