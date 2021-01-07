package com.tamiflu.controller;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Lesson;
import com.tamiflu.service.LessonService;
import com.tamiflu.vo.Result;
import com.tamiflu.vo.SpecificLessonsVO;
import com.tamiflu.vo.SpecifinTeachesInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/lesson")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    /*
    * 查看课程信息（动态条件）
    *
    * */
    @GetMapping("findAllLessons")
    public Result findAllLessons() {
        Result result = new Result();
        try {
            List<Lesson> lessons = lessonService.findAllLessons();
            result.setMsg("成功查询所有课程信息");
            result.setData(lessons);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("获取课程信息失败，请稍后再试!");
        }
        return result;
    }

    @PostMapping("findLessons")
    public Result findLessons(@RequestBody SpecificLessonsVO params) {
        Result result = new Result();
        try {
            PageInfo pageInfo = lessonService.findLessons(params.getPageNow(),params.getPageSize(), params.getLessons());
            result.setMsg("成功查询符合条件的课程信息！");
            result.setData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询失败！请检查查询条件是否合法，或稍后再试!");
        }

        return result;
    }

    /*
    * 更新课程信息
    *
    * */
    @PostMapping("updateLesson")
    public Result updateLesson(@RequestBody Lesson lesson) {
        Result result = new Result();
        try {
            lessonService.updateLesson(lesson);
            result.setMsg("成功更新该课程信息！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("更新失败！请稍后再试!");
        }
        return result;
    }
    /*
    * 根据课程号删除课程
    *
    *
    * */
    @GetMapping("deleteLessonById")
    public Result deleteLessonById(String lessonId) {
        Result result = new Result();
        try {
            lessonService.deleteLessonById(lessonId);
            result.setMsg("成功删除该课程！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("删除失败！请稍后再试!");
        }
        return result;
    }
    /*
    * 添加课程信息
    *
    *
    * */
    @PostMapping("insertLesson")
    public Result insertLesson(@RequestBody Lesson lesson) {
        Result result = new Result();
        try {
            lessonService.insertLesson(lesson);
            result.setMsg("成功添加该课程信息！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("插入失败！请稍后再试!");
        }
        return result;
    }

    /*
    * 查询授课信息
    *
    *
    * */
    @PostMapping("findTeachesInfo")
    public Result findTeachesInfo(@RequestBody SpecifinTeachesInfoVO params) {
        Result result = new Result();
        try {
            Map<String,Object> data = new HashMap<>();
            List<Lesson> teachesInfo = lessonService.findTeachesInfo(params.getPageNow(),
                    params.getPageSize(), params.getCondition());
            Integer total = lessonService.findTeachesInfo(1,2000,params.getCondition()).size();
            result.setMsg("成功查询符合条件的授课信息！");
            data.put("list",teachesInfo);
            data.put("total",total);
            result.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询授课信息失败!请检查条件是否合法，或稍后再试！");
        }
        return result;
    }
    /*
    * 根据学院号和教师号分页查询课程信息
    *
    * */
    @GetMapping("findLessonsBySchoolAndTea")
    public Result findLessonsBySchoolAndTea(Integer pageNow,Integer pageSize,String schoolId,String teaId) {
        Result result = new Result();
        try {
            PageInfo pageInfo =  lessonService.findLessonsBySchoolAndTea(pageNow,pageSize,schoolId,teaId);
            result.setMsg("成功获取符合条件的课程信息！");
            result.setData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("获取课程信息失败，请检查条件是否合法，或稍后再试！");
        }
        return result;
    }
}
