package com.nd.answer.repository;

import com.nd.answer.model.PaperAnswerEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/15 0015.
 */
public interface PaperAnswerRepository extends JpaRepository<PaperAnswerEntity, String> {
    /**
     * 已经作答
     *
     * @param paperId
     * @return
     */
    List<PaperAnswerEntity> findByPaperIdAndResultIsNotNull(String paperId);

    /**
     * 未作答
     *
     * @param paperId
     * @return
     */
    List<PaperAnswerEntity> findByPaperIdAndResultIsNull(String paperId);

    /**
     * 定位一个学生的某一个试卷
     *
     * @param paperId
     * @param userId
     * @return
     */
    PaperAnswerEntity findByPaperIdAndUserId(String paperId, String userId);

    /**
     * 获取一套试卷的，给定学生集合的作答列表
     * @param paperId
     * @param userIds
     * @return
     */
    List<PaperAnswerEntity> findByPaperIdAndUserIdIn(String paperId, Set<String> userIds);
}
