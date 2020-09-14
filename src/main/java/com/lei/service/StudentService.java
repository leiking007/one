package com.lei.service;

import com.lei.domain.Student;

import java.util.List;

public interface StudentService {
    Student getById(String id);
    Integer save(Student s);
    List<Student> getAll();
}
