package com.homeworksystem.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.homeworksystem.bean.Homework;
import com.homeworksystem.dao.HomeworkMapper;
import com.homeworksystem.service.HomeworkService;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
@Service
public class HomeworkServiceImp implements HomeworkService{
	@Autowired
	HomeworkMapper homeworkDao;
	/**
	 * 学生提交作业，如果之前提交过作业，那么只更新作业内容
	 * @param homework
	 */
	public void insertHomework(Homework homework) {
		Homework oldHomework=homeworkDao.selectByPrimaryKey(homework.getStudentId(), homework.getQuestionId());
		if(oldHomework==null) {
			homeworkDao.insert(homework);
		}else {
			oldHomework.setHomework(homework.getHomework());
			homeworkDao.updateByPrimaryKey(oldHomework);
		}
	}
	
	/**
	 * 教师批改作业时需要按课程号获取所有作业
	 * @param questionId
	 * @return
	 */
	public List<Homework> selectByQuestionId(String questionId){
		return homeworkDao.selectByQuestionId(Integer.parseInt(questionId));
	}
	
	@Override
	public PageInfo<Homework> selectByQuestionId(String questionId, int pageNum, int pageSize,int subscriptNum) {
		//分页，每页一个
		PageHelper.startPage(pageNum,pageSize);
		List<Homework> homeworks = selectByQuestionId(questionId);
		PageInfo<Homework> info=new PageInfo<Homework>(homeworks,subscriptNum);
		return info;
	}
	/**
	 * 查询作业的成绩
	 * @param questionId
	 * @param studentId
	 * @return
	 */
	public Integer selectScore(String questionId,String studentId) {
		return homeworkDao.selectScore(Integer.parseInt(questionId), studentId);
	}
	/**
	 * 查询作业的内容
	 * @param questionId
	 * @param studentId
	 * @return
	 */
	public String selectHomeworkContext(String questionId,String studentId) {
		Homework homework=homeworkDao.selectByPrimaryKey(studentId, Integer.parseInt(questionId));
		String context=homework!=null?homework.getHomework():"";
		return context;
	}
	/**
	 * 查找完成作业的学生人数
	 * @param questionId
	 * @return
	 */
	public int selectFinishedHomeworkNum(Integer questionId) {
		return homeworkDao.selectNumByQuestionId(questionId);
	}
	/**
	 * 删除作业
	 * @param questionId
	 * @param studentId
	 */
	public void deleteByPrimaryKey(String questionId,String studentId) {
		homeworkDao.deleteByPrimaryKey(studentId, Integer.parseInt(questionId));
	}
	/**
	 * 更改分数
	 * @param questionId
	 * @param studentId
	 */
	public void updateScore(String questionId,String studentId,String score) {
		homeworkDao.updateScore(Integer.parseInt(questionId), studentId, Integer.parseInt(score));
	}
	/**
	 * 更新重复度
	 * @param homeworks
	 */
	public void updateRepeatability(List<Homework> homeworks) {
		homeworkDao.updateRepeatability(homeworks);
	}
	
}
