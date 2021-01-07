package com.tamiflu;

import com.tamiflu.entity.Teacher;
import com.tamiflu.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestTeacherService {
    @Autowired
    private TeacherService teacherService;

    @Test
    public void testInsertTeaInfo() {
        teacherService.insertTeaInfo(new Teacher("12458866","男","马云","副教授","无",null,null));
    }

    @Test
    public void testDeleteTeacherById(){
        teacherService.deleteTeacherById("233");
    }

    @Test
    public void testUpdateTeaInfo(){
        teacherService.updateTeaInfo(new Teacher("12458866","男","马云","教授","大佬",null,null));
    }

}