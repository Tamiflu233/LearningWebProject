package com.tamiflu.controller;

import com.tamiflu.entity.Answer;
import com.tamiflu.service.AnswerService;
import com.tamiflu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerService answerService;

    /*
    * 回答问题
    * */
    @PostMapping("addAnswer")
    public Result addAnswer(@RequestBody Answer answer) {
        Result result = new Result();
        try {
            answerService.addAnswer(answer);
            result.setMsg("回答成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("回答失败！请稍后再试");
        }
        return result;
    }


    /*
    * 更新回答
    * */
    @PostMapping("updateAnswer")
    public Result updateAnswer(@RequestBody Answer answer) {
        Result result = new Result();
        try {
            answerService.updateAnswer(answer);
            result.setMsg("更新回答成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("更新回答失败！请稍后再试");
        }
        return result;
    }
    /*
    * 删除回答
    *
    * */
    @GetMapping("deleteAnswer")
    public Result deleteAnswer(String aid,String qid) {
        Result result = new Result();
        try {
            answerService.deleteAnswer(aid,qid);
            result.setMsg("删除回答成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("删除回答失败！请稍后再试");
        }
        return result;
    }
    /*
    * 按qid找回答
    * */
    @GetMapping("findAnswerByQid")
    public Result findAnswerByQid(String qid) {
        Result result = new Result();
        try {
            Answer answer = answerService.findAnswerByQid(qid);
            result.setMsg("成功获取回答");
            result.setData(answer);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("获取回答失败!请稍后再试！");
        }
        return result;
    }
}
