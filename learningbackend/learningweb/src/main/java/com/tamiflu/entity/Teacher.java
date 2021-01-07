package com.tamiflu.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private String teaId;
    private String sex;
    private String name;
    private String profession;
    private String info;
    private String uid;
    private List<Lesson> listLesson;
}
