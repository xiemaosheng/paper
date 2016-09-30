package com.nd.knowledge.service.impl;

import com.nd.knowledge.model.KnowledgeData;
import com.nd.knowledge.model.KnowledgeResp;
import com.nd.knowledge.repository.KnowledgeRepository;
import com.nd.knowledge.service.KnowledgeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/25 0025.
 */
@RunWith(MockitoJUnitRunner.class)
public class KnowledgeServiceImplTest {
    @Spy
    @InjectMocks
    KnowledgeService knowledgeService = new KnowledgeServiceImpl();

    @Mock
    KnowledgeRepository knowledgeRepository;


    @Test
    public void testFindKnowledges() {
        List<KnowledgeData> knowledgeDataList = new ArrayList<>();
        KnowledgeData knowledgeData = new KnowledgeData("23623656", "23623656", "23623656", "23623656");
        KnowledgeData knowledgeDataT = new KnowledgeData("23623656", "23623656", "23623656", "23623656");
        knowledgeDataList.add(knowledgeData);
        knowledgeDataList.add(knowledgeDataT);
        when(knowledgeRepository.findByCourseToData("12")).thenReturn(knowledgeDataList);
        List<KnowledgeResp> actual=knowledgeService.findKnowledges("12");
        Assert.assertNotNull(actual);

    }

}