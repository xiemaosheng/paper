package com.nd.paper.model;

/**
 * 学生作答详情
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public class StudentDetail {
    private String studentId;
    private Boolean answer;
    private String correct;
    private String userRealName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Boolean getAnswer() {
        return answer;
    }

    public void setAnswer(Boolean answer) {
        this.answer = answer;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName;
    }
}
