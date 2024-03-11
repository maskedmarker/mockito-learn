package org.example.learn.mockito.manager;

import org.example.learn.mockito.dao.StudentScoreDao;
import org.example.learn.mockito.model.po.StudentScore;
import org.example.learn.mockito.service.Calculator;
import org.example.learn.mockito.service.StudentScoreService;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class StudentScoreManagerTest {

    @Mock
    private StudentScoreService studentScoreService;

    @Mock
    private Calculator calculator;

    // studentScoreManager字段会被mock,
    // 同时从当前对象的已经标注@Mock的字段作为mock, 将这些mock注入到studentScoreManager
    @InjectMocks
    private StudentScoreManager studentScoreManager;

    @Test
    public void test0() {
        List<StudentScore> expectScores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            StudentScore studentScore = new StudentScore();
            studentScore.setId(123);
            studentScore.setCourse("course" + i);
            studentScore.setScore(i);
            expectScores.add(studentScore);
        }

        double avgScore = studentScoreManager.getAvgScore(123);
        assertEquals(0, avgScore, 0);
    }
}