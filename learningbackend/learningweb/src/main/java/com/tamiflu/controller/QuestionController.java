package com.tamiflu.controller;


import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Question;
import com.tamiflu.service.QuestionService;
import com.tamiflu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    /*
    * 分页查询提问
    *
    * */
    @GetMapping("findQuestions")
    public Result findQuestions(Integer pageNow, Integer pageSize, String lessonId, String teaId, String schoolId) {
        Result result = new Result();
        try {
            PageInfo pageInfo = questionService.findQuestionsByPage(pageNow,
                    pageSize,
                    lessonId,
                    teaId,
                    schoolId);
            result.setMsg("查询提问信息成功！");
            result.setData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询提问信息失败!请稍后再试！");
        }

        return result;
    }

    /*
    * 更新提问
    *
    * */
    @PostMapping("updateQuestion")
    public Result updateQuestion(@RequestBody Question question) {
        Result result = new Result();
        try {
            questionService.updateQuestion(question);
            result.setMsg("更新提问信息成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("更新提问信息失败！请稍后再试!");
        }
        return result;
    }
    /*
    * 删除提问
    *
    *
    * */
    @GetMapping("deleteQuestion")
    public Result deleteQuestion(String qid) {
        Result result = new Result();
        try {
            questionService.deleteQuestionById(qid);
            result.setMsg("删除提问成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("提问删除失败！请稍后再试！");
        }
        return result;
    }
    /*
    * 发布提问
    *
    * */
    @PostMapping("addQuestion")
    public Result addQuestion(@RequestBody Question question) {
        Result result = new Result();
        try {
            questionService.insertQuestion(question);
            result.setMsg("提问发布成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("提问发布失败!请稍后再试！");
        }
        return result;
    }
}
