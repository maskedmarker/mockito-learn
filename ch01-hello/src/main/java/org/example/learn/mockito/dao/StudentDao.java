package org.example.learn.mockito.dao;

import org.example.learn.mockito.model.po.Student;

public interface StudentDao {

    Student query(int id);
}
