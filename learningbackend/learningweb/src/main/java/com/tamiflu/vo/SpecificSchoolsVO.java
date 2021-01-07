package com.tamiflu.vo;


import com.tamiflu.entity.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificSchoolsVO {
    private Integer pageNow;
    private Integer pageSize;
    private School schools;
}
