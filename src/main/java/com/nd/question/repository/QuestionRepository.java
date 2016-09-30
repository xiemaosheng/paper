package com.nd.question.repository;

import com.nd.question.model.QuestionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Set;


/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0011.
 */
public interface QuestionRepository extends MongoRepository<QuestionEntity, String> {
    Page<QuestionEntity> findByKnowledge(String knowledge, Pageable pageable);

    /**
     * 查询该非集
     *
     * @param anwseredQuestionIdSet
     * @return
     */
    List<QuestionEntity> findByKnowledgeAndQuestionIdNotIn(String knowledge, Set<String> anwseredQuestionIdSet);

    /**
     * 通过questions id的集合查询问题
     * @param questionIdSet
     * @return
     */
    List<QuestionEntity> findByQuestionIdIn(Set<String> questionIdSet);
}
