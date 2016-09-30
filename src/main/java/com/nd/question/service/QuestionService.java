package com.nd.question.service;

import com.nd.paper.model.PaperEntity;
import com.nd.paper.model.PaperReq;
import com.nd.question.model.PageResp;
import com.nd.question.model.QuestionEntity;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/11 0011.
 */
public interface QuestionService {
    Object uploadQuestions(XSSFWorkbook wb, String userId);

    PageResp getQuestions(String knowledge, Integer pageNum, Integer limit);

    PaperEntity getPaper2Req(PaperReq paperReq,String teacherId);
}
