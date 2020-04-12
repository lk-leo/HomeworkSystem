package com.homeworksystem.serviceImp;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.homeworksystem.bean.Student;
import com.homeworksystem.dao.StudentMapper;
import com.homeworksystem.service.StudentService;
/**
 * 
 * 建立在持久层(DAO)之上，调用持久层的方法为Controller提供服务
 * 
 * 持久层例如studentDao只增删改查数据库中students表，这就需要Service层调用多个
 * Dao的方法从数据库中获取数据，然后进行整合修改，最后返回给Controller
 */
@Service
public class StudentServiceImp implements StudentService{
	@Autowired
	StudentMapper studentDao;
	/**
	  * 登陆
	 * @param studentId
	 * @param password
	 * @return
	 */
	@Override
	public boolean login(String studentId,String password) {
		String passWord2 = studentDao.selectByPrimaryKey(studentId).getPassWord();
		if(passWord2!=null&&passWord2.contentEquals(password))
			return true;
		return false;
	}
	/**
	 * test
	 * @param id
	 * @return
	 */
	@Override
	public Student getStudent(String id) {
		return studentDao.selectByPrimaryKey(id);
	}
	/**
	 * 注册
	 * @param student
	 */
	@Override
	public void signUp(Student student) {
		studentDao.insert(student);
	}
	/**
	 * 查询所有学生
	 * @return
	 */
	@Override
	public List<Student> selectAll(){
		return studentDao.selectAll();
	}
	/**
	 * 根据学号查询学生信息
	 * @param studentId
	 * @return
	 */
	@Override
	public Student selectByStudentId(String studentId) {
		return studentDao.selectByPrimaryKey(studentId);
	}
	/**
	 * 更新
	 * @param student
	 */
	@Override
	public void update(Student student) {
		studentDao.updateByPrimaryKey(student);
	}
}
