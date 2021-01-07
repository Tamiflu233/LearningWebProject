package com.tamiflu.service;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Teacher;

import java.util.List;

public interface TeacherService {
    //    添加老师信息(除了uid)
    public void insertTeaInfo(Teacher teaInfo);
//    动态条件查询所有老师
    public PageInfo<Teacher> findTeachers(Integer pageNow,Integer pageSize,Teacher teacher);
//    查询所有老师
    public List<Teacher> findAllTeachers();
    //    根据教师号删除老师
    public void deleteTeacherById(String teaId);
    //    更新教师信息
    public void updateTeaInfo(Teacher newInfo);
    //    查找教某门课的所有老师
    public List<Teacher> findTeachersByLesson(String lessonId);
    //    根据用户名返回教师信息
    public Teacher findTeacherByUsername(String username);
}
