package com.tamiflu.controller;


import com.tamiflu.entity.Teaches;
import com.tamiflu.service.TeachesService;
import com.tamiflu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/teaches")
public class TeachesController {

    @Autowired
    private TeachesService teachesService;

    /*
    * 删除某条授课信息
    *
    * */
    @GetMapping("deleteTeachesInfo")
    public Result deleteTeachesInfo(String lessonId,String teaId) {
        Result result = new Result();
        try {
            teachesService.deleteTeachesInfoById(lessonId,teaId);
            result.setMsg("成功删除授课信息！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("授课信息删除失败！请稍后再试！");
        }
        return result;
    }

    /*
    *
    * 添加授课信息
    *
    * */
    @PostMapping("addTeachesInfo")
    public Result addTeachesInfo(@RequestBody Teaches teachesInfo) {
        Result result = new Result();
        try {
            teachesService.insertTeachesInfo(teachesInfo);
            result.setMsg("成功添加授课信息!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("授课信息添加失败!请检查该教师该门课的授课信息是否已有，或请稍后再试!");
        }
        return result;
    }

}
