package com.tamiflu.vo;

import com.tamiflu.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificTeachersVO {
    private Integer pageNow;
    private Integer pageSize;
    private Teacher teacher;
}
