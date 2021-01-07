package com.tamiflu;

import com.tamiflu.entity.Account;
import com.tamiflu.entity.Teacher;
import com.tamiflu.mapper.AccountMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccountMapperTest {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    void selectSameTeaAccount() {
        Account account = new Account();
        account.setUsername("asmin");
        account.setTeacher(new Teacher("776688",null,null,null,null,null,null));
        System.out.println(accountMapper.selectSameTeaAccount(account));
    }
}