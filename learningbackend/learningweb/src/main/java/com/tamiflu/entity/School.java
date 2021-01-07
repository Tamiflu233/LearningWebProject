package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class School {
    private String schoolId;
    private String schoolName;
    private String info;
    private List<Teacher> listTeachers;
}
