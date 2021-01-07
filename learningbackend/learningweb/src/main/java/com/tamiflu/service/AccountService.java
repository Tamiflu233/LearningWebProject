package com.tamiflu.service;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Account;
import com.tamiflu.entity.Student;

public interface AccountService {
    //    插入新账号
    public void insertAccount(Account account);

//    创建老师账号，并将uid绑定到老师信息
    public void createTeaAccount(Account account, String teaId);
//    验证账号用户名是否存在，以及该教师是否已有账号
    public Integer checkTeaAccount(Account account);
//    分页查询所有教师账号信息
    public PageInfo<Account> findAllTeaAccounts(Integer pageNow,Integer pageSize,Account account);
//    更新账号信息
    public void updateAccount(Account account);
//    删除账号
    public void deleteAccount(String uid);
//    创建学生账号
    public void createStuAccount(Account account, Student student);
    //    验证账号用户名是否存在，以及该教师是否已有账号
    public Integer checkStuAccount(Account account,Student student);

//    获取用户昵称
    public String getUserNickname(String username);

}
