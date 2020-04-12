package com.homeworksystem.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.homeworksystem.bean.Homework;

/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
public interface HomeworkService {
	/**
	 * 学生提交作业，如果之前提交过作业，那么只更新作业内容
	 * @param homework
	 */
	public void insertHomework(Homework homework);
	/**
	 * 教师批改作业时需要按课程号获取所有作业
	 * @param questionId
	 * @return
	 */
	public List<Homework> selectByQuestionId(String questionId);
	/**
	 *  教师批改作业时需要按课程号获取所有作业，然后进行分页
	 * @param questionId
	 * @param pageNum
	 * @param pageSize
	 * @param subscriptNum 下面显示页数
	 * @return
	 */
	public PageInfo<Homework> selectByQuestionId(String questionId,int pageNum,int pageSize,int subscriptNum);
	/**
	 * 查询作业的成绩
	 * @param questionId
	 * @param studentId
	 * @return
	 */
	public Integer selectScore(String questionId,String studentId) ;
	/**
	 * 查询作业的内容
	 * @param questionId
	 * @param studentId
	 * @return
	 */
	public String selectHomeworkContext(String questionId,String studentId);
	/**
	 * 查找完成作业的学生人数
	 * @param questionId
	 * @return
	 */
	public int selectFinishedHomeworkNum(Integer questionId) ;
	/**
	 * 删除作业
	 * @param questionId
	 * @param studentId
	 */
	public void deleteByPrimaryKey(String questionId,String studentId);
	/**
	 * 更改分数
	 * @param questionId
	 * @param studentId
	 */
	public void updateScore(String questionId,String studentId,String score) ;
	/**
	 * 更新重复度
	 * @param homeworks
	 */
	public void updateRepeatability(List<Homework> homeworks) ;
}
