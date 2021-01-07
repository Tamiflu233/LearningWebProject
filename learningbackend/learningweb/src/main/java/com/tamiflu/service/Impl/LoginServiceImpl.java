package com.tamiflu.service.Impl;

import com.tamiflu.entity.Account;
import com.tamiflu.mapper.AccountMapper;
import com.tamiflu.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Integer checkLogin(Account account) {
        Integer state = 0;
        Account targetAccount = null;
        targetAccount = accountMapper.selectAccountByUsername(account.getUsername());
        if(targetAccount == null) {
            state = 1;  //用户名不存在
        }else if(!(targetAccount.getPassword().equals(account.getPassword()))) {
            state = 2;  //密码错误
        } else if(!(targetAccount.getUserType().equals((account.getUserType())))) {
            state = 3;  //身份错误
        }
        return state;
    }
}
