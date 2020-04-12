package com.homeworksystem.bean;

import java.io.Serializable;

public class Course implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private Integer courseId;


    private String teacherId;


    private String courseName;

    
    private String teacherName;
    
    
    private Integer num;
    
    
    public Course() {
    	
    }
    
    /**
     * 
     * @param courseId
     * @param teacherId
     * @param courseName
     * @param teacherName
     */
    public Course(Integer courseId, String teacherId, String courseName, String teacherName) {
		super();
		this.courseId = courseId;
		this.teacherId = teacherId;
		this.courseName = courseName;
		this.teacherName = teacherName;
	}


	public Integer getCourseId() {
        return courseId;
    }


    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }


    public String getTeacherId() {
        return teacherId;
    }

 
    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    
    
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", teacherId=" + teacherId + ", courseName=" + courseName
				+ ", teacherName=" + teacherName + "]";
	}
    
}