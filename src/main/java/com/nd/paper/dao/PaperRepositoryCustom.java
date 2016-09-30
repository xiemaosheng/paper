package com.nd.paper.dao;

import com.nd.paper.model.PaperEntity;
import com.nd.question.model.PageResp;
import org.springframework.data.domain.Pageable;

import java.util.Set;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public interface PaperRepositoryCustom {
    PageResp<PaperEntity> findByStudentIdContainAndKnowledgeCodeIn(String studentId, Set<String> knowledgeIds, Pageable pageable);
}
