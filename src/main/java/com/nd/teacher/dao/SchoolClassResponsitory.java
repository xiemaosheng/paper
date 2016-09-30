package com.nd.teacher.dao;

import com.nd.teacher.model.SchoolClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator on 2016/9/15 0015.
 */
public interface SchoolClassResponsitory extends JpaRepository<SchoolClassEntity,String> {
    List<SchoolClassEntity> findByTeacherId(String teacherId);
}
