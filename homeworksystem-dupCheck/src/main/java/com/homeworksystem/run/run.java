package com.homeworksystem.run;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.homeworksystem.utilImp.DuplicateCheckingImp;

public class run {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext provider=new ClassPathXmlApplicationContext("provider.xml");
		provider.start();
		DuplicateCheckingImp bean = provider.getBean(DuplicateCheckingImp.class);
		Thread thread = new Thread(bean);
		thread.setDaemon(true);
		thread.start();
		System.out.println("------------------查重程序启动成功---------------");
		System.in.read();
	}

}
