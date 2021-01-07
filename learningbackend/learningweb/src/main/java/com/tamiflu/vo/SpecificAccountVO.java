package com.tamiflu.vo;

import com.tamiflu.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecificAccountVO {
    private Integer pageNow;
    private Integer pageSize;
    private String teaId;
    private Account accounts;
}
