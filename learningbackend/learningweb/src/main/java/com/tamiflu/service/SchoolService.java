package com.tamiflu.service;

import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.School;

import java.util.List;

public interface SchoolService {
    //    查找所有学院
    List<School> findAllSchools();
    //    按id删除学院
    void deleteSchoolById(String schoolId);
    //    动态查找学院信息(改用pageHelper插件来帮助分页，在业务层实现)
    PageInfo<School> findSpecificSchools(Integer pageNum,Integer pageSize,School school);
    //    插入学院数据
    void insertSchool(School school);
    //    更新学院数据
    void updateSchool(School    school);
    //    分页查询
    List<School> findSchoolsByPage(Integer pageNow, Integer rows);
    //    查询总条数
    Long findTotals();
    //    按学院号查询学院信息
    public School findSchoolById(String schoolId);
}
