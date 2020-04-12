package com.homeworksystem.service;

import java.util.List;
import com.homeworksystem.bean.Question;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
public interface QuestionService {
	/**
	 * 根据学号找出这个学生所有问题
	 * @param studentId
	 * @return
	 */
	public List<Question> selectByStudentId(String studentId);
	/**
	 * 根据课程号查找问题
	 * @param courseId
	 * @return
	 */
	public List<Question> selectByCourseId(Integer courseId);
	/**
	 * 根据问题号查找问题
	 * @param questionId
	 * @return
	 */
	public Question selectByQuestionId(Integer questionId);
	/**
	 * 教师提交问题
	 * @param question
	 */
	public void insertQuestion(Question question);
	/**
	 * 取消布置某次作业
	 * @param questionId
	 */
	public void deleteQuestion(String courseId,String questionId);
	/**
	 * 开启查重
	 * @param questionId
	 */
	public void startDuplicateChecking(String questionId) ;
	/**
	 * 关闭查重
	 * @param questionId
	 */
	public void closeDuplicateChecking(String questionId) ;
	
}
