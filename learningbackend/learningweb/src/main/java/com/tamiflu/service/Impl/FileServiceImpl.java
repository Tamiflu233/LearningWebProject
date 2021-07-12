package com.tamiflu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.FileInfo;
import com.tamiflu.mapper.FileMapper;
import com.tamiflu.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileMapper fileMapper;

    @Override
    public PageInfo<FileInfo> selectAllFiles(Integer pageNow, Integer pageSize, String lessonId,
                                             String teaId) {
        PageHelper.startPage(pageNow, pageSize);
        List<FileInfo> fileInfos = fileMapper.selectAllFiles(lessonId, teaId);
        PageInfo<FileInfo> pageInfo = new PageInfo<>(fileInfos);
        return pageInfo;
    }

    @Override
    public void insertFile(FileInfo fileInfo) {
        fileMapper.insertFile(fileInfo);
    }

    @Override
    public void deleteFile(String id) {
        fileMapper.deleteFile(id);
    }
}
