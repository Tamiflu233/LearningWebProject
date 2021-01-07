package com.tamiflu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStuAccountVO {
    private String nickname;
    private String username;
    private String password;
    private String checkpass;
    private String stuId;
    private String name;
    private String schoolId;
    private String sex;
}
