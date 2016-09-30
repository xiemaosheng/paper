package com.nd.paper.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nd.answer.model.PaperAnswerEntity;
import com.nd.answer.repository.PaperAnswerRepository;
import com.nd.knowledge.model.KnowledgeEntity;
import com.nd.knowledge.repository.KnowledgeRepository;
import com.nd.paper.dao.PaperRepository;
import com.nd.paper.model.*;
import com.nd.paper.service.IPaperService;
import com.nd.question.model.PageResp;
import com.nd.uc.repository.UserEntity;
import com.nd.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@Service
public class PaperServiceImpl implements IPaperService {

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private KnowledgeRepository knowledgeRepository;

    @Autowired
    private PaperAnswerRepository paperAnswerRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void addPaper(PaperEntity paperEntity) {
        paperRepository.save(paperEntity);
    }

    @Override
    public PageResp<PaperNavigation> getTeachersPapers(String teacherId, String courseId, Integer pageNum, Integer pageSize) {
        List<KnowledgeEntity> KnowledgeEntityList = knowledgeRepository.findByCourse(courseId);
        Set<String> knowledgeIds = new HashSet<>();
        for (KnowledgeEntity kde : KnowledgeEntityList) {
            knowledgeIds.add(kde.getKnowledgeId());
        }
        //获取该老师当前的所有试卷分页集合
        Page<PaperEntity> page = paperRepository.findByTeacherIdAndKnowledgeCodeIn(teacherId, knowledgeIds, new PageRequest(pageNum, pageSize));
        Long total = page.getTotalElements();
        List<PaperEntity> list = page.getContent();
        List<PaperNavigation> paperNavigationList = new ArrayList<>();
        for (PaperEntity paperEntity : list) {
            PaperNavigation paperNavigation = new PaperNavigation();
            paperNavigation.setPaperId(paperEntity.getPaperId());
            paperNavigation.setPaperName(paperEntity.getPaperName());
            paperNavigation.setCreateDate(paperEntity.getCreateDate());
            //分发数
            if (paperEntity.getUserItems() != null) {
                Integer sendTotal = paperEntity.getUserItems().size();
                paperNavigation.setDistribute(sendTotal);
            } else {
                paperNavigation.setDistribute(0);
            }
            //是否已经推荐资源,默认没有推荐
            paperNavigation.setSend(false);
            paperNavigation.setAnswered(0);
            //作答数
            List<PaperAnswerEntity> anwserList = paperAnswerRepository.findByPaperIdAndResultIsNotNull(paperEntity.getPaperId());
            if (anwserList != null && !anwserList.isEmpty()) {
                paperNavigation.setAnswered(anwserList.size());
                //已经推荐资源标记
                if (anwserList.get(0).getResource() != null) {
                    JSONArray jsonArray = JSONArray.parseArray(anwserList.get(0).getResource());
                    if (jsonArray.toArray().length > 0) {
                        paperNavigation.setSend(true);
                    }
                }
            }
            paperNavigationList.add(paperNavigation);
        }
        return new PageResp<PaperNavigation>(total, paperNavigationList);
    }

