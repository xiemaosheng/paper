package com.nd.knowledge.repository;

import com.nd.knowledge.model.KnowledgeData;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
@Repository
public class KnowledgeRepositoryImpl implements KnowledgeRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<KnowledgeData> findByCourseToData(String course) {
        String hql = "SELECT a.knowledge_id as knowledgeId ,a.knowledge_name as knowledgeName,b.knowledge_id  as sonKnowledgeId,b.knowledge_name as sonKnowledgeName FROM t_knowledge a Inner  JOIN t_knowledge b  on a.knowledge_id=b.parent_id  where a.course=:course";
        Query query = entityManager.createNativeQuery(hql);
        query.setParameter("course", course);
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.aliasToBean(KnowledgeData.class));
        return query.getResultList();
    }
}
