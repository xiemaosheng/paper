package com.nd.answer.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.nd.answer.model.*;
import com.nd.answer.repository.PaperAnswerRepository;
import com.nd.answer.service.IPaperAnswerService;
import com.nd.common.exception.NiceException;
import com.nd.knowledge.model.KnowledgeEntity;
import com.nd.knowledge.repository.KnowledgeRepository;
import com.nd.paper.dao.PaperRepository;
import com.nd.paper.model.PaperEntity;
import com.nd.question.model.QuestionEntity;
import com.nd.question.repository.QuestionRepository;
import com.nd.uc.repository.UserEntity;
import com.nd.user.dao.UserRepository;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
@Service
public class PaperAnswerServiceImpl implements IPaperAnswerService {
    @Autowired
    private PaperAnswerRepository paperAnswerRepository;
    @Autowired
    private PaperRepository paperRepository;
    @Autowired
    private KnowledgeRepository knowledgeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;

    /**
     * 初始化试卷答题表
     *
     * @param paperEntity
     * @return
     */
    @Override
    public void initPaperAnswer(PaperEntity paperEntity) {
        List<String> uidList = paperEntity.getUserItems();
        String paperId = paperEntity.getPaperId();
        List<PaperAnswerEntity> list = new ArrayList<>();
        for (String uid : uidList) {
            PaperAnswerEntity paperAnswerEntity = new PaperAnswerEntity();
            paperAnswerEntity.setPaperId(paperId);
            paperAnswerEntity.setUserId(uid);
            list.add(paperAnswerEntity);
        }
        paperAnswerRepository.save(list);
    }

    /**
     * 返回试卷答题信息
     *
     * @param paperId
     * @param teacherId
     * @return
     */
    @Override
    public AnswerResp respPaperAnswer2User(String paperId, String teacherId) {
        PaperEntity paperEntity = paperRepository.findOne(paperId);
        if (ObjectUtils.isEmpty(paperEntity)) {
            throw new NiceException("PAPER_NOT_EXIT", "试卷不存在");
        }
        String knowledgeId = paperEntity.getKnowledgeCode();
        List<QuestionEntity> questionEntityList = paperEntity.getQuestionEntityItems();
        KnowledgeEntity knowledgeEntity = knowledgeRepository.findOne(knowledgeId);
        if (ObjectUtils.isEmpty(knowledgeEntity)) {
            throw new NiceException("KNOWLEDGE_NOT_EXIT", "知识点不存在");
        }
        UserEntity teacher = userRepository.findOne(teacherId);
        if (ObjectUtils.isEmpty(teacher)) {
            throw new NiceException("USER_NOT_EXIT", "用户信息不存在");
        }
        String paperName = paperEntity.getPaperName();
        String knowledgeName = knowledgeEntity.getKnowledgeName();
        String teacherName = teacher.getRealName();
        DateTime dt2 = new DateTime(paperEntity.getCreateDate());
        String startTime = dt2.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        int questionTotal = questionEntityList.size();
        List<QuestionResp> questionsSingle = new ArrayList<>();
        List<QuestionResp> questionsMulti = new ArrayList<>();
        for (QuestionEntity questionEntity : questionEntityList) {
            String questionType = questionEntity.getQuestionType();
            QuestionResp questionResp = new QuestionResp(questionEntity.getQuestionId(), questionEntity.getQuestionTitle(), questionEntity.getChioceItems());
            if (null != questionType && "single".equals(questionType)) {

                questionsSingle.add(questionResp);
            } else {
                questionsMulti.add(questionResp);
            }
        }
        AnswerResp answerResp = new AnswerResp(paperId, paperName, knowledgeId,
                knowledgeName, teacherId, teacherName, startTime,
                questionTotal, questionsSingle, questionsMulti);
        return answerResp;
    }