    @Override
    public PaperResp getPaperDetail(String paperId) {
        PaperEntity paperEntity = paperRepository.findOne(paperId);
        PaperResp paperResp = new PaperResp();
        if (paperEntity != null) {
            PaperAnwserDetail paperAnwserDetail = new PaperAnwserDetail();
            paperResp.setPaperName(paperEntity.getPaperName());//试卷名
            if (paperEntity.getKnowledgeCode() != null) {
                paperResp.setKnowledgeId(paperEntity.getKnowledgeCode());//知识点id
                KnowledgeEntity knowledgeEntity = knowledgeRepository.findOne(paperEntity.getKnowledgeCode());
                paperResp.setKnowledgeName(knowledgeEntity.getKnowledgeName());//知识点名字
            }
            if (paperEntity.getTeacherId() != null) {
                paperResp.setTeacherId(paperEntity.getTeacherId());//老师id
                UserEntity userEntity = userRepository.findOne(paperEntity.getTeacherId());
                if (userEntity != null) {
                    paperResp.setTeacherName(userEntity.getRealName());//老师真名
                }
            }
            if (paperEntity.getQuestionEntityItems() != null && !paperEntity.getQuestionEntityItems().isEmpty()) {
                Integer qTotal = paperEntity.getQuestionEntityItems().size();
                paperResp.setQuestionTotal(qTotal.toString());//问题总数
            }
            paperAnwserDetail.setDistribute(0);
            if (paperEntity.getUserItems() != null && !paperEntity.getUserItems().isEmpty()) {
                Integer total = paperEntity.getUserItems().size();
                paperAnwserDetail.setDistribute(total);//试卷分发数
            }
            List<PaperAnswerEntity> answeredList = null;
            List<StudentDetail> studentList = new ArrayList<>();
            paperAnwserDetail.setAnswer(0);
            if (paperEntity.getPaperId() != null) {
                answeredList = paperAnswerRepository.findByPaperIdAndResultIsNotNull(paperEntity.getPaperId());
                if (answeredList != null && !answeredList.isEmpty()) {
                    paperAnwserDetail.setAnswer(answeredList.size());//试卷已经作答数
                }
            }
            for (PaperAnswerEntity pae : answeredList) {
                StudentDetail studentDetail = new StudentDetail();
                studentDetail.setStudentId(pae.getUserId());
                UserEntity userEntity = userRepository.findOne(pae.getUserId());
                studentDetail.setUserRealName(userEntity.getRealName());
                studentDetail.setAnswer(true);//已经作答
                //作答正确数/总数字符串
                studentDetail.setCorrect(pae.getTotalScore());
                studentList.add(studentDetail);
            }

            List<PaperAnswerEntity> noAnsweredList = null;
            if (paperEntity.getPaperId() != null) {
                noAnsweredList = paperAnswerRepository.findByPaperIdAndResultIsNull(paperEntity.getPaperId());
                for (PaperAnswerEntity pae : noAnsweredList) {
                    StudentDetail studentDetail = new StudentDetail();
                    studentDetail.setStudentId(pae.getUserId());
                    UserEntity userEntity = userRepository.findOne(pae.getUserId());
                    studentDetail.setUserRealName(userEntity.getRealName());
                    studentDetail.setAnswer(false);//未作答
                    //计算作答正确数
                    studentDetail.setCorrect(pae.getTotalScore());
                    studentList.add(studentDetail);
                }
            }
            paperAnwserDetail.setStudents(studentList);
            paperResp.setAnswerDetail(paperAnwserDetail);
        }
        return paperResp;
    }

    @Override
    public PageResp getStudentsPapers(String studentId, String courseId, Integer pageNum, Integer pageSize) {
        //通过couse获取所有知识点的id集合
        List<KnowledgeEntity> KnowledgeEntityList = knowledgeRepository.findByCourse(courseId);
        Set<String> knowledgeIds = new HashSet<>();
        for (KnowledgeEntity kde : KnowledgeEntityList) {
            knowledgeIds.add(kde.getKnowledgeId());
        }
        PageResp<PaperEntity> pageResp = paperRepository.findByStudentIdContainAndKnowledgeCodeIn(studentId, knowledgeIds, new PageRequest(pageNum, pageSize));
        List<PaperEntity> paperEntityList = pageResp.getItems();
        List<PaperStudentNavigation> paperStudentNavigationList = new ArrayList<>();
        for (PaperEntity paperEntity : paperEntityList) {
            String paperId = paperEntity.getPaperId();
            String paperName = paperEntity.getPaperName();
            Date startTime = paperEntity.getCreateDate();//发卷时间
            PaperAnswerEntity paperAnswerEntity = paperAnswerRepository.findByPaperIdAndUserId(paperId, studentId);
            Date answerTime = null;
            String resource = null;
            String score =null;
            if (paperAnswerEntity != null) {
                answerTime = paperAnswerEntity.getAnswerTime();//作答时间
                resource = paperAnswerEntity.getResource();//是否已经推送资源
                score=paperAnswerEntity.getTotalScore();//等分情况
            }
            Boolean isSend = false;
            if (resource != null && JSONObject.parseArray(resource).toArray() != null) {
                if (JSONObject.parseArray(resource).toArray().length > 0) {
                    isSend = true;
                }
            }

            PaperStudentNavigation paperStudentNavigation = new PaperStudentNavigation(paperId, paperName, startTime, answerTime, isSend, score);
            paperStudentNavigationList.add(paperStudentNavigation);
        }
        return new PageResp<PaperStudentNavigation>(pageResp.getTotal(), paperStudentNavigationList);
    }
}
