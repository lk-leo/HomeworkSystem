package com.homeworksystem.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeworksystem.bean.Teacher;
import com.homeworksystem.dao.TeacherMapper;
import com.homeworksystem.service.TeacherService;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
@Service
public class TeacherServiceImp implements TeacherService{
	@Autowired
	TeacherMapper teacherDao;
	/**
	  * 登陆
	 * @param teacherId
	 * @param password
	 * @return
	 */
	@Override
	public boolean login(String teacherId,String password) {
		if(teacherDao.selectByPrimaryKey(teacherId).getPassWord().equals(password))
			return true;
		return false;
		
	}
	/**
	 * 注册
	 * @param student
	 */
	@Override
	public void signUp(Teacher teacher) {
		teacherDao.insert(teacher);
	}
	/**
	 * 查询所有老师
	 * @return
	 */
	@Override
	public List<Teacher> selectAll(){
		return teacherDao.selectAll();
	}
	/**
	 * 根据id查询老师
	 * @param teacher
	 * @return
	 */
	@Override
	public Teacher selectByTeacherId(String teacherId) {
		return teacherDao.selectByPrimaryKey(teacherId);
	}
	/**
	 * 更新
	 * @param teacher
	 */
	@Override
	public void update(Teacher teacher) {
		teacherDao.updateByPrimaryKey(teacher);
	}
}
