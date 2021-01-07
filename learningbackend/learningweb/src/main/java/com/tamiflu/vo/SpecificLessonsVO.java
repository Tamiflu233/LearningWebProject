package com.tamiflu.vo;

import com.tamiflu.entity.Lesson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificLessonsVO {
    private Integer pageNow;
    private Integer pageSize;
    private Lesson lessons;
}
