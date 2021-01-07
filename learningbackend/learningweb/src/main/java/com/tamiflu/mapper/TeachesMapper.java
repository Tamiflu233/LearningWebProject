package com.tamiflu.mapper;

import com.tamiflu.entity.Teaches;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeachesMapper {

//    根据id删除授课信息
    public void deleteTeachesInfoById(@Param("lessonId") String lessonId,@Param("teaId") String teaId);
//    添加授课信息
    public void insertTeachesInfo(Teaches teachesInfo);
}
