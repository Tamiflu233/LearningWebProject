package com.tamiflu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.School;
import com.tamiflu.mapper.SchoolMapper;
import com.tamiflu.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolMapper schoolMapper;
    @Override
    public List<School> findAllSchools() {
        return schoolMapper.findAllSchools();
    }

    @Override
    public void deleteSchoolById(String schoolId) {
        schoolMapper.deleteSchoolById(schoolId);
    }

    @Override
    public PageInfo<School> findSpecificSchools(Integer pageNow,Integer pageSize,School school) {
        PageHelper.startPage(pageNow,pageSize);
        List<School> schools = schoolMapper.findSpecificSchools(school);
        PageInfo<School> pageInfo = new PageInfo<>(schools);
        return pageInfo;
    }

    @Override
    public void insertSchool(School school) {
        schoolMapper.insertSchool(school);
    }

    @Override
    public void updateSchool(School school) {
        schoolMapper.updateSchool(school);
    }

    @Override
    public List<School> findSchoolsByPage(Integer pageNow, Integer rows) {
        int start = (pageNow-1)*rows;
        return schoolMapper.findSchoolsByPage(start, rows);
    }

    @Override
    public Long findTotals() {
        return schoolMapper.findTotals();
    }

    @Override
    public School findSchoolById(String schoolId) {
        return schoolMapper.findSchoolById(schoolId);
    }
}
