package com.homeworksystem.bean;

import java.io.Serializable;

public class Teacher implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String teacherId;

    
    private String passWord;

   
    private String userName;

    
    private String gender;

    public Teacher() {
    	
    }
    
    public Teacher(String teacherId, String passWord, String userName, String gender) {
		super();
		this.teacherId = teacherId;
		this.passWord = passWord;
		this.userName = userName;
		this.gender = gender;
	}



	public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId == null ? null : teacherId.trim();
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
		return "Teacher [teacherId=" + teacherId + ", passWord=" + passWord + ", userName=" + userName + ", gender="
				+ gender + "]";
	}
    
}