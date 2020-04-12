package com.homeworksystem.run;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Run {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext ioc=new ClassPathXmlApplicationContext("ApplicationContext.xml");
		ioc.start();
		System.out.println("------------------启动成功-----------------------");
		System.in.read();
	}

}
