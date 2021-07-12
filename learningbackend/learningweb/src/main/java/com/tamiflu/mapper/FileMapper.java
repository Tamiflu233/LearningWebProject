package com.tamiflu.mapper;

import com.tamiflu.entity.FileInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileMapper {

    public void insertFile(FileInfo fileInfo);

    public void deleteFile(String id);

    public List<FileInfo> selectAllFiles(@Param("lessonId") String lessonId,
                                         @Param("teaId") String teaId);
}
