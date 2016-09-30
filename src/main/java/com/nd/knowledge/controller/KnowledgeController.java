package com.nd.knowledge.controller;

import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.knowledge.model.KnowledgeResp;
import com.nd.knowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
@RestController
@RequestMapping(value = "/v1/teachers")
public class KnowledgeController {

    @Autowired
    private KnowledgeService knowledgeService;

    @GetMapping(value = "/courses/{course_id}/knowledges")
    public List<KnowledgeResp> getKnowledges(@AuthenticationPrincipal UserInfo userInfo,
                                             @PathVariable(value = "course_id") String courseId) {
        return knowledgeService.findKnowledges(courseId);
    }
}
