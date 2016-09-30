package com.nd.resource.controller;

import com.nd.gaea.rest.security.authens.UserInfo;
import com.nd.question.model.QuestionEntity;
import com.nd.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
@RestController
@RequestMapping(value = "/v1")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    /**
     * 老师通过给定的学生id集合和试卷推荐资源给学生
     *
     * @param paperId
     * @param userIds
     */
    @RequestMapping(value = "/teachers/papers/{paper_id}/actions/push", method = RequestMethod.POST)
    public void pushResource(@PathVariable("paper_id") String paperId, @RequestBody Set<String> userIds) {
        resourceService.pushResource(paperId, userIds);
    }

    /**
     * 学生查看指定的试卷的推荐资源
     * @param userInfo
     * @param paperId
     * @return
     */
    @RequestMapping(value = "/students/resources/{paper_id}", method = RequestMethod.GET)
    public Map<String, List<QuestionEntity>> checkMyResources(@AuthenticationPrincipal UserInfo userInfo, @PathVariable("paper_id") String paperId) {
        return resourceService.checkMyResources(userInfo.getUserId(), paperId);
    }
}
