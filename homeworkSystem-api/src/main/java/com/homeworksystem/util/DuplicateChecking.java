package com.homeworksystem.util;
/**
 * 
 * 查重服务
 */
public interface DuplicateChecking {
	/**
	 * 在后台自动运行查重程序
	 * @param questionId
	 */
	public void check(Integer questionId) ;
	
}
