package com.homeworksystem.dao;

import com.homeworksystem.bean.Teacher;
import java.util.List;

/**
 * 持久层
 * 功能：对数据库进行增删改查，为service层提供方法操作数据库
 * 具体实现在conf/mapper/TeacherMapper.xml
 *
 */
public interface TeacherMapper {
    
    int deleteByPrimaryKey(String teacherId);

    
    int insert(Teacher record);

   
    Teacher selectByPrimaryKey(String teacherId);

    
    List<Teacher> selectAll();

    
    int updateByPrimaryKey(Teacher record);
}