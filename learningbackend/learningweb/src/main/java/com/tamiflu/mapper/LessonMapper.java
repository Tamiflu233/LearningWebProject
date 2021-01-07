package com.tamiflu.mapper;

import com.tamiflu.entity.Lesson;
import com.tamiflu.entity.TeachesInfoCondition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LessonMapper {
//    查询课程信息(动态条件)
    public List<Lesson> findLessons(Lesson lesson);
//    获取所有课程信息
    public List<Lesson> findAllLessons();
//    添加课程信息
    public void insertLesson(Lesson lesson);
//    根据课程号删除课程
    public void deleteLessonById(String lessonId);
//    更新课程信息
    public void updateLesson(Lesson lesson);
//    获取授课信息
    public List<Lesson> findTeachesInfo(@Param("start") Integer start,
            @Param("size") Integer size
            ,@Param("condition") TeachesInfoCondition condition);
//    根据学院id和教师id分页获取所有课程
    public List<Lesson> findLessonsBySchoolAndTea(@Param("schoolId") String schoolId,@Param("teaId") String teaId);
}
