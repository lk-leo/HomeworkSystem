package com.homeworksystem.dao;

import com.homeworksystem.bean.Course;
import com.homeworksystem.bean.CurriculaVariable;
import com.homeworksystem.bean.Student;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 持久层
 * 功能：对数据库进行增删改查，为service层提供方法操作数据库
 * 具体实现在conf/mapper/curriculaVariableMapper.xml
 *
 */
public interface curriculaVariableMapper {

	/**
	 * 根据主键删除选课信息
	 * @param studentId
	 * @param courseId
	 * @return
	 */
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("courseId") Integer courseId);

    /**
     * 插入一条新的选课信息
     * @param record
     * @return
     */
    int insert(CurriculaVariable record);
    
    /**
     * 查询所有选课信息
     * @return
     */
    List<CurriculaVariable> selectAll();
    
    /**
     * 查询选这门课的学生id
     * @param courseId
     * @return
     */
    List<Student> selectByCourseId(@Param("courseId")Integer courseId);
    
    /**
     * 查询学生选的课程
     * @param studentId
     * @return
     */
    List<Course> selectByStudentId(@Param("studentId")String studentId);
    
    /**
     *  判断这个学生是否选了这门课，为空则没选
     * @param studentId
     * @param courseId
     * @return
     */
    CurriculaVariable selectByPrimaryKey(@Param("studentId")String studentId,
    		@Param("courseId")Integer courseId);
    
    /**
     * 删除这门课的选课信息
     * @param courseId
     */
    void deleteByCourseId(@Param("courseId")Integer courseId);
    /**
     * 选课人数
     * @param courseId
     * @return
     */
    Integer studentNum(@Param("courseId")Integer courseId);
}