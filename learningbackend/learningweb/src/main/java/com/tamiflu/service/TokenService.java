package com.tamiflu.service;

import com.tamiflu.entity.Account;

public interface TokenService {
//    获取登录token
    public String sign(Account account);
//    验证token
    public boolean verify(String token);
}
