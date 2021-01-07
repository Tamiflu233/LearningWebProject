package com.tamiflu.mapper;

import com.tamiflu.entity.Student;

public interface StudentMapper {
//    创建学生信息
    public void insertStuInfo(Student student);
//    根据username查看学生信息
    public Student findStudentByUsername(String username);
    //    按学号查询学生信息
    public Student findStudentById(String stuId);
}
