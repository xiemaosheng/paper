package com.nd.paper.dao;

import com.nd.paper.model.PaperEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Set;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/15 0015.
 */
public interface PaperRepository extends MongoRepository<PaperEntity, String>,PaperRepositoryCustom {
    Page<PaperEntity> findByTeacherIdAndKnowledgeCodeIn(String teacherId, Set<String> knowledgeIds, Pageable pageable);
}
