package com.homeworksystem.dao;

import com.homeworksystem.bean.Question;
import java.util.List;

import org.apache.ibatis.annotations.Param;
/**
 * 持久层
 * 功能：对数据库进行增删改查，为service层提供方法操作数据库
 * 具体实现在conf/mapper/QuestionMapper.xml
 *
 */
public interface QuestionMapper {
    
    int deleteByPrimaryKey(Integer questionId);

   
    int insert(Question record);

    
    Question selectByPrimaryKey(Integer questionId);

    
    List<Question> selectAll();

    
    int updateByPrimaryKey(Question record);
    
    /**
          * 根据课程号查找出该课程的所有问题
     * @param courseId
     * @return
     */
    List<Question> selectByCourseId(@Param("courseId")Integer courseId);
    /**
     * 开启或者关闭查重
     * @Param questionId
     * @param dupCheck
     */
    void updateDupCheck(@Param("questionId")Integer questionId,@Param("dupCheck")Integer dupCheck);
}