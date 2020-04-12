package com.homeworksystem.dao;

import com.homeworksystem.bean.Student;
import java.util.List;

/**
 * 持久层
 * 功能：对数据库进行增删改查，为service层提供方法操作数据库
 * 具体实现在conf/mapper/StudentMapper.xml
 *
 */
public interface StudentMapper {
  
    int deleteByPrimaryKey(String studentId);

   
    int insert(Student record);

    
    Student selectByPrimaryKey(String studentId);

  
    List<Student> selectAll();

   
    int updateByPrimaryKey(Student record);
}