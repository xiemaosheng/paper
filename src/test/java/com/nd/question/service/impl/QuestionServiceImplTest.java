package com.nd.question.service.impl;

import com.nd.question.repository.QuestionRepository;
import com.nd.question.service.QuestionService;
import com.nd.teacher.service.IClassService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/26 0026.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuestionServiceImplTest {

    @Spy
    @InjectMocks
    QuestionService questionService = new QuestionServiceImpl();

    @Mock
    QuestionRepository questionRepository;

    @Mock
    private IClassService classService;

    @Test
    public void uploadQuestions() throws Exception {

    }

    @Test
    public void getQuestions() throws Exception {
//        List<QuestionEntity> list = new ArrayList<>();
//        QuestionEntity qe = new QuestionEntity();
//        list.add(qe);
//        Page<QuestionEntity> page= new PageImpl(list,new PageRequest(0,10),10);
//        when(questionRepository.findByKnowledge("", new PageRequest(1, 10))).thenReturn(page);
//        PageResp pageResp= questionService.getQuestions("",0,10);
    }

    @Test
    public void getPaper2Req() throws Exception {

    }

}