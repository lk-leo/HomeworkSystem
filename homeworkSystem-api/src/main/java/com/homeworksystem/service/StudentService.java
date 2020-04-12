package com.homeworksystem.service;

import java.util.List;

import com.homeworksystem.bean.Student;

/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
public interface StudentService {
	/**
	  * 登陆
	 * @param studentId
	 * @param password
	 * @return
	 */
	public boolean login(String studentId,String password) ;
	/**
	 * test
	 * @param id
	 * @return
	 */
	public Student getStudent(String id) ;
	/**
	 * 注册
	 * @param student
	 */
	public void signUp(Student student) ;
	/**
	 * 查询所有学生
	 * @return
	 */
	public List<Student> selectAll();
	/**
	 * 根据学号查询学生信息
	 * @param studentId
	 * @return
	 */
	public Student selectByStudentId(String studentId) ;
	/**
	 * 更新
	 * @param student
	 */
	public void update(Student student) ;
}
