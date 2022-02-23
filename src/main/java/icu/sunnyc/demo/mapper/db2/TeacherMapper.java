package icu.sunnyc.demo.mapper.db2;

import icu.sunnyc.demo.entity.Student;
import icu.sunnyc.demo.entity.Teacher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author: houcheng
 * @date: 2022/2/22 15:27:10
 * @version: V1.0
 * @description: TODO
 * @modify
 */
@Repository
public interface TeacherMapper {

    /**
     * 根据id获取student对象
     * @param id id
     * @return student对象
     */
    Teacher getTeacherById(Integer id);
}
