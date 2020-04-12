package com.homeworksystem.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeworksystem.bean.Course;
import com.homeworksystem.bean.CurriculaVariable;
import com.homeworksystem.bean.Student;
import com.homeworksystem.dao.curriculaVariableMapper;
import com.homeworksystem.service.CurriculaVariableService;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
@Service
public class CurriculaVariableServiceImp implements CurriculaVariableService{
	@Autowired
	curriculaVariableMapper curriculaVariableDao;
	/**
	 * 查询这个学生选的课程
	 * @return
	 */
	public List<Course> selectByStudentId(String studentId){
		return curriculaVariableDao.selectByStudentId(studentId);
	}
	/**
	 * 查询所有选这门课程的学生学号
	 * @return
	 */
	public List<Student> selectByCourseId(String courseId){
		return curriculaVariableDao.selectByCourseId(Integer.parseInt(courseId));
	}
	/**
	 * 判断学生是否已经选过该门课
	 * @return
	 */
	public boolean selectByPrimaryKey(String studentId,String courseId) {
		if(curriculaVariableDao.selectByPrimaryKey(studentId, Integer.parseInt(courseId))==null)
			return false;
		return true;
	}
	/**
	 * 学生选课
	 * @param curriculaVariable
	 */
	public void insert(CurriculaVariable curriculaVariable) {
		curriculaVariableDao.insert(curriculaVariable);
	}
	/**
	 * 学生退选
	 * @param studentId
	 * @param courseId
	 */
	public void delete(String studentId,Integer courseId) {
		curriculaVariableDao.deleteByPrimaryKey(studentId, courseId);
	}
	/**
	 * 选课人数
	 * @param courseId
	 * @return
	 */
	public Integer studentNum(Integer courseId) {
		return curriculaVariableDao.studentNum(courseId);
	}
}
