package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private String qid;
    private String lessonId;
    private String title;
    private String content;
    private Date publishDate;
    private String username;
    private String nickname;
    private String teaId;
    private Integer isAnswered;
    private Teacher teacher;
    private Lesson lesson;
    private Answer answer;
}
