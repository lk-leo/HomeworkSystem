package com.homeworksystem.service;

import java.util.List;
import com.homeworksystem.bean.Course;

/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
public interface CourseService {
	/**
	 * 查询所有课程
	 * @return
	 */
	public List<Course> selectAll();
	/**
	 * 查询该老师开的所有课程
	 * @param teacherId
	 * @return
	 */
	public List<Course> selectByTeacherId(String teacherId);
	/**
	 * 查询学生的选课信息
	 * @param studentId
	 * @return
	 */
	public List<Course> selectByStudentId(String studentId);
	/**
	 * 开课
	 * @param course
	 */
	public void insert(Course course);
	/**
	 * 关闭课程
	 * @param courseId
	 */
	public void delete(String courseId);
	
}
