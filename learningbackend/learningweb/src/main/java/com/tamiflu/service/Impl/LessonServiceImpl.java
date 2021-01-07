package com.tamiflu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Lesson;
import com.tamiflu.entity.TeachesInfoCondition;
import com.tamiflu.mapper.LessonMapper;
import com.tamiflu.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {

    @Autowired
    private LessonMapper lessonMapper;

    @Override
    public List<Lesson> findAllLessons() {
        return lessonMapper.findAllLessons();
    }

    @Override
    public PageInfo<Lesson> findLessons(Integer pageNow, Integer pageSize, Lesson lesson) {
        PageHelper.startPage(pageNow,pageSize);
        List<Lesson> lessons = lessonMapper.findLessons(lesson);
        PageInfo<Lesson> pageInfo = new PageInfo<>(lessons);
        return pageInfo;
    }

    @Override
    public void insertLesson(Lesson lesson) {
        lessonMapper.insertLesson(lesson);
    }

    @Override
    public void deleteLessonById(String lessonId) {
        lessonMapper.deleteLessonById(lessonId);
    }

    @Override
    public void updateLesson(Lesson lesson) {
        lessonMapper.updateLesson(lesson);
    }

    @Override
    public List<Lesson> findTeachesInfo(Integer pageNow, Integer pageSize, TeachesInfoCondition condition) {
        Integer start = (pageNow - 1) * pageSize;
        List<Lesson> teachesInfo = lessonMapper.findTeachesInfo(start,pageSize,condition);
        return teachesInfo;
    }

    @Override
    public PageInfo<Lesson> findLessonsBySchoolAndTea(Integer pageNow, Integer pageSize, String schoolId, String teaId) {
        PageHelper.startPage(pageNow,pageSize);
        List<Lesson> lessons = lessonMapper.findLessonsBySchoolAndTea(schoolId,teaId);
        PageInfo<Lesson> pageInfo = new PageInfo<>(lessons);
        return pageInfo;
    }
}
