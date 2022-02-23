package icu.sunnyc.demo.mapper.db1;

import icu.sunnyc.demo.entity.Student;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author: houcheng
 * @date: 2022/2/22 15:26:23
 * @version: V1.0
 * @description: TODO
 * @modify
 */
@Repository
public interface StudentMapper {

    /**
     * 根据id获取student对象
     * @param id id
     * @return student对象
     */
    Student getStudentById(Integer id);
}
