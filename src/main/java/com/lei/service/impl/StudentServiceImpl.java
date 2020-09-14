package com.lei.service.impl;

import com.lei.dao.StudentDao;
import com.lei.domain.Student;
import com.lei.service.StudentService;
import com.lei.util.SqlSessionUtil;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    //通过mybatis反射机制，取得dao层对象
    private StudentDao studentDao=
            SqlSessionUtil.getSqlSession().getMapper(StudentDao.class);
    @Override
    public Student getById(String id) {
        Student stud=null;
        stud=studentDao.getById(id);
        return stud;
    }

    @Override
    public Integer save(Student s) {
        return studentDao.save(s);
    }

    @Override
    public List<Student> getAll() {
        List<Student> students=null;
        students=studentDao.getAll();
        return students;
    }
}
