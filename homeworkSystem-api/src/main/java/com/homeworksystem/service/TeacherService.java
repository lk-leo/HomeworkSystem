package com.homeworksystem.service;

import java.util.List;
import com.homeworksystem.bean.Teacher;

/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
public interface TeacherService {
	/**
	  * 登陆
	 * @param teacherId
	 * @param password
	 * @return
	 */
	public boolean login(String teacherId,String password) ;
	/**
	 * 注册
	 * @param student
	 */
	public void signUp(Teacher teacher) ;
	/**
	 * 查询所有老师
	 * @return
	 */
	public List<Teacher> selectAll();
	/**
	 * 根据id查询老师
	 * @param teacher
	 * @return
	 */
	public Teacher selectByTeacherId(String teacherId) ;
	/**
	 * 更新
	 * @param teacher
	 */
	public void update(Teacher teacher) ;
}
