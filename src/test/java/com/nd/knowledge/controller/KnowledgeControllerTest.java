package com.nd.knowledge.controller;

import com.nd.knowledge.model.KnowledgeResp;
import com.nd.knowledge.service.KnowledgeService;
import com.nd.knowledge.service.impl.KnowledgeServiceImpl;
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
public class KnowledgeControllerTest {
    @Spy
    @InjectMocks
    KnowledgeController knowledgeController = new KnowledgeController();

    @Mock
    KnowledgeService knowledgeService = new KnowledgeServiceImpl();

    @Test
    public void getKnowledges() throws Exception {

        List<KnowledgeResp> list=new ArrayList<>();
        when(knowledgeService.findKnowledges("12")).thenReturn(list);
        List<KnowledgeResp> actual=knowledgeController.getKnowledges(null,"12");
        Assert.assertEquals(actual,list);
    }

}