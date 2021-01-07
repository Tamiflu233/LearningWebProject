package com.tamiflu.mapper;

import com.tamiflu.entity.Teacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeacherMapper {
//    添加老师信息(除了uid)
    public void insertTeaInfo(Teacher teaInfo);
//    根据教师号更新老师的uid
    public void updateTeaUid(@Param("teaId") String teaId,@Param("uid") String uid);
    //    查询所有老师
    public List<Teacher> findAllTeachers();
//    分页查询所有老师信息(动态条件)
    public List<Teacher> findTeachers(Teacher teaInfo);
//    根据教师号删除老师
    public void deleteTeacherById(String teaId);
//    更新教师信息
    public void updateTeaInfo(Teacher newInfo);
//    查找教某门课的所有老师
    public List<Teacher> findTeachersByLesson(String lessonId);
//    根据用户名返回教师信息
    public Teacher findTeacherByUsername(String username);

}
