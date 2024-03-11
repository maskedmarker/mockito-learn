package org.example.learn.mockito.service;

import org.example.learn.mockito.dao.StudentDao;
import org.example.learn.mockito.model.po.Student;

public class StudentService {

    private StudentDao studentDao;

    public Student query(int id) {
        return studentDao.query(id);
    }
}
