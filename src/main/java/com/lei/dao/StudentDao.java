package com.lei.dao;

import com.lei.domain.Student;

import java.util.List;

public interface StudentDao {
    Student getById(String id);
    Integer save(Student s);
    List<Student> getAll();
}
