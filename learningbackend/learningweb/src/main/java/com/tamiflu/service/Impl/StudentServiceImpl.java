package com.tamiflu.service.Impl;

import com.tamiflu.entity.Student;
import com.tamiflu.mapper.StudentMapper;
import com.tamiflu.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

  @Override
  public void insertStuInfo(Student student) {
      studentMapper.insertStuInfo(student);
  }

  @Override
  public Student findStudentByUsername(String username) {
    return studentMapper.findStudentByUsername(username);
  }
}
