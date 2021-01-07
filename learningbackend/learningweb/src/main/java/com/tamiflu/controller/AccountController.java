package com.tamiflu.controller;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Account;
import com.tamiflu.entity.Student;
import com.tamiflu.entity.Teacher;
import com.tamiflu.service.AccountService;
import com.tamiflu.vo.CreateStuAccountVO;
import com.tamiflu.vo.CreateTeaAccountVO;
import com.tamiflu.vo.Result;
import com.tamiflu.vo.SpecificAccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    /*
    * 查询所有账号和对应的教师名称
    *
    * */
    @PostMapping("findTeaAccounts")
    public Result findTeaAccounts(@RequestBody SpecificAccountVO params) {
        Result result = new Result();
        Account account = null;
        String teaId = null;
        try {
            teaId = params.getTeaId();
            account = params.getAccounts();
            Teacher teacher = new Teacher();
            teacher.setTeaId(teaId);
            account.setTeacher(teacher);
            PageInfo pageInfo = accountService.findAllTeaAccounts(params.getPageNow(), params.getPageSize(), account);
            result.setMsg("成功查询符合条件的教师账号!");
            result.setData(pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("查询失败！请检查查询条件是否合法，或稍后再试!");
        }

        return result;
    }

    /*
    * 创建教师账号
    *
    *
    * */
    @PostMapping("createTeaAccount")
    public Result createTeaAccount(@RequestBody CreateTeaAccountVO params) {
        Result result = new Result();
        Integer state = 0;
        Account account = null;
        Teacher teacher = null;
        try {
            teacher = new Teacher();
            teacher.setTeaId(params.getTeaId());
            account = new Account(params.getUid(),
                    params.getUsername(),
                    params.getPassword(),
                    params.getNickname(),
                    "teacher",
                    teacher);
            state = accountService.checkTeaAccount(account);
            if(state != 0) {
                if(state == 1) {
                    result.setMsg("warn-用户名已存在");
                } else if (state == 2) {
                    result.setMsg("warn-该教师已有账号!请勿重复创建!");
                }
            } else {
                account.setUserType("teacher");
                accountService.createTeaAccount(account,account.getTeacher().getTeaId());
                result.setMsg("success-教师账号创建成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("教师账号创建失败，请稍后再试!");
        }
        return result;
    }
    /*
    * 创建学生账号
    *
    *
    * */
    @PostMapping("createStuAccount")
    public Result createStuAccount(@RequestBody CreateStuAccountVO params) {
        Result result = new Result();
        Integer state = 0;
        Account account = null;
        Student student = null;
        try {
           student =
              new Student(params.getStuId(),
                      params.getSex(),
                      params.getName(),
                      params.getSchoolId(),
                      null);
            account = new Account(null,
                    params.getUsername(),
                    params.getPassword(),
                    params.getNickname(),
                    "student",
                    null);
            state = accountService.checkStuAccount(account,student);
            if(state != 0) {
                if(state == 1) {
                    result.setMsg("warn-该学生已有账号，请勿重复注册!");
                } else if (state == 2) {
                    result.setMsg("warn-用户名已存在，请更改!");
                }
            } else {
                accountService.createStuAccount(account,student);
                result.setMsg("success-学生账号创建成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("学生账号创建失败，请稍后再试!");
        }
        return result;
    }

    /*
    * 删除账号
    *
    * */
    @GetMapping("deleteAccount")
    public Result deleteAccount(String uid) {
        Result result = new Result();
        try {
            accountService.deleteAccount(uid);
            result.setMsg("账号删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("账号删除失败！请稍后再试！");
        }
        return result;
    }
    /*
    * 更新账号信息
    *
    * */
    @PostMapping("updateAccount")
    public Result updateAccount(@RequestBody Account account) {
        Result result = new Result();
        try {
            accountService.updateAccount(account);
            result.setMsg("账号信息更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("账号信息删除失败，请稍后再试!");
        }
        return result;
    }

}
