package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    private String lessonId;
    private String lessonName;
    private String schoolId;
    private String info;
    private School school;
    List<Teacher> listTeacher;
}
