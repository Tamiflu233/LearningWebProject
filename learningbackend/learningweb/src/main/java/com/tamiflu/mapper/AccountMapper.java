package com.tamiflu.mapper;

import com.tamiflu.entity.Account;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
//    添加新账号
    public void insertAccount(Account account);
//    获取账号信息与对应的教师信息
    public List<Account> selectAllTeaAccounts(@Param("account") Account account);
//    获取与该账号具有相同用户名或教师号一致的账号
    public Account selectSameTeaAccount(@Param("account") Account account);
//    删除账号
    public void deleteAccount(String uid);
//    更新账号信息
    public void updateAccount(Account account);
//    查看该用户名的账号
    public Account selectAccountByUsername(String username);
}
