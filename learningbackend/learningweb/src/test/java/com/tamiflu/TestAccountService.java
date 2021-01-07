package com.tamiflu;

import com.tamiflu.entity.Account;
import com.tamiflu.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestAccountService {
    @Autowired
    private AccountService accountService;

    @Test
    public void testInsertAccount() {
        Account account = new Account();
        account.setUsername("Tamiflu233");
        account.setNickname("哈哈你们好");
        account.setPassword("weng7788");
        account.setUserType("teacher");
        accountService.insertAccount(account);
    }

    @Test
    public void testCreateTeaAccount() {
        Account account = new Account();
        account.setUsername("Ts");
        account.setNickname("汪汪");
        account.setPassword("weng2233");
        account.setUserType("teacher");
        accountService.createTeaAccount(account,"12458866");
    }
}
