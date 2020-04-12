package com.homeworksystem.bean;

import java.io.Serializable;

public class Student implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String studentId;

    /**
     * MySQL password是关键字，所以数据库中密码用pass_word表示，对应驼峰命名是passWord
     */
    private String passWord;

    
    private String userName;

    
    private String gender;
    
    public Student() {
    	
    }
    
    public Student(String studentId, String passWord, String userName, String gender) {
		super();
		this.studentId = studentId;
		this.passWord = passWord;
		this.userName = userName;
		this.gender = gender;
	}

	public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", passWord=" + passWord + ", userName=" + userName + ", gender="
				+ gender + "]";
	}
    
    
}