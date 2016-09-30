package com.nd.paper.dao;

import com.nd.paper.model.PaperEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@Repository(value = "paperDao")
public class PaperDao {
    @Resource
    private MongoTemplate mongoTemplate;

    private final static String DB_NAME = "paper";

    public void save(PaperEntity paperEntity) {
        mongoTemplate.save(paperEntity, DB_NAME);
    }
}
