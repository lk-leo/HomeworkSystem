package com.homeworksystem.serviceImp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeworksystem.bean.Course;
import com.homeworksystem.bean.Question;
import com.homeworksystem.bean.Student;
import com.homeworksystem.dao.HomeworkMapper;
import com.homeworksystem.dao.QuestionMapper;
import com.homeworksystem.dao.curriculaVariableMapper;
import com.homeworksystem.service.QuestionService;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
@Service
public class QuestionServiceImp implements QuestionService{
	@Autowired
	QuestionMapper questionDao;
	@Autowired
	curriculaVariableMapper curriculaVariableDao;
	@Autowired
	HomeworkMapper homeworkDao;
	/**
	 * 根据学号找出这个学生所有问题
	 * @param studentId
	 * @return
	 */
	public List<Question> selectByStudentId(String studentId){
		//先找出这个学生所选的课程
		List<Course> courses = curriculaVariableDao.selectByStudentId(studentId);
		//将所选课程下的问题都放在这个容器
		List<Question> questions=new ArrayList<Question>();
		//遍历课程
		for(Course course:courses) {
			//通过课程号选出这个课程下的所有问题
			List<Question> temps = selectByCourseId(course.getCourseId());
			//遍历这些问题，稍做加工，比如添加一个问题概述，然后放到questions
			for(Question temp:temps) {
				Integer Score=homeworkDao.selectScore(temp.getQuestionId(), studentId);
				int score=Score==null?0:Score;
				Question q=new Question(temp.getQuestionId(), 
										temp.getCourseId(), 
										temp.getQuestion(), 
										//题目描述outline设置为小于10字符，显示全部题目，否则只显示前10个字符加...
										temp.getQuestion().trim().length()<=10?temp.getQuestion():(temp.getQuestion().substring(0,10)+"..."), //题目概述
										course.getCourseName(),
										""+score,
										temp.getDeadline());
				questions.add(q);
			}
		}
		return questions;
	}
	/**
	 * 根据课程号查找问题
	 * @param courseId
	 * @return
	 */
	public List<Question> selectByCourseId(Integer courseId) {
		List<Question> questions = questionDao.selectByCourseId(courseId);
		//题目描述outline设置为小于10字符，显示全部题目，否则只显示前10个字符加...
		questions.forEach( q -> q.setOutline(q.getQuestion().trim().length()<=10?q.getQuestion():(q.getQuestion().substring(0,10)+"...")));
		return questions;
	}
	/**
	 * 根据问题号查找问题
	 * @param questionId
	 * @return
	 */
	public Question selectByQuestionId(Integer questionId) {
		return questionDao.selectByPrimaryKey(questionId);
	}
	/**
	 * 教师提交问题
	 * @param question
	 */
	public void insertQuestion(Question question) {
		questionDao.insert(question);
	}
	/**
	 * 取消布置某次作业
	 * @param questionId
	 */
	public void deleteQuestion(String courseId,String questionId) {
		//对应的学生作业也得删除
		List<Student> students = curriculaVariableDao.selectByCourseId(Integer.parseInt(courseId));
		for(Student s:students)
			homeworkDao.deleteByPrimaryKey(s.getStudentId(), Integer.parseInt(questionId));
		questionDao.deleteByPrimaryKey(Integer.parseInt(questionId));
	}
	/**
	 * 开启查重
	 */
	@Override
	public void startDuplicateChecking(String questionId) {
		questionDao.updateDupCheck(Integer.parseInt(questionId),1);
	}
	/**
	 * 关闭查重
	 */
	@Override
	public void closeDuplicateChecking(String questionId) {
		questionDao.updateDupCheck(Integer.parseInt(questionId),0);
	}
}
