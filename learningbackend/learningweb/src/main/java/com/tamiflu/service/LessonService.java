package com.tamiflu.service;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Lesson;
import com.tamiflu.entity.TeachesInfoCondition;

import java.util.List;

public interface LessonService {
    //    获取所有课程信息
    public List<Lesson> findAllLessons();
    //    查询课程信息(动态条件)
    public PageInfo<Lesson> findLessons(Integer pageNow,Integer pageSize,Lesson lesson);
    //    添加课程信息
    public void insertLesson(Lesson lesson);
    //    根据课程号删除课程
    public void deleteLessonById(String lessonId);
    //    更新课程信息
    public void updateLesson(Lesson lesson);
//    查询授课信息(动态条件)
    public List<Lesson> findTeachesInfo(Integer pageNow, Integer pageSize, TeachesInfoCondition condition);
    //    根据学院id和教师id分页获取所有课程
    public PageInfo<Lesson> findLessonsBySchoolAndTea(Integer pageNow,Integer pageSize,String schoolId, String teaId);
}
