package com.tamiflu.vo;

import com.tamiflu.entity.Teaches;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificTeachesVO {
    private Integer pageNow;
    private Integer pageSize;
    private Teaches teachesInfo;
}
