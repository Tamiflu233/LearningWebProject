package com.tamiflu.controller;

import com.tamiflu.entity.Account;
import com.tamiflu.service.AccountService;
import com.tamiflu.service.LoginService;
import com.tamiflu.service.TokenService;
import com.tamiflu.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountService accountService;

    @PostMapping("checkLogin")
    public Result checkLogin(@RequestBody Account account) {
        Result result = new Result();
        Integer state = 0;
        String userType = account.getUserType();
//        处理前端单选按钮值为中文的情况
        if(userType.equals("学生")) {
            account.setUserType("student");
        } else if(userType.equals("教师")) {
            account.setUserType("teacher");
        } else if(userType.equals("管理员")) {
            account.setUserType("admin");
        }
        try {
            state = loginService.checkLogin(account);
            if(state == 0) {
                result.setMsg("success-登陆成功！");
//                生成token并返回
                String token= tokenService.sign(account);
                result.setData(token);
            } else if(state == 1) {
                result.setMsg("warn-用户不存在！请重新输入！");
            } else if(state == 2) {
                result.setMsg("warn-密码错误!请重新输入！");
            } else if(state == 3) {
                result.setMsg("warn-用户身份错误!请勿选择错误的身份！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("服务器出错，登陆失败，请稍后再试!");
        }
        return result;
    }
//    获取昵称
    @GetMapping("getNickname")
    public Result getNickname(String username) {
        Result result = new Result();
        String nickname = null;
        try {
            nickname = accountService.getUserNickname(username);
            result.setMsg("成功获取用户昵称！");
            result.setData(nickname);
        } catch (Exception e) {
            e.printStackTrace();
            result.setCode(500);
            result.setMsg("获取用户昵称失败，请稍后再试");
        }
        return  result;
    }
}
