package com.lei.test;

import com.lei.domain.Student;
import com.lei.service.StudentService;
import com.lei.service.impl.StudentServiceImpl;
import com.lei.util.ServiceFactory;

import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        StudentService ss= (StudentService) ServiceFactory.getService(new StudentServiceImpl());
        Student stu=ss.getById("A0001");
        System.out.println(stu);
/*        Student student=new Student("A0012","关羽",23);
        System.out.println(ss.save(student));*/
/*        List<Student> students=ss.getAll();
        for (Student s:students){
            System.out.println(s);
        }*/
    }
}
