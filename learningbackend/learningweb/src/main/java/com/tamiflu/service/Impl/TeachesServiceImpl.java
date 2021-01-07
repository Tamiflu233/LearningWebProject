package com.tamiflu.service.Impl;

import com.tamiflu.entity.Teaches;
import com.tamiflu.mapper.TeachesMapper;
import com.tamiflu.service.TeachesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeachesServiceImpl implements TeachesService {

    @Autowired
    private TeachesMapper teachesMapper;

    @Override
    public void deleteTeachesInfoById(String lessonId, String teaId) {
        teachesMapper.deleteTeachesInfoById(lessonId,teaId);
    }

    @Override
    public void insertTeachesInfo(Teaches teachesInfo) {
        teachesMapper.insertTeachesInfo(teachesInfo);
    }
}