    /**
     * 提交答题结果时更新试卷答题表
     *
     * @param questionAnswerReq
     * @param uid
     * @param paperId
     * @return
     */
    @Override
    public void updatePaperAnswerResult(QuestionAnswerReq questionAnswerReq, String uid, String paperId) {
        List<QuestionAnswer> questionAnswerList = questionAnswerReq.getAnswers();
        PaperEntity paperEntity = paperRepository.findOne(paperId);
        if (ObjectUtils.isEmpty(paperEntity)) {
            throw new NiceException("PAPER_NOT_EXIT", "试卷不存在");
        }
        int i = 0;
        for (QuestionAnswer questionAnswer : questionAnswerList) {
            QuestionEntity questionEntity = questionRepository.findOne(questionAnswer.getQuestionId());
            if (ObjectUtils.isEmpty(questionEntity)) {
                throw new NiceException("QUESTION_NOT_EXIT", "题目不存在");
            }
            List<String> rightAnswer = questionEntity.getAnswer();
            List<String> submitAnswer = questionAnswer.getAnswer();
            if (validate(rightAnswer, submitAnswer)) {
                i++;
            }

        }
        String result = JSON.toJSONString(questionAnswerList);
        PaperAnswerEntity paperAnswerEntity = new PaperAnswerEntity();
        Date date = new Date();
        paperAnswerEntity.setResult(result);
        paperAnswerEntity.setUserId(uid);
        paperAnswerEntity.setPaperId(paperId);
        paperAnswerEntity.setAnswerTime(date);
        paperAnswerEntity.setTotalScore(i + "/" + paperEntity.getQuestionEntityItems().size());
        paperAnswerRepository.save(paperAnswerEntity);
    }

    /**
     * 返回指定uid答题结果信息
     *
     * @param paperId
     * @param uid
     * @return
     */
    @Override
    public AnswerResultResp queryAnswerResultInfo(String paperId, String uid) {
        PaperEntity paperEntity = paperRepository.findOne(paperId);
        if (ObjectUtils.isEmpty(paperEntity)) {
            throw new NiceException("PAPER_NOT_EXIT", "试卷不存在");
        }
        KnowledgeEntity knowledgeEntity = knowledgeRepository.findOne(paperEntity.getKnowledgeCode());
        if (ObjectUtils.isEmpty(knowledgeEntity)) {
            throw new NiceException("KNOWLEDGE_NOT_EXIT", "知识点不存在");
        }
        UserEntity userEntity = userRepository.findOne(paperEntity.getTeacherId());
        if (ObjectUtils.isEmpty(userEntity)) {
            throw new NiceException("USER_NOT_EXIT", "用户信息不存在");
        }
        PaperAnswerEntity paperAnswerEntity = paperAnswerRepository.findByPaperIdAndUserId(paperId, uid);
        if (ObjectUtils.isEmpty(paperAnswerEntity)) {
            throw new NiceException("PAPER_ANSWER_NOT_EXIT", "指定用户答题信息不存在");
        }

        String paperName = paperEntity.getPaperName();
        String knowledgeCode = knowledgeEntity.getKnowledgeId();
        String knowledgeName = knowledgeEntity.getKnowledgeName();
        String teacherId = userEntity.getUserId();
        String teacherName = userEntity.getRealName();
        DateTime dateTime1 = new DateTime(paperEntity.getCreateDate());
        DateTime dateTime2 = new DateTime(paperAnswerEntity.getAnswerTime());
        String startTime = dateTime1.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        String submitTime = dateTime2.toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
        String score = paperAnswerEntity.getTotalScore();
        int questionTotal = paperEntity.getQuestionEntityItems().size();

        List<QuestionResultResp> questionsSingle = new ArrayList<>();
        List<QuestionResultResp> questionsMulti = new ArrayList<>();
        for (QuestionEntity questionEntity : paperEntity.getQuestionEntityItems()) {
            String questionType = questionEntity.getQuestionType();
            String result = paperAnswerEntity.getResult();
            List<QuestionAnswer> list = JSON.parseObject(result, new TypeReference<ArrayList<QuestionAnswer>>() {
            });
            List<String> answerList = new ArrayList<>();
            for (QuestionAnswer questionAnswer : list) {

                if (questionEntity.getQuestionId().equals(questionAnswer.getQuestionId())) {
                    answerList = questionAnswer.getAnswer();
                }
            }

            QuestionResultResp QuestionResultResp = new QuestionResultResp(questionEntity.getQuestionId(), questionEntity.getQuestionTitle(), questionEntity.getChioceItems(),
                    questionEntity.getAnswer(), answerList, questionEntity.getAnalysis());
            if (null != questionType && "single".equals(questionType)) {

                questionsSingle.add(QuestionResultResp);
            } else {
                questionsMulti.add(QuestionResultResp);
            }
        }

        AnswerResultResp answerResultResp = new AnswerResultResp(paperId, paperName, knowledgeCode, knowledgeName, teacherId, teacherName,
                startTime, submitTime, score, questionTotal, questionsSingle, questionsMulti);

        return answerResultResp;
    }

    private boolean validate(List<String> rightAnswer, List<String> submitAnswer) {
        boolean flag = true;
        if (rightAnswer.size() == submitAnswer.size()) {
            for (String submit : submitAnswer) {
                if (!rightAnswer.contains(submit)) {
                    flag = false;
                }
            }
        } else {
            flag = false;
        }
        return flag;
    }
}
