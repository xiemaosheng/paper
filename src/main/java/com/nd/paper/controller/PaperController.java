package com.nd.paper.controller;

import com.nd.answer.service.IPaperAnswerService;
import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.paper.model.PaperEntity;
import com.nd.paper.model.PaperReq;
import com.nd.paper.model.PaperResp;
import com.nd.paper.service.IPaperService;
import com.nd.paper.service.impl.PaperServiceImpl;
import com.nd.question.model.PageResp;
import com.nd.question.service.QuestionService;
import com.nd.util.PageReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/15 0015.
 */
@RestController
@RequestMapping(value = "/v1")
public class PaperController {
    @Autowired
    private IPaperService iPaperService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private IPaperAnswerService paperAnswerService;

    @Autowired
    private PaperServiceImpl paperService;


    /**
     * 老师获取试卷列表
     *
     * @param teacherId
     * @param courseId
     */
    @GetMapping(value = "/teachers/papers")
    public PageResp getTeachersPapers(@RequestParam(value = "teacher_id") String teacherId,
                                      @RequestParam(value = "course_id") String courseId,
                                      PageReq pageReq) {
        return iPaperService.getTeachersPapers(teacherId, courseId, pageReq.getPage_num(), pageReq.getPage_size());
    }

    /**
     * 学生获取试卷列表
     *
     * @param userInfo
     * @param courseId
     * @return
     */
    @GetMapping(value = "/students/papers")
    public PageResp getStudentsPapers(@AuthenticationPrincipal UserInfo userInfo,
                                      @RequestParam(value = "course_id") String courseId,
                                      PageReq pageReq) {
        return iPaperService.getStudentsPapers(userInfo.getUserId(), courseId, pageReq.getPage_num(), pageReq.getPage_size());
    }

    /**
     * 老师查看作试卷的答详情
     *
     * @param paperId
     * @return
     */
    @GetMapping(value = "teachers/papers/{paper_id}")
    public PaperResp getPaperDetail(@PathVariable(value = "paper_id") String paperId) {
        return iPaperService.getPaperDetail(paperId);
    }


    /**
     * 分发试卷
     *
     * @param userInfo
     * @param paperReq
     * @return
     */
    @PostMapping(value = "/teachers/actions/sendPaper")
    public void sendPaper(@AuthenticationPrincipal UserInfo userInfo, @RequestBody PaperReq paperReq) {
        PaperEntity paperEntity = questionService.getPaper2Req(paperReq,userInfo.getUserId());
        paperService.addPaper(paperEntity);
        paperAnswerService.initPaperAnswer(paperEntity);
    }

}
