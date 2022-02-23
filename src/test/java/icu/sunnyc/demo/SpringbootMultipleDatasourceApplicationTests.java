package icu.sunnyc.demo;

import icu.sunnyc.demo.entity.Student;
import icu.sunnyc.demo.entity.Teacher;
import icu.sunnyc.demo.mapper.db1.StudentMapper;
import icu.sunnyc.demo.mapper.db2.TeacherMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMultipleDatasourceApplicationTests {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    public void testDataSource() {
        Student studentById = studentMapper.getStudentById(1);
        Teacher teacherById = teacherMapper.getTeacherById(1);
        System.out.println(studentById);
        System.out.println(teacherById);
    }


}
