package com.nd.resource.service;

import com.nd.question.model.QuestionEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public interface ResourceService {
    /**
     * 通过给定的学生和试卷推荐资源
     *
     * @param paperId
     * @param userIds
     */
    void pushResource(String paperId, Set<String> userIds);

    /**
     * 学生查看指定的试卷的推荐资源
     *
     * @param userId
     * @param paperId
     * @return
     */
    Map<String, List<QuestionEntity>> checkMyResources(String userId, String paperId);
}
