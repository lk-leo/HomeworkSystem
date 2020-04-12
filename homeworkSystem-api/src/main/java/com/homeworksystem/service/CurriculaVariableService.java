package com.homeworksystem.service;

import java.util.List;


import com.homeworksystem.bean.Course;
import com.homeworksystem.bean.CurriculaVariable;
import com.homeworksystem.bean.Student;

/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
public interface CurriculaVariableService {
	/**
	 * 查询这个学生选的课程
	 * @return
	 */
	public List<Course> selectByStudentId(String studentId);
	/**
	 * 查询所有选这门课程的学生学号
	 * @return
	 */
	public List<Student> selectByCourseId(String courseId);
	/**
	 * 判断学生是否已经选过该门课
	 * @return
	 */
	public boolean selectByPrimaryKey(String studentId,String courseId);
	/**
	 * 学生选课
	 * @param curriculaVariable
	 */
	public void insert(CurriculaVariable curriculaVariable);
	/**
	 * 学生退选
	 * @param studentId
	 * @param courseId
	 */
	public void delete(String studentId,Integer courseId);
	/**
	 * 选课人数
	 * @param courseId
	 * @return
	 */
	public Integer studentNum(Integer courseId);
}
