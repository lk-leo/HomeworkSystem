package com.homeworksystem.bean;

import java.io.Serializable;

public class Homework implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String studentId;


    private Integer questionId;

 
    private String homework;

    
    private Integer score;
    
    
    private String userName;
    
    /**
     * 查重
     */
    private Integer repeatability;
    
    public Homework(){
    	
    }

	public Homework(String studentId, Integer questionId, String homework, Integer score, String studentName) {
		super();
		this.studentId = studentId;
		this.questionId = questionId;
		this.homework = homework;
		this.score = score;
		this.userName = studentName;
	}

	public Homework(String studentId, Integer questionId, String homework, Integer score, 
			String studentName,Integer repeatability) {
		super();
		this.studentId = studentId;
		this.questionId = questionId;
		this.homework = homework;
		this.score = score;
		this.userName = studentName;
		this.repeatability=repeatability;
	}


	public String getStudentId() {
        return studentId;
    }


    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }


    public Integer getQuestionId() {
        return questionId;
    }


    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }


    public String getHomework() {
        return homework;
    }


    public void setHomework(String homework) {
        this.homework = homework == null ? null : homework.trim();
    }


	public Integer getScore() {
		return score;
	}


	public void setScore(Integer score) {
		this.score = score;
	}




	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}


	

	public Integer getRepeatability() {
		return repeatability;
	}

	public void setRepeatability(Integer repeatability) {
		this.repeatability = repeatability;
	}

	@Override
	public String toString() {
		return "Homework [studentId=" + studentId + ", questionId=" + questionId + ", homework=" + homework + ", score="
				+ score + ", userName=" + userName + ", repeatability=" + repeatability + "]";
	}

	
}