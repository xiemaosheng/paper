package com.nd.paper.service;

import com.nd.paper.model.PaperEntity;
import com.nd.paper.model.PaperNavigation;
import com.nd.paper.model.PaperResp;
import com.nd.question.model.PageResp;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public interface IPaperService {
    void addPaper(PaperEntity paperEntity);

    /**
     * 老师获取试卷列表
     *
     * @param teacherId
     * @param courseId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResp<PaperNavigation> getTeachersPapers(String teacherId, String courseId, Integer pageNum, Integer pageSize);

    /**
     * 老师查看试卷作答详情
     *
     * @param paperId
     * @return
     */
    PaperResp getPaperDetail(String paperId);

    /**
     * 学生获取试卷列表
     *
     * @param courseId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageResp getStudentsPapers(String studentId, String courseId, Integer pageNum, Integer pageSize);
}
