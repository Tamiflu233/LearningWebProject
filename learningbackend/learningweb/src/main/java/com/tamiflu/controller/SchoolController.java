package com.tamiflu.controller;


import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.School;
import com.tamiflu.service.SchoolService;
import com.tamiflu.vo.Result;
import com.tamiflu.vo.SpecificSchoolsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

//查询所有学院
    @GetMapping("findAllSchools")
    public Result showAllSchools() {
        Result result = new Result();
        try {
            List<School> data = schoolService.findAllSchools();
            result.setMsg("查找所有学院成功!");
            result.setData(data);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查找所有学员失败!请稍后再试!");
        }
        return result;
    }

//    分页查找该页的所有学院信息
    @GetMapping("findSchoolsByPage")
    public Result showSchoolsByPage(Integer pageNow,Integer pageSize) {
        Map<String, Object> data = new HashMap<>();
        Result result = new Result();
        pageNow = pageNow==null?1:pageNow;
        pageSize = pageSize==null?5:pageSize;
        try {
            List<School> schools = schoolService.findSchoolsByPage(pageNow,pageSize);
            Long total = schoolService.findTotals();
            data.put("total",total);
            data.put("schools",schools);
            result.setMsg("查询当前页学院信息成功!");
            result.setData(data);
        }catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询当前页学院失败！请稍后重试!");
        }
        return result;

    }

//    按学院号删除学院
    @GetMapping("deleteSchoolById")
    public Result deleteSchoolById(String schoolId) {
        Result result = new Result();
        try {
            schoolService.deleteSchoolById(schoolId);
            result.setMsg("该学院删除成功！");
        } catch (Exception e) {
            result.setCode(500);
            result.setMsg("该学院删除失败！请稍后再试!");
        }
        return result;
    }

//    插入学院
    @PostMapping("insertSchool")
    public Result insertSchool(@RequestBody School school) {
        Result result = new Result();
        try {
            schoolService.insertSchool(school);
            result.setMsg("学院信息插入成功!");
        }
        catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("学院信息插入失败!请检查是否学院id已经存在或稍后再试！");
        }

        return result;
    }

//    更新学院信息
    @PostMapping("updateSchool")
    public Result updateSchool(@RequestBody School school) {
        Result result = new Result();
        try {
            schoolService.updateSchool(school);
            result.setMsg("学院信息更新成功!");
        }catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("学院信息更新失败！请稍后再试！");
        }
        return result;
    }

//    动态多条件模糊查找学院(需要分页)
    @PostMapping("findSpecificSchools")
    public Result findSpecificSchools(@RequestBody SpecificSchoolsVO params) {
//        System.out.println(params.toString());
        Result result = new Result();
        try {
            PageInfo pageInfo = schoolService.findSpecificSchools(params.getPageNow(),params.getPageSize(), params.getSchools());
            result.setMsg("成功查询符合条件的学院信息!");
            result.setData(pageInfo);
        }catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询失败！请检查查询条件是否合法，或稍后再试!");
        }
        return result;
    }
//    按学院号查询学院名称
    @GetMapping("findSchoolById")
    public Result findSchoolById(String schoolId) {
        Result result = new Result();
        try {
            School school = schoolService.findSchoolById(schoolId);
            result.setMsg("成功查询学院信息！");
            result.setData(school);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("学院信息查询失败！请稍后再试！");
        }
        return result;
    }

}
