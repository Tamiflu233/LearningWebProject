package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private String stuId;
    private String sex;
    private String name;
    private String schoolId;
    private String uid;
}
