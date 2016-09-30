package com.nd.teacher.service;

import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public interface IClassService {
    List<String> getClassIds(String teacherId);
    List<String> getStudentIds(String classId);
}
