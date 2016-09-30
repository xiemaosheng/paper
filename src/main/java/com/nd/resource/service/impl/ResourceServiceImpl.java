package com.nd.resource.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.nd.answer.model.PaperAnswerEntity;
import com.nd.answer.repository.PaperAnswerRepository;
import com.nd.common.enums.ResourceEnum;
import com.nd.common.utils.RandomTools;
import com.nd.paper.dao.PaperRepository;
import com.nd.paper.model.PaperEntity;
import com.nd.question.model.QuestionEntity;
import com.nd.question.repository.QuestionRepository;
import com.nd.resource.service.ResourceService;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private PaperAnswerRepository paperAnswerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public void pushResource(String paperId, Set<String> userIds) {
        if (paperId != null) {
            PaperEntity paperEntity = paperRepository.findOne(paperId);
            if (paperEntity != null) {
                //该知识点下的已经做了的题目
                List<QuestionEntity> list = paperEntity.getQuestionEntityItems();
                Set<String> answeredQuestionIdSet = new HashSet<>();
                for (QuestionEntity qe : list) {
                    answeredQuestionIdSet.add(qe.getQuestionId());
                }
                String knowledge = paperEntity.getKnowledgeCode();
                //该知识点下的去除掉已经推荐过的的题目，剩下没有推荐的题目。
                Set<String> questionIds = new HashSet<>();//随机生成要推荐题目id集合
                List<QuestionEntity> questionEntityList = questionRepository.findByKnowledgeAndQuestionIdNotIn(knowledge, answeredQuestionIdSet);
                if (questionEntityList != null) {
                    if (questionEntityList.size() <= ResourceEnum.Three.getResourceNum()) {
                        for (QuestionEntity qe : questionEntityList) {
                            questionIds.add(qe.getQuestionId());
                        }
                    } else {
                        //随机生成的推荐题目下标集合
                        Set<Integer> questionIndexIds = RandomTools.randomPush(0, questionEntityList.size());
                        for (Integer index : questionIndexIds) {
                            questionIds.add(questionEntityList.get(index).getQuestionId());
                        }
                    }
                }
                List<PaperAnswerEntity> paperAnswerEntityList = paperAnswerRepository.findByPaperIdAndUserIdIn(paperId, userIds);
                List<Object> questionIdsList = new ArrayList<>();
                for (String str : questionIds) {
                    questionIdsList.add(str);
                }
                //将推荐的题目集合转换成Json数据
                JSONArray jSONArray = new JSONArray(questionIdsList);
                for (PaperAnswerEntity pae : paperAnswerEntityList) {
                    pae.setResource(jSONArray.toJSONString());
                }
                //将推荐的资源更新到数据库
                paperAnswerRepository.save(paperAnswerEntityList);
            }
        }

    }

    @Override
    public Map<String, List<QuestionEntity>> checkMyResources(String userId, String paperId) {
        Map<String, List<QuestionEntity>> map = new HashedMap<>();
        PaperAnswerEntity paperAnswerEntity = null;
        List<QuestionEntity> questionEntityList = null;
        Set<String> questionsIds = new HashSet<>();
        if (paperId != null) {
            paperAnswerEntity = paperAnswerRepository.findByPaperIdAndUserId(paperId, userId);
        }
        if (paperAnswerEntity != null) {
            String resources = paperAnswerEntity.getResource();
            if (resources != null && StringUtils.isNotBlank(resources)) {
                JSONArray jsonArray = JSONArray.parseArray(resources);
                Object[] oo = jsonArray.toArray();
                for (int i = 0; i < oo.length; i++) {
                    questionsIds.add((String) oo[i]);
                }
            }
        }
        if (questionsIds.size() > 0) {
            questionEntityList = questionRepository.findByQuestionIdIn(questionsIds);
        }
        map.put("resources", questionEntityList);
        return map;
    }
}
