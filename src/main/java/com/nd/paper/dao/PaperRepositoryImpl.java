package com.nd.paper.dao;

import com.nd.paper.model.PaperEntity;
import com.nd.question.model.PageResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public class PaperRepositoryImpl implements PaperRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public PageResp<PaperEntity> findByStudentIdContainAndKnowledgeCodeIn(String studentId, Set<String> knowledgeIds, Pageable pageable) {
        Set<String> studentIds = new HashSet<>();
        studentIds.add(studentId);
        Query query = new Query();
        query.addCriteria(where("knowledgeCode").in(knowledgeIds));
        query.addCriteria(where("userItems").all(studentIds));
        Long total = mongoTemplate.count(query, PaperEntity.class);
        query.skip(pageable.getOffset()).limit(pageable.getPageSize());
        List<PaperEntity> paperEntityList = mongoTemplate.find(query, PaperEntity.class);


        return new PageResp<PaperEntity>(total, paperEntityList);
    }
}
