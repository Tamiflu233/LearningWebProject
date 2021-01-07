package com.tamiflu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.Account;
import com.tamiflu.entity.Student;
import com.tamiflu.mapper.AccountMapper;
import com.tamiflu.mapper.StudentMapper;
import com.tamiflu.mapper.TeacherMapper;
import com.tamiflu.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public void insertAccount(Account account) {
        accountMapper.insertAccount(account);
        String uid = account.getUid();
        System.out.println("成功获取自增id: "+ uid);
    }

    @Override
    public void createTeaAccount(Account account, String teaId) {
        accountMapper.insertAccount(account);
        String uid = account.getUid();
        teacherMapper.updateTeaUid(teaId,uid);
    }

    @Override
    public Integer checkTeaAccount(Account account) {
        Integer state = 0;
        Account sameUser = null;
        sameUser = accountMapper.selectSameTeaAccount(account);
        if(sameUser != null) {
            if(sameUser.getTeacher().getTeaId().equals(account.getTeacher().getTeaId())) {
                state = 2;
            } else if(sameUser.getUsername().equals(account.getUsername())) {
                state = 1;
            }
        }
        return state;
    }

    @Override
    public PageInfo<Account> findAllTeaAccounts(Integer pageNow, Integer pageSize, Account account) {
        PageHelper.startPage(pageNow,pageSize);
        List<Account> accounts = accountMapper.selectAllTeaAccounts(account);
        PageInfo<Account> pageInfo = new PageInfo<>(accounts);
        return pageInfo;
    }

    @Override
    public void updateAccount(Account account) {
        accountMapper.updateAccount(account);
    }

    @Override
    public void deleteAccount(String uid) {
        accountMapper.deleteAccount(uid);

    }

    @Override
    public void createStuAccount(Account account, Student student) {
        accountMapper.insertAccount(account);
        student.setUid(account.getUid());
        studentMapper.insertStuInfo(student);
    }

    @Override
    public Integer checkStuAccount(Account account,Student student) {
        Integer state = 0;
        Account sameUser = null;
        Student sameStu = null;
        sameUser = accountMapper.selectAccountByUsername(account.getUsername());
        sameStu = studentMapper.findStudentById(student.getStuId());
        if(sameStu != null) {
            state = 1;
        } else if(sameUser != null) {
            state = 2;
        }
        return state;
    }

    @Override
    public String getUserNickname(String username) {
        return accountMapper.selectAccountByUsername(username).getNickname();
    }
}
