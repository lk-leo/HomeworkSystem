package com.homeworksystem.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageInfo;
import com.homeworksystem.bean.Course;
import com.homeworksystem.bean.Homework;
import com.homeworksystem.bean.Question;
import com.homeworksystem.bean.Student;
import com.homeworksystem.service.CourseService;
import com.homeworksystem.service.CurriculaVariableService;
import com.homeworksystem.service.HomeworkService;
import com.homeworksystem.service.QuestionService;
import com.homeworksystem.service.TeacherService;
import com.homeworksystem.util.DuplicateChecking;

@Controller
public class TeacherController {
	
	@Autowired
	CourseService courseService;
	@Autowired
	CurriculaVariableService curriculaVariableService;
	@Autowired
	QuestionService questionService;
	@Autowired
	HomeworkService homeworkService;
	@Autowired
	TeacherService teacherService;
	@Autowired
	DuplicateChecking duplicateChecking;
	/**
	 * 前往教师主页面
	 * @param teacherId
	 * @param page
	 * @return
	 */
	@RequestMapping("/mainMenu/teacher/{page}/{teacherId}")
	public ModelAndView toMainMenu(@PathVariable("teacherId")String teacherId,
			@PathVariable("page")String page) {
		ModelAndView mv=new ModelAndView("teacherMainMenu");
		mv.addObject("id", teacherId);
		if(page.equals("myCourse")) {
			mv.addObject("page", "myCourse");
			List<Course> courses = courseService.selectByTeacherId(teacherId);
			mv.addObject("allTheCourses", courses);
		}else if(page.equals("createCourse")) {
			mv.addObject("page", "createCourse");
			List<Course> courses = courseService.selectByTeacherId(teacherId);
			mv.addObject("allTheCourses", courses);
		}else if(page.equals("assignQuestion")) {
			mv.addObject("page", "assignQuestion");
			List<Course> courses = courseService.selectByTeacherId(teacherId);
			mv.addObject("allTheCourses", courses);
		}else if(page.equals("correctHomework")) {
			mv.addObject("page", "correctHomework");
			List<Course> courses = courseService.selectByTeacherId(teacherId);
			mv.addObject("allTheCourses", courses);
		}
		return mv;
	}
	/**
	 * 教师提交新开课程
	 * @param teacherId
	 * @param courseName
	 * @return
	 */
	@RequestMapping("createCourse/{teacherId}")
	public ModelAndView createCourse(@PathVariable("teacherId")String teacherId
			,@RequestParam("courseName")String courseName) {
		ModelAndView mv=new ModelAndView("forward:../mainMenu/teacher/createCourse/"+teacherId);
		courseService.insert(new Course(null,teacherId,courseName,null));
		return mv;
	}
	/**
	  * 关闭课程
	 * @param teacherId
	 * @param courseId
	 * @return
	 */
	@RequestMapping("closeCourse/{teacherId}/{courseId}")
	public ModelAndView closeCourse(@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId) {
		ModelAndView mv=new ModelAndView("forward:../../mainMenu/teacher/createCourse/"+teacherId);
		courseService.delete(courseId);
		return mv;
	}
	/**
	  * 前往布置作业界面
	 * @param teacherId
	 * @param courseId
	 * @return
	 */
	@RequestMapping("toAssignQuestionPage/{teacherId}/{courseId}")
	public ModelAndView toAssignQuestionPage(@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId) {
		ModelAndView mv=new ModelAndView("assignQuestion");
		List<Student> students= curriculaVariableService.selectByCourseId(courseId);
		mv.addObject("allTheStudents", students);
		mv.addObject("teacherId", teacherId);
		mv.addObject("courseId", courseId);
		List<Question> questions = questionService.selectByCourseId(Integer.parseInt(courseId));
		mv.addObject("allTheQuestions", questions);
		questions.forEach(q -> q.setNum(homeworkService.selectFinishedHomeworkNum(q.getQuestionId())));
		//获取当前时间
		SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间 
        sdf.applyPattern("yyyy-MM-dd");
		Date current=new Date();
		String time=sdf.format(current);
		String[] part=time.split("-");
		int year=Integer.parseInt(part[0]);
		int month=Integer.parseInt(part[1]);
		int day=Integer.parseInt(part[2]);
		List<Integer> years=Arrays.asList(year,year+1,year+2);
		List<Integer> months=new ArrayList<Integer>();
		for(int i=0;i<12;i++)
			months.add(month>12?(month++)-12:month++);
		List<Integer> days=new ArrayList<Integer>();
		for(int i=0;i<31;i++)
			days.add(day>31?(day++)-31:day++);
		List<Integer> hours=new ArrayList<Integer>();
		for(int i=0;i<=23;i++)
			hours.add(i);
		mv.addObject("year", years);
		mv.addObject("month", months);
		mv.addObject("day", days);
		mv.addObject("hour", hours);
		return mv;
	}
	/**
	 * 布置作业
	 * @param teacherId
	 * @param courseId
	 * @param questionContext
	 * @return
	 */
	@RequestMapping("newQuestion/{teacherId}/{courseId}")
	public ModelAndView newQuestion(@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId,
			@RequestParam("questionContext")String questionContext,
			@RequestParam("year")String year,
			@RequestParam("month")String month,
			@RequestParam("day")String day,
			@RequestParam("hour")String hour) {
		ModelAndView mv=new ModelAndView("forward:../../toAssignQuestionPage/"+teacherId+"/"+courseId);
		String deadline=year+"-"+month+"-"+day+" "+hour+":00:00";
		Question question=new Question(null, Integer.parseInt(courseId), questionContext, null, null, null,Timestamp.valueOf(deadline));
		questionService.insertQuestion(question);
		return mv;
	}
	/**
	 * 取消某次作业
	 * @param teacherId
	 * @param questionId
	 * @return
	 */
	@RequestMapping("deleteQuestion/{teacherId}/{courseId}/{questionId}")
	public ModelAndView closeQuestion(@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId,
			@PathVariable("questionId")String questionId) {
		//重新跳转到布置作业界面
		ModelAndView mv=new ModelAndView("forward:../../../toAssignQuestionPage/"+teacherId+"/"+courseId);
		questionService.deleteQuestion(courseId,questionId);
		return mv;
	}
	/**
	 * 开启或者关闭查重
	 * @param operator
	 * @param teacherId
	 * @param courseId
	 * @param questionId
	 * @return
	 */
	@RequestMapping("duplicateChecking/{operator}/{teacherId}/{courseId}/{questionId}")
	public ModelAndView startDuplicateChecking(
			@PathVariable("operator")String operator,
			@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId,
			@PathVariable("questionId")String questionId) {
		ModelAndView mv=new ModelAndView("forward:../../../../toAssignQuestionPage/"+teacherId+"/"+courseId);
		if(operator.equals("true")) {
			duplicateChecking.check(Integer.parseInt(questionId));
			questionService.startDuplicateChecking(questionId);
		}else {
			questionService.closeDuplicateChecking(questionId);
		}
		return mv;
	}
	/**
	 * 批改作业
	 * @param teacherId
	 * @param courseId
	 * @param questionId
	 * @param pageNum
	 * @return
	 */
	@RequestMapping("/toCorrectHomeworkPage/{teacherId}/{courseId}/{questionId}")
	public ModelAndView toCorrectHomeworkPage(@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId,
			@PathVariable("questionId")String questionId,
			@RequestParam(value="pageNum",defaultValue="1")Integer pageNum) {
		ModelAndView mv=new ModelAndView("correctHomework");
		PageInfo<Homework> info= homeworkService.selectByQuestionId(questionId, pageNum, 1, 5);
		mv.addObject("info", info);
		List<Homework> homeworks2 = homeworkService.selectByQuestionId(questionId);
		mv.addObject("homeworks", homeworks2);
		mv.addObject("teacherId", teacherId);
		mv.addObject("courseId", courseId);
		mv.addObject("questionId", questionId);
		mv.addObject("pageNum", pageNum);
		return mv;
	}
	/**
	  * 提交分数
	 * @param teacherId
	 * @param courseId
	 * @param questionId
	 * @param pageNum
	 * @param score
	 * @return
	 */
	@RequestMapping("/score/{teacherId}/{courseId}/{questionId}/{studentId}")
	public ModelAndView setScore(@PathVariable("teacherId")String teacherId,
			@PathVariable("courseId")String courseId,
			@PathVariable("questionId")String questionId,
			@PathVariable("studentId")String studentId,
			@RequestParam(value="pageNum",defaultValue = "0")String pageNum,
			@RequestParam(value="score",defaultValue = "0")String score) {
		ModelAndView mv=new ModelAndView("forward:../../../../toCorrectHomeworkPage/"+teacherId+"/"+courseId+"/"+questionId+"?"+pageNum);
		homeworkService.updateScore(questionId, studentId, score);
		return mv;
	}
	
}
