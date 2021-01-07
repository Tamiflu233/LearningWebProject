package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeachesInfoCondition {
    private String lessonId;
    private String schoolId;
    private String teaId;
}
