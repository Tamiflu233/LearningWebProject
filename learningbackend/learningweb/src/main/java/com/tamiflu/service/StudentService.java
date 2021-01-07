package com.tamiflu.service;

import com.tamiflu.entity.Student;

public interface StudentService {
    //    创建学生信息
    public void insertStuInfo(Student student);
    //    根据username查看学生信息
    public Student findStudentByUsername(String username);
}
