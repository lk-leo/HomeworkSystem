package com.homeworksystem.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homeworksystem.bean.Course;
import com.homeworksystem.dao.CourseMapper;
import com.homeworksystem.dao.curriculaVariableMapper;
import com.homeworksystem.service.CourseService;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
@Service
public class CourseServiceImp implements CourseService{
	@Autowired
	CourseMapper courseDao;
	@Autowired
	curriculaVariableMapper curriculaVariableDao;
	/**
	 * 查询所有课程
	 * @return
	 */
	public List<Course> selectAll(){
		List<Course> courses = courseDao.selectAll();
		//选课人数统计
		for(Course course:courses)
			course.setNum(curriculaVariableDao.studentNum(course.getCourseId()));
		return courses;
	}
	/**
	 * 查询该老师开的所有课程
	 * @param teacherId
	 * @return
	 */
	public List<Course> selectByTeacherId(String teacherId){
		List<Course> courses = courseDao.selectByTeacherId(teacherId);
		//选课人数统计
		for(Course course:courses)
			course.setNum(curriculaVariableDao.studentNum(course.getCourseId()));
		return courses;
	}
	/**
	 * 查询学生的选课信息
	 * @param studentId
	 * @return
	 */
	public List<Course> selectByStudentId(String studentId){
		return curriculaVariableDao.selectByStudentId(studentId);
	}
	/**
	 * 开课
	 * @param course
	 */
	public void insert(Course course) {
		courseDao.insert(course);
	}
	/**
	 * 关闭课程
	 * @param courseId
	 */
	public void delete(String courseId) {
		//先删选课信息
		curriculaVariableDao.deleteByCourseId(Integer.parseInt(courseId));
		courseDao.deleteByPrimaryKey(Integer.parseInt(courseId));
	}
}
