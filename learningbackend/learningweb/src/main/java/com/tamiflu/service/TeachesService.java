package com.tamiflu.service;

import com.tamiflu.entity.Teaches;

public interface TeachesService {
    //    根据id删除授课信息
    public void deleteTeachesInfoById(String lessonId, String teaId);
    //    添加授课信息
    public void insertTeachesInfo(Teaches teachesInfo);
}
