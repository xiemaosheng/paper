package com.nd.teacher.service.impl;

import com.nd.teacher.dao.SchoolClassResponsitory;
import com.nd.teacher.dao.StudentClassResponsitory;
import com.nd.teacher.model.SchoolClassEntity;
import com.nd.teacher.model.StudentClassEntity;
import com.nd.teacher.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
@Service
public class ClassServiceImpl implements IClassService {
    @Autowired
    private SchoolClassResponsitory schoolClassResponsitory;
    @Autowired
    private StudentClassResponsitory studentClassResponsitory;

    @Override
    public List<String> getClassIds(String teacherId) {
        List<SchoolClassEntity> list = schoolClassResponsitory.findByTeacherId(teacherId);
        List<String> classIds = new ArrayList<>();
        for (SchoolClassEntity schoolClassEntity : list) {
            String classId = schoolClassEntity.getClassId();
            classIds.add(classId);
        }
        return classIds;
    }

    @Override
    public List<String> getStudentIds(String classId) {
        List<StudentClassEntity> list = studentClassResponsitory.findByClassId(classId);
        List<String> studentIds = new ArrayList<>();
        for (StudentClassEntity studentClassEntity : list) {
            String studentId = studentClassEntity.getStudentId();
            studentIds.add(studentId);
        }
        return studentIds;
    }
}
