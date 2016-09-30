package com.nd.teacher.dao;


import com.nd.teacher.model.StudentClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public interface StudentClassResponsitory extends JpaRepository<StudentClassEntity,String> {
    List<StudentClassEntity> findByStudentId(String studentId);
    List<StudentClassEntity> findByClassId(String classId);
}
