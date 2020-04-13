package com.homeworksystem.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.homeworksystem.bean.Course;
import com.homeworksystem.bean.CurriculaVariable;
import com.homeworksystem.bean.Homework;
import com.homeworksystem.bean.Question;
import com.homeworksystem.service.CourseService;
import com.homeworksystem.service.CurriculaVariableService;
import com.homeworksystem.service.HomeworkService;
import com.homeworksystem.service.QuestionService;
import com.homeworksystem.util.DuplicateChecking;

/**
 * 
 * 学生页面发送的请求都由这个类处理，除了和教师页面功能相似的请求
 */
@Controller
public class StudentController {
	/**
	 * 这个类提供的方法用于增删改查数据库中课程相关的数据。
	 */
	@Autowired
	CourseService courseService;
	/**
	 * 这个类提供的方法用于增删改查数据库中选课相关的数据。
	 */
	@Autowired
	CurriculaVariableService curriculaVariableService;
	/**
	 * 这个类提供的方法用于增删改查数据库中问题相关的数据。
	 */
	@Autowired
	QuestionService questionService;
	/**
	 * 这个类提供的方法用于增删改查数据库中作业相关的数据。
	 */
	@Autowired
	HomeworkService homeworkService;
	/**
	 * 查重服务
	 */
	@Autowired
	DuplicateChecking duplicateChecking;
	/**
	 * 跳转到学生主页面
	 * @param studentId
	 * @param page
	 * @return
	 */
	@RequestMapping("/mainMenu/student/{page}/{studentId}")
	public ModelAndView toMainMenu(@PathVariable("studentId")String studentId,
			@PathVariable("page")String page) {
		ModelAndView mv=new ModelAndView("studentMainMenu");
		mv.addObject("id", studentId);
		if(page.equals("myCourse")) {
			mv.addObject("page", "myCourse");
			//查询这个学生选的课程，并在页面显示
			List<Course> myCourses= courseService.selectByStudentId(studentId);
			mv.addObject("myCourses", myCourses);
		}else if(page.equals("chooseCourse")) {
			mv.addObject("page", "chooseCourse");
			//查询这个学生选的课程，并在页面显示
			List<Course> myCourses= courseService.selectByStudentId(studentId);
			mv.addObject("myCourses", myCourses);
			//全校课程
			List<Course> courses = courseService.selectAll();
			mv.addObject("allTheCourses", courses);
		}else if(page.equals("chooseQuestion")) {
			mv.addObject("page", "chooseQuestion");
			//查询这个学生选的课程内所有问题，并在页面显示
			List<Question> questions = questionService.selectByStudentId(studentId);
			//获取当前时间
			Timestamp current=new Timestamp(System.currentTimeMillis());
			//判断该问题是否过了截止日期
			questions.forEach( (q) -> q.setIsLate(current.after(q.getDeadline())));
			mv.addObject("allTheQuestions", questions);
		}
		return mv;
	}
	/**
	 * 学生选课
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	@RequestMapping("choose/{studentId}/{courseId}")
	public ModelAndView chooseCourse(@PathVariable("studentId")String studentId
			,@PathVariable("courseId")String courseId) {
		//先判断这门课是否已经选过
		if(curriculaVariableService.selectByPrimaryKey(studentId, courseId)) {

		}else {
			curriculaVariableService.insert(new CurriculaVariable(studentId,courseId));
		}
		ModelAndView mv=new ModelAndView("forward:../../mainMenu/student/chooseCourse/"+studentId);
		return mv;
	}
	
	/**
	 * 学生退选
	 * @param studentId
	 * @param courseId
	 * @return
	 */
	@RequestMapping("unchoose/{studentId}/{courseId}")
	public ModelAndView unchooseCourse(@PathVariable("studentId")String studentId
			,@PathVariable("courseId")String courseId) {
		//先判断这门课是否已经选过
		if(curriculaVariableService.selectByPrimaryKey(studentId, courseId)) {
			curriculaVariableService.delete(studentId, Integer.parseInt(courseId));
		}else {
			
		}
		ModelAndView mv=new ModelAndView("forward:../../mainMenu/student/chooseCourse/"+studentId);
		return mv;
	}
	/**
	 * 根据学生选的问题前往提交界面
	 * @return
	 */
	@RequestMapping("/chooseQuestion/{studentId}/{questionId}")
	public ModelAndView chooseQuestion(@PathVariable("studentId")String studentId
			,@PathVariable("questionId")String questionId) {
		ModelAndView mv=new ModelAndView("submitHomeworkPage");
		mv.addObject("id", studentId);
		Question question = questionService.selectByQuestionId(Integer.parseInt(questionId));
		mv.addObject("questionId", question.getQuestionId());
		mv.addObject("questionContext", question.getQuestion());
		String context = homeworkService.selectHomeworkContext(questionId, studentId);
		mv.addObject("homeworkContext", context);
		mv.addObject("isLate", new Timestamp(System.currentTimeMillis()).after(question.getDeadline()));
		return mv;
	}
	/**
	 * 处理学生提交作业
	 * @param studentId
	 * @param questionId
	 * @param homeworkContext
	 * @return
	 */
	@RequestMapping("/homework/{questionId}/{studentId}")
	public ModelAndView submitHomework(@PathVariable("studentId")String studentId
			,@PathVariable("questionId")String questionId
			,@RequestParam(value="homeworkContext",defaultValue = "")String homeworkContext) {
		System.out.println(homeworkContext);
		ModelAndView mv=new ModelAndView("forward:../../mainMenu/student/chooseQuestion/"+studentId);
		mv.addObject("id", studentId);
		//如果之前提交过作业，那么只更新作业内容
		homeworkService.insertHomework(new Homework(studentId, Integer.parseInt(questionId), homeworkContext, 0,null));
		Question question = questionService.selectByQuestionId(Integer.parseInt(questionId));
		if(question.getDupCheck()==1)//教师开启查重，这将此作业进行查重
			duplicateChecking.check(Integer.parseInt(questionId));
		return mv;
	}
}
