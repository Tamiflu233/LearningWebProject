package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private String aid;
    private String qid;
    private String lessonId;
    private Date answerDate;
    private String teaId;
    private String answerContent;
    private Integer isRead;
}
