package com.nd.knowledge.repository;

import com.nd.knowledge.model.KnowledgeData;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
public interface KnowledgeRepositoryCustom {
    List<KnowledgeData> findByCourseToData(String course);
}
