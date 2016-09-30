package com.nd.knowledge.repository;

import com.nd.knowledge.model.KnowledgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
public interface KnowledgeRepository extends JpaRepository<KnowledgeEntity, String>, KnowledgeRepositoryCustom {
    List<KnowledgeEntity> findByParentId(String parentId);

    List<KnowledgeEntity> findByCourse(String course);

}
