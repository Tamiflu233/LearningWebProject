package com.tamiflu.service;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.FileInfo;

public interface FileService {
    public PageInfo<FileInfo> selectAllFiles(Integer pageNow,
                                             Integer pageSize,
                                             String lessonId,
                                             String teaId
    );

    public void insertFile(FileInfo fileInfo);

    public void deleteFile(String id);
}
