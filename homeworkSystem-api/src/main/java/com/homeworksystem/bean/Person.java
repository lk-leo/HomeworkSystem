package com.homeworksystem.bean;


import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
/**
 * 
 * 兼容student类和teacher类
 * Person可以转换成student，也可以转换成teacher
 *
 */
public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 区分学生和老师
	 */
	private String type;
	
	@NotNull
	private String id;

    /**
     * MySQL password是关键字，所以数据库中密码用pass_word表示，对应驼峰命名是passWord
     */
	@Length(min=6,max=18)
    private String passWord;

    private String userName;

    private String gender;


    public Person() {
    	
    }
    
	public Person(String type, String id, @Length(min = 6, max = 18) String passWord,
			@NotNull String userName, @NotNull String gender) {
		super();
		this.type = type;
		this.id = id;
		this.passWord = passWord;
		this.userName = userName;
		this.gender = gender;
	}
	
	public Person(Student student) {
		this.type="student";
		this.id=student.getStudentId();
		this.passWord=student.getPassWord();
		this.gender=student.getGender();
		this.userName=student.getUserName();
	}

	public Person(Teacher teacher) {
		this.type="teacher";
		this.id=teacher.getTeacherId();
		this.passWord=teacher.getPassWord();
		this.gender=teacher.getGender();
		this.userName=teacher.getUserName();
	}
	
	@Override
	public String toString() {
		return "Person [type=" + type + ", id=" + id + ", passWord=" + passWord + ", userName=" + userName + ", gender="
				+ gender + "]";
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPassWord() {
		return passWord;
	}


	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}
    /**
     * 将person转换成student对象
     * @return
     */
    public Student convertToStudent() {
    	return new Student(id, passWord, userName, gender);
    }
    /**
     * 将person转换成Teacher对象
     * @return
     */
    public Teacher convertToTeacher() {
    	return new Teacher(id, passWord, userName, gender);
    }
    
}
