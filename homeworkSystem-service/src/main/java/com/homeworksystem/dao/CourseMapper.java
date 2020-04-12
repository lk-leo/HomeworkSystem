package com.homeworksystem.dao;

import com.homeworksystem.bean.Course;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 持久层
 * 功能：对数据库进行增删改查，为service层提供方法操作数据库
 * 具体实现在conf/mapper/CourseMapper.xml
 *
 */
public interface CourseMapper {
	/**
	 * 根据 courseId删除课程
	 * @param courseId
	 * @return
	 */
    int deleteByPrimaryKey(Integer courseId);
    /**
     * 插入一个新的课程
     * @param record
     * @return
     */
    int insert(Course record);
    /**
     * 根据courseId查询课程
     * @param courseId
     * @return
     */
    Course selectByPrimaryKey(Integer courseId);

    /**
     * 查询所有课程
     * @return
     */
    List<Course> selectAll();
    
    /**
     * 查询指导teacherId教师开的课程
     * @param teacherId
     * @return
     */
    List<Course> selectByTeacherId(@Param("teacherId")String teacherId);
    /**
     * 更新课程信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(Course record);
}