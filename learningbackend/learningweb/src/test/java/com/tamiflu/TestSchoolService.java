package com.tamiflu;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tamiflu.entity.School;
import com.tamiflu.mapper.SchoolMapper;
import com.tamiflu.service.SchoolService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestSchoolService {
    @Autowired
    private SchoolService schoolService;

    @Test
    public void testFindAllSchools() {
        schoolService.findAllSchools().forEach(item-> System.out.println(item));
    }

    @Test
    public void testInsertSchool() {
        schoolService.insertSchool(new School("1088","沪江学院","无",null));
    }

    @Test
    public void testUpdateSchool() {
        schoolService.updateSchool(new School("13333","沪江学院","呵呵",null));
    }

    @Test
    public void testFindSpecificSchools() {
//        PageHelper.startPage(1,5);
//        List<School> schools = schoolService.findSpecificSchools(new School());
//        PageInfo<School> pageInfo = new PageInfo<School>(schools);
//        System.out.println("总记录数： "+pageInfo.getTotal());
//        System.out.println(pageInfo.getList().get(0));
    }
}
