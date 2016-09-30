package com.nd.teacher.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@Entity
@Table(name = "t_school_class")
@IdClass(SchoolClassEntity.SchoolClassEntityPK.class)
public class SchoolClassEntity implements  Serializable{
    @Id
    @Column(name = "class_id")
    private String classId;

    @Id
    @Column(name = "teacher_id")
    private String teacherId;

    @Basic
    @Column(name = "class_name")
    private String className;



    @Basic
    @Column(name = "create_date")
    private Date createDate;

    @Basic
    @Column(name = "update_date")
    private String updateDate;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    @Embeddable
    static class SchoolClassEntityPK implements Serializable{
        @Id
        @Column(name = "class_id")
        private String classId;
        @Id
        @Column(name = "teacher_id")
        private String teacherId;

        public String getClassId() {
            return classId;
        }

        public void setClassId(String classId) {
            this.classId = classId;
        }

        public String getTeacherId() {
            return teacherId;
        }

        public void setTeacherId(String teacherId) {
            this.teacherId = teacherId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            SchoolClassEntityPK that = (SchoolClassEntityPK) o;

            if (classId != null ? !classId.equals(that.classId) : that.classId != null) return false;
            return teacherId != null ? teacherId.equals(that.teacherId) : that.teacherId == null;

        }

        @Override
        public int hashCode() {
            int result = classId != null ? classId.hashCode() : 0;
            result = 31 * result + (teacherId != null ? teacherId.hashCode() : 0);
            return result;
        }
    }
}
