package com.nd.knowledge.service.impl;

import com.nd.knowledge.model.KnowledgeChild;
import com.nd.knowledge.model.KnowledgeData;
import com.nd.knowledge.model.KnowledgeResp;
import com.nd.knowledge.repository.KnowledgeRepository;
import com.nd.knowledge.service.KnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/12 0012.
 */
@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    KnowledgeRepository knowledgeRepository;

    @Override
    public List<KnowledgeResp> findKnowledges(String courseId) {
        List<KnowledgeData> knowledgeDataList = knowledgeRepository.findByCourseToData(courseId);
        Map<String, KnowledgeResp> knowledgeRespMap = new HashMap<>();
        List<KnowledgeResp> knowledgeRespList = new ArrayList<>();
        for (KnowledgeData knowledgeData : knowledgeDataList) {
            KnowledgeResp knowledgeResp = null;
            if (knowledgeRespMap.get(knowledgeData.getKnowledgeId()) == null) {
                knowledgeResp = new KnowledgeResp();
                knowledgeResp.setKnowledgeId(knowledgeData.getKnowledgeId());
                knowledgeResp.setKnowledgeName(knowledgeData.getKnowledgeName());
                List<KnowledgeChild> childKnowledge = new ArrayList<>();
                KnowledgeChild knowledgeChild = new KnowledgeChild();
                knowledgeChild.setKnowledgeId(knowledgeData.getSonKnowledgeId());
                knowledgeChild.setKnowledgeName(knowledgeData.getSonKnowledgeName());
                childKnowledge.add(knowledgeChild);
                knowledgeResp.setChildKnowledge(childKnowledge);
                knowledgeRespMap.put(knowledgeData.getKnowledgeId(), knowledgeResp);
            } else {
                knowledgeResp = knowledgeRespMap.get(knowledgeData.getKnowledgeId());
                List<KnowledgeChild> childKnowledge = knowledgeResp.getChildKnowledge();
                KnowledgeChild knowledgeChild = new KnowledgeChild(knowledgeData.getSonKnowledgeId(), knowledgeData.getSonKnowledgeName());
                childKnowledge.add(knowledgeChild);
            }
        }
        for (Map.Entry<String, KnowledgeResp> en : knowledgeRespMap.entrySet()) {
            knowledgeRespList.add(en.getValue());
        }
        return knowledgeRespList;
    }
}
