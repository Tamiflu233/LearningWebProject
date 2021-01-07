package com.tamiflu.mapper;


import com.tamiflu.entity.School;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolMapper {
//    查找所有学院
    public List<School> findAllSchools();
//    动态查找学院信息(改用pageHelper插件来帮助分页，在业务层实现)
    public List<School> findSpecificSchools(School school);
//    按id删除学院
    public void deleteSchoolById(String schoolId);
//    插入学院数据
    public void insertSchool(School school);
//    更新学院数据
    public void updateSchool(School school);
    //    分页查询
    public List<School> findSchoolsByPage(@Param("start")Integer start, @Param("rows") Integer rows);
    //    查询总条数
    public Long findTotals();
//    按学院号查询学院信息
    public School findSchoolById(String schoolId);
}
