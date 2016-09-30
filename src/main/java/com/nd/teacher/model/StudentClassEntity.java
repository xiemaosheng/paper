package com.nd.teacher.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@Entity
@Table(name = "t_class_student")
@IdClass(StudentClassEntity.StudentClassEntityPK.class)
public class StudentClassEntity implements Serializable {
    @Id
    @Column(name = "class_id")
    private String classId;

    @Id
    @Column(name = "student_id")
    private String studentId;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Embeddable
    static class StudentClassEntityPK implements Serializable{
        @Id
        @Column(name = "class_id")
        private String classId;

        @Id
        @Column(name = "student_id")
        private String studentId;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getStudentId() {
            return studentId;
        }

        public void setStudentId(String studentId) {
            this.studentId = studentId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            StudentClassEntityPK that = (StudentClassEntityPK) o;

            if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
            return studentId != null ? studentId.equals(that.studentId) : that.studentId == null;

        }

        @Override
        public int hashCode() {
            int result = classId != null ? classId.hashCode() : 0;
            result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
            return result;
        }
    }
}
