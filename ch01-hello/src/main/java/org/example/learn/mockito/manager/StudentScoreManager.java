package org.example.learn.mockito.manager;

import org.example.learn.mockito.dao.StudentScoreDao;
import org.example.learn.mockito.model.po.StudentScore;
import org.example.learn.mockito.service.Calculator;
import org.example.learn.mockito.service.StudentScoreService;

import java.util.List;

public class StudentScoreManager {

    private StudentScoreService studentScoreService;

    private Calculator calculator;

    public double getAvgScore(int id) {
        List<StudentScore> studentScores = studentScoreService.queryAll(id);
        return calculator.avg(studentScores.stream().mapToDouble(StudentScore::getScore).toArray());
    }
}
