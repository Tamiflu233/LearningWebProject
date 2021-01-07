package com.tamiflu.vo;

import com.tamiflu.entity.TeachesInfoCondition;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecifinTeachesInfoVO {
    private Integer pageNow;
    private Integer pageSize;
    private TeachesInfoCondition condition;
}
