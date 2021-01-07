package com.tamiflu.controller;


import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Teacher;
import com.tamiflu.service.TeacherService;
import com.tamiflu.vo.Result;
import com.tamiflu.vo.SpecificTeachersVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    /*
    * 查看教师信息(动态条件)
    *
    * */
    @GetMapping("findAllTeachers")
    public Result findAllTeachers() {
        Result result = new Result();
        try {
            List<Teacher> teachers = teacherService.findAllTeachers();
            result.setMsg("成功查询所有教师信息！");
            result.setData(teachers);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询所有教师信息失败！请稍后再试!");
        }
        return result;
    }

    @PostMapping("findTeachers")
    public Result findTeachers(@RequestBody SpecificTeachersVO params) {
        Result result = new Result();

        try {
            PageInfo pageInfo = teacherService.findTeachers(params.getPageNow(),params.getPageSize(), params.getTeacher());
            result.setMsg("成功查询符合条件的教师信息！");
            result.setData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询失败！请检查查询条件是否合法，或稍后再试!");
        }

        return result;
    }

    /*
    * 更新教师信息
    *
    *
    * */
    @PostMapping("updateTeaInfo")
    public Result updateTeaInfo(@RequestBody Teacher teaInfo) {
        Result result = new Result();
        try {
            teacherService.updateTeaInfo(teaInfo);
            result.setMsg("成功更新该教师信息！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("更新失败！请稍后再试!");
        }
        return result;
    }

    /*
    * 添加教师信息
    *
    *
    * */
    @PostMapping("insertTeaInfo")
    public Result insertTeacher(@RequestBody Teacher teaInfo) {
        Result result = new Result();
//        System.out.println(teaInfo);
        try {
            teacherService.insertTeaInfo(teaInfo);
            result.setMsg("成功添加该教师信息！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("插入失败！请稍后再试!");
        }

        return result;
    }


    /*
    * 根据教师号删除教师
    *
    *
    * */
    @GetMapping("deleteTeacherById")
    public Result deleteTeacherById(String teaId) {
        Result result = new Result();
        try {
            teacherService.deleteTeacherById(teaId);
            result.setMsg("成功删除该教师！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("删除失败！请稍后再试!");
        }
        return result;
    }

    /*
    * 根据课程号查询老师
    *
    *
    * */
    @GetMapping("findTeachersByLesson")
    public Result findTeachersByLesson(String lessonId) {
        Result result = new Result();
        try {
            List<Teacher> teachers = teacherService.findTeachersByLesson(lessonId);
            result.setMsg("查询教师信息成功！");
            result.setData(teachers);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询教师信息失败!请稍后再试！");
        }
        return result;
    }

//    根据用户名返回教师信息
    @GetMapping("findTeacherByUsername")
    public Result findTeacherByUsername(String username) {
        Result result = new Result();
        try {
            Teacher teacher = teacherService.findTeacherByUsername(username);
            result.setMsg("成功查询教师信息！");
            result.setData(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("教师信息查询失败!请稍后再试！");
        }
        return  result;
    }
}
