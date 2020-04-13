package com.homeworksystem.utilImp;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.homeworksystem.bean.Homework;
import com.homeworksystem.service.HomeworkService;
import com.homeworksystem.util.DuplicateChecking;
/**
 * 
 *	查重服务
 *	既是生产者也是消费者
 *	1、接受web的服务请求，得到questionId，放入待执行队列
 *	2、从待执行队列中拿出questionId，然后利用service提供的服务从数据库获取这个问题下所有的作业
 *	3、运行程序，算出重复度记录在临时队列中
 *	4、将临时队列中的重复度利用service写回数据库
 */
@Service
public class DuplicateCheckingImp implements DuplicateChecking,Runnable{
	/**
	 * 待处理队列，存放questionId
	 */
	private ConcurrentLinkedQueue<Integer> waitList;
	/**
	 * 线程池，用于执行查重任务
	 */
	private ExecutorService threadPool;
	/**
	 * 获取Service服务
	 */
	@Autowired
	private HomeworkService homeworkService;
	/**
	 * 初始化
	 */
	public DuplicateCheckingImp() {
		waitList=new ConcurrentLinkedQueue<Integer>();
		threadPool=Executors.newFixedThreadPool(3);
	}
	public void check(Integer id) {
		waitList.add(id);
		System.out.println("问题号："+id+"进入等待");
	}
	/**
	 * 不断从待执行队列中拿出问题号，交由线程池执行
	 */
	public void run() {
		while(true) {
			if(waitList.isEmpty()) {
				Thread.yield();
				continue;
			}
			System.out.println("拿出问题号");
			//从待执行队列中拿出问题号
			Integer questionId = waitList.poll();
			//从数据库中拿出这个问题下所有作业
			List<Homework> homeworks = homeworkService.selectByQuestionId(""+questionId);
			//交由线程池执行
			threadPool.execute(new Task(homeworks));
		}
	}
	/**
	 * 
	 *	任务
	 *	传入作业，结果写回数据库
	 */
	class Task implements Runnable{
		List<Homework> homeworks;
		public Task(List<Homework> homeworks) {
			this.homeworks=homeworks;
		}
		
		public void run() {
			if(homeworks.isEmpty()) {
				System.out.println("此问题还没有作业提交");
				return;
			}
			for(Homework h1:homeworks)
				for(Homework h2:homeworks)
					if(h1.getStudentId().equals(h2.getStudentId()))//跳过同一个学生的作业
						continue;
					else
						check(h1,h2);
			//放回数据库
			homeworkService.updateRepeatability(homeworks);
			System.out.println("成功写回数据库");
		}
		/**
		 * 查重
		 */
		private void check(Homework h1,Homework h2) {
			h1.setRepeatability(-1);
			h2.setRepeatability(-1);
		}
	}

}
