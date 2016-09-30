package com.nd.answer.model;

import com.nd.question.model.QuestionEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/9/16 0016.
 */
public class AnswerResp {
    private String paper_id;
    private String paper_name;
    private String knowledge_code;
    private String knowledgeName;
    private String teacher_id;
    private String teacher_name;
    private String start_time;
    private int question_total;
    private List<QuestionResp> questions_single;
    private List<QuestionResp> questions_multi;

    public AnswerResp() {
    }

    public AnswerResp(String paper_id, String paper_name, String knowledge_code, String knowledgeName, String teacher_id, String teacher_name, String start_time, int question_total, List<QuestionResp> questions_single, List<QuestionResp> questions_multi) {
        this.paper_id = paper_id;
        this.paper_name = paper_name;
        this.knowledge_code = knowledge_code;
        this.knowledgeName = knowledgeName;
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.start_time = start_time;
        this.question_total = question_total;
        this.questions_single = questions_single;
        this.questions_multi = questions_multi;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public String getKnowledge_code() {
        return knowledge_code;
    }

    public void setKnowledge_code(String knowledge_code) {
        this.knowledge_code = knowledge_code;
    }

    public String getKnowledgeName() {
        return knowledgeName;
    }

    public void setKnowledgeName(String knowledgeName) {
        this.knowledgeName = knowledgeName;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public int getQuestion_total() {
        return question_total;
    }

    public void setQuestion_total(int question_total) {
        this.question_total = question_total;
    }

    public List<QuestionResp> getQuestions_single() {
        return questions_single;
    }

    public void setQuestions_single(List<QuestionResp> questions_single) {
        this.questions_single = questions_single;
    }

    public List<QuestionResp> getQuestions_multi() {
        return questions_multi;
    }

    public void setQuestions_multi(List<QuestionResp> questions_multi) {
        this.questions_multi = questions_multi;
    }
}
