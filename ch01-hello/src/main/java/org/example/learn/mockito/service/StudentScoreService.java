package org.example.learn.mockito.service;

import org.example.learn.mockito.dao.StudentScoreDao;
import org.example.learn.mockito.model.po.StudentScore;

import java.util.List;

public class StudentScoreService {

    private StudentScoreDao studentScoreDao;

    public List<StudentScore> queryAll(int id) {
        return studentScoreDao.query(id);
    }
}
