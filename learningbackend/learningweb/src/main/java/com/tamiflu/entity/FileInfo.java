package com.tamiflu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private String id;
    private String lessonId;
    private String teaId;
    private String name;
    private String url;
    private String teaName;
    private Date date;
    private String size;
}
