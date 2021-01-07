package com.tamiflu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Teacher;
import com.tamiflu.mapper.TeacherMapper;
import com.tamiflu.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public void insertTeaInfo(Teacher teaInfo) {
        teacherMapper.insertTeaInfo(teaInfo);
    }

    @Override
    public PageInfo<Teacher> findTeachers(Integer pageNow, Integer pageSize, Teacher teacher) {
        PageHelper.startPage(pageNow,pageSize);
        List<Teacher> teaInfos = teacherMapper.findTeachers(teacher);
        PageInfo<Teacher> pageInfo = new PageInfo<>(teaInfos);
        return pageInfo;
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherMapper.findAllTeachers();
    }

    @Override
    public void deleteTeacherById(String teaId) {
        teacherMapper.deleteTeacherById(teaId);
    }

    @Override
    public void updateTeaInfo(Teacher newInfo) {
        teacherMapper.updateTeaInfo(newInfo);
    }

    @Override
    public List<Teacher> findTeachersByLesson(String lessonId) {
        return teacherMapper.findTeachersByLesson(lessonId);
    }

    @Override
    public Teacher findTeacherByUsername(String username) {
        return teacherMapper.findTeacherByUsername(username);
    }
}
