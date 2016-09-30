package com.nd.question.service.impl;

import com.nd.common.utils.XlsImportResult;
import com.nd.paper.model.PaperEntity;
import com.nd.paper.model.PaperReq;
import com.nd.question.model.PageResp;
import com.nd.question.model.QuestionEntity;
import com.nd.question.repository.QuestionRepository;
import com.nd.question.service.QuestionService;
import com.nd.teacher.service.IClassService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/11 0011.
 */
@Service
public class QuestionServiceImpl implements QuestionService {
    private final static Pattern CODE_PATTERN = Pattern.compile("【(\\w{1})】", Pattern.MULTILINE);
    @Autowired
    QuestionRepository questionRepository;
    @Autowired
    private IClassService classService;

    @Override
    public Object uploadQuestions(XSSFWorkbook wb, String userId) {
        XlsImportResult xlsImportResult = new XlsImportResult();
        Sheet sheet = wb.getSheetAt(0);
        Integer ruwNum = sheet.getLastRowNum();
        List<QuestionEntity> questionEntityList = new ArrayList();
        for (int i = 1; i <= ruwNum; i++) {
            Row row = sheet.getRow(i);
            QuestionEntity questionEntity = new QuestionEntity();
            boolean isCorrectQuesiton = true;
            Cell cell = row.getCell(0);
            String questionTitle = cell.getRichStringCellValue().toString();
            if (questionTitle != null && StringUtils.isNotBlank(questionTitle.trim())) {
                questionEntity.setQuestionTitle(questionTitle);//题干
            } else {
                continue;
            }
            cell = row.getCell(1);
            String chioceCode = cell.getRichStringCellValue().toString();
            if (chioceCode != null && StringUtils.isNoneBlank(chioceCode.trim())) {
                LinkedHashMap<String, String> chioceItems = new LinkedHashMap<>();
                getAnwsers(chioceCode, chioceItems);
                if (chioceItems.size() < 2) {
                    isCorrectQuesiton = false;
                    xlsImportResult.addError(cell.getRowIndex(), 1, "题目选项格式不对！应该是如下格式：【A】 它是白色【B】 它是蓝色【C】 它是红色【D】 它是灰色 且至少为2个");
                } else {
                    questionEntity.setChioceItems(chioceItems);//题目选项
                }
            } else {
                isCorrectQuesiton = false;
                xlsImportResult.addError(cell.getRowIndex(), 1, "题目选项不能为空！");
            }

            cell = row.getCell(2);
            String answerCode = cell.getRichStringCellValue().toString();
            if (answerCode != null && StringUtils.isNotBlank(answerCode.trim())) {
                LinkedHashMap<String, String> answertemsMap = new LinkedHashMap<>();
                getAnwsers(answerCode, answertemsMap);
                Set<String> answertems = answertemsMap.keySet();
                if (answertems.size() < 1) {
                    isCorrectQuesiton = false;
                    xlsImportResult.addError(cell.getRowIndex(), 2, "答案至少为一个");
                } else {
                    List<String> answertemsList = new ArrayList<>();
                    for (String key : answertems) {
                        answertemsList.add(key);
                    }
                    questionEntity.setAnswer(answertemsList);//题目答案
                }
            } else {
                isCorrectQuesiton = false;
                xlsImportResult.addError(cell.getRowIndex(), 2, "正确答案不能为空！");
            }

            cell = row.getCell(3);
            String knowledge = cell.getRichStringCellValue().toString();
            if (knowledge != null && StringUtils.isNotBlank(knowledge.trim())) {
                questionEntity.setKnowledge(knowledge);//题目知识点
            } else {
                isCorrectQuesiton = false;
                xlsImportResult.addError(cell.getRowIndex(), 3, "题目知识点不能为空！");
            }

            cell = row.getCell(4);
            String analysis = cell.getRichStringCellValue().toString();
            if (analysis != null && StringUtils.isNotBlank(analysis.trim())) {
                questionEntity.setAnalysis(analysis);//题目答案解析
            } else {
                isCorrectQuesiton = false;
                xlsImportResult.addError(cell.getRowIndex(), 4, "题目答案解析不能为空！");
            }


            cell = row.getCell(5);
            String questionType = cell.getRichStringCellValue().toString();
            if (questionType != null && StringUtils.isNotBlank(questionType.trim())) {
                questionEntity.setQuestionType(questionType);//题目类型
            } else {
                isCorrectQuesiton = false;
                xlsImportResult.addError(cell.getRowIndex(), 5, "题目类型不能为空！");
            }

            questionEntity.setCreateDate(new Date());//题目创建时间
            questionEntity.setCreateBy(userId);//题目创建人
            if (isCorrectQuesiton) {
                questionEntityList.add(questionEntity);
            }
        }
        questionRepository.insert(questionEntityList);
        return xlsImportResult;
    }

    @Override
    public PageResp getQuestions(String knowledge, Integer pageNum, Integer limit) {
        Page page = questionRepository.findByKnowledge(knowledge, new PageRequest(pageNum, limit));
        List<QuestionEntity> lits = page.getContent();
        return new PageResp(page.getTotalElements(), lits);

    }


    /**
     * 通过正则匹配获取题目的选项或者答案
     *
     * @param anwsers
     * @return
     */
    private void getAnwsers(String anwsers, LinkedHashMap<String, String> codeMap) {
        List<MatchResult> list = new ArrayList<>();
        Matcher m = CODE_PATTERN.matcher(anwsers);
        while (m.find()) {
            list.add(m.toMatchResult());
        }
        for (int i = 0; i < list.size(); i++) {
            String strTemp = "";
            MatchResult matcher = list.get(i);
            String index = matcher.group(1);
            int nextIdx = i + 1;
            if (list.size() == nextIdx) {
                strTemp = anwsers.substring(matcher.end(), anwsers.length());
                index = index.trim();
                strTemp = strTemp.trim();
                codeMap.put(index, strTemp);
            } else {
                MatchResult nextMatcher = list.get(nextIdx);
                strTemp = anwsers.substring(matcher.end(), nextMatcher.start());
                index = index.trim();
                strTemp = strTemp.trim();
                codeMap.put(index, strTemp);
            }
        }
    }

    @Override
    public PaperEntity getPaper2Req(PaperReq paperReq,String teacherId) {
        List<String> questionLists = paperReq.getQuestionId();
        List<QuestionEntity> list = new ArrayList<>();
        PaperEntity paperEntity = new PaperEntity();
        for (String id : questionLists) {
            QuestionEntity questionEntity = questionRepository.findOne(id);
            list.add(questionEntity);
        }
        List<String> classIds = classService.getClassIds(teacherId);
        List<String> studentIds = new ArrayList<>();

        for (String classId : classIds) {
            studentIds = classService.getStudentIds(classId);
        }

        paperEntity.setCreateDate(new Date());
        paperEntity.setKnowledgeCode(paperReq.getKnowledgeId());
        paperEntity.setPaperName(paperReq.getPaperName());
        paperEntity.setTeacherId(teacherId);
        paperEntity.setQuestionEntityItems(list);
        paperEntity.setUserItems(studentIds);
        return paperEntity;
    }
}
