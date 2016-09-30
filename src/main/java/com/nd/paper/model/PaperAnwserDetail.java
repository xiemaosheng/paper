package com.nd.paper.model;

import java.util.List;

/**
 * 作答详情
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public class PaperAnwserDetail {
    private Integer distribute;
    private Integer answer;
    private List<StudentDetail> students;

    public Integer getDistribute() {
        return distribute;
    }

    public void setDistribute(Integer distribute) {
        this.distribute = distribute;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }

    public List<StudentDetail> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDetail> students) {
        this.students = students;
    }
}
