package com.nd.answer.controller;

import com.nd.answer.model.QuestionAnswerReq;
import com.nd.answer.service.impl.PaperAnswerServiceImpl;
import com.nd.gaea.rest.security.authens.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator
 * on 2016/9/16 0016.
 */
@RestController
@RequestMapping(value = "/v1/students/papers")
public class AnswerController {
    @Autowired
    private PaperAnswerServiceImpl paperAnswerService;

    /** 学生获取试题
     * @param userInfo
     * @param paperId
     * @return
     */
    @GetMapping(value = "/{paper_id}/actions/answer")
    public Object getAnswer(@AuthenticationPrincipal UserInfo userInfo, @PathVariable("paper_id") String paperId) {
        return paperAnswerService.respPaperAnswer2User(paperId, userInfo.getUserId());
    }

    /** 学生提交作答
     * @param userInfo
     * @param questionAnswerReq
     * @param paperId
     */
    @PostMapping(value = "/papers/{paper_id}/actions/submit")
    public void submitAnswer(@AuthenticationPrincipal UserInfo userInfo, @RequestBody QuestionAnswerReq questionAnswerReq,
                             @PathVariable("paper_id") String paperId) {
        paperAnswerService.updatePaperAnswerResult(questionAnswerReq, userInfo.getUserId(), paperId);
    }

    /** 查看做题结果
     * @param userInfo
     * @param paperId
     * @return
     */
    @GetMapping(value = "/papers/{paper_id}")
    public Object queryPaperAnswerResultInfo(@AuthenticationPrincipal UserInfo userInfo,@PathVariable("paper_id") String paperId) {
        return paperAnswerService.queryAnswerResultInfo(paperId,userInfo.getUserId());
    }
}
