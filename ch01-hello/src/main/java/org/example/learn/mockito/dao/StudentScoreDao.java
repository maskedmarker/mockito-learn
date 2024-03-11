package org.example.learn.mockito.dao;

import org.example.learn.mockito.model.po.StudentScore;

import java.util.List;

public interface StudentScoreDao {

    List<StudentScore> query(int id);
}
