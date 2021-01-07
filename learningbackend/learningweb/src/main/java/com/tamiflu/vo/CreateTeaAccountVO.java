package com.tamiflu.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeaAccountVO {
    private String uid;
    private String username;
    private String password;
    private String nickname;
    private String userType;
    private String teaId;

}
