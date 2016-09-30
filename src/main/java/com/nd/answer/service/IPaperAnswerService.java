package com.nd.answer.service;

import com.nd.answer.model.AnswerResp;
import com.nd.answer.model.AnswerResultResp;
import com.nd.answer.model.QuestionAnswerReq;
import com.nd.paper.model.PaperEntity;

/**
 * Created by Administrator
 * on 2016/9/16 0016.
 */
public interface IPaperAnswerService {

    void initPaperAnswer(PaperEntity paperEntity);

    AnswerResp respPaperAnswer2User(String paperId, String teacherId);

    void updatePaperAnswerResult(QuestionAnswerReq questionAnswerReq, String uid, String paperId);

    AnswerResultResp queryAnswerResultInfo(String paperId, String uid);
}
