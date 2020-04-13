# HomeworkSystem
基于SSM、dubbo、Tomcat制作的分布式作业管理系统。





							     目录

1、简介

2、运行环境

3、用到的框架

4、单机版环境搭建步骤

5、多机局域网环境搭建步骤

6、常见问题







							简介


这是个Maven项目
api是接口项目，提供一些基本类例如student类,Teacher类等等，以及接口，例如Service层的接口等等
service提供查询数据库服务，dupCheck和web可以使用其提供的服务，对数据库进行增删改查
dupCheck多线程查重，从service中获取作业信息后，利用算法对其进行计算，计算每两个作业之间的重复度
web建立Tomcat服务器之上，为用户提供可视化的页面，客户的请求由其转发给service和dupCheck
采用Alibaba的开源项目dubbo实现的分布式系统，其中注册中心是zookeeper
目录/数据库/Mysql.txt存放恢复数据库的sql语句

							开发环境

java10

Mysql8.0，其他版本应该也行

Tomcat8.5，安装教程:https://www.bilibili.com/video/BV1q4411u7mM?p=67

zookeeper3.4 安装教程：

eclipse

Maven

字符集UTF-8








							用到的框架


1、spring(对象管理、事务控制)
2、springmvc（拦截和处理用户请求）
3、mybatis （提供数据库增删改查）
4、dubbo （分布式框架）
5、Tomcat （服务器）
6、ehcache （Mybatis二级缓存）
7、zookeeper （dubbo用到的注册中心）
8、hibernate （数据校验）







							单机版环境搭建步骤


1、确保上面开发环境运行正常

2、恢复数据库

3、打开eclipse,点击按钮File，再点击open project from file system...，点击directory，找到源代码（四个都要导入）所在地址,然后点击finish

4、导入运行环境，右键项目，然后点击bulid path->configure build path..->Libraries，然后导入环境，点击Add Library，然后点击JRE system Library和Server runtime分别导入JRE，Tomcat

5、如果你的Java不是10，就要修改修改项目Java版本：https://jingyan.baidu.com/article/6c67b1d69a59a02787bb1e30.html?qq-pf-to=pcqq.c2c

6、启动zookeeper包下bin/ckserver.cmd

7、/homeworksystem-service/src/main/resources/dbconf.properties文件，修改数据库用户名和密码，如果你的数据库名不叫homeworksystemdatabase就要改jdbcURL数据库名称。

8、运行service，启动程序是/homeworksystem-service/src/main/java/com/homeworksystem/run/Run.java（确保service在dupCheck之前运行，确保dupCheck在web之前运行）

9、运行dupCheck，启动程序是/com.homeworksystem-dupCheck/src/main/java/com/homeworksystem/run/run.java

10、右键单击/homeworkSystem-web/src/main/webapp/index.jsp，有个Run as->Run on server运行，选择Tomcat

11、查看网页是否正常







							多台电脑局域网环境搭建步骤


1、确保每台电脑都有导入api项目，其他三个项目只需要选择一个

2、步骤基本与单机一致，不同：多机在启动程序前需要修改
	/com.homeworksystem-dupCheck/src/main/resources/provider.xml
	/homeworkSystem-web/src/main/resources/consumer.xml
	/homeworksystem-service/src/main/resources/ApplicationContext.xml
	中<dubbo:registry address="zookeeper://127.0.0.1:2181"></dubbo:registry>  IP地址改为zookeeper所在的IP地址

3、其他的部分一致








							常见问题


1、没找到server选项
https://blog.csdn.net/weixin_43422355/article/details/83309750#commentBox

2、Run on server时报错Project facet Java version 10 not supported
解决:https://jingyan.baidu.com/article/6c67b1d69a59a02787bb1e30.html，把jdk版本换成你的版本（如果你的jdk不是10的话）

3、webcontent文件夹中jsp全部报错
解决方法：出现上述问题的原因是你的Eclipse项目没有导入JSP运行所需要的Tomcat类库，主要是servlet-api.jar文件(或者servlet.jar），tomcat容器里面有这文件，在以下位置：%Tomcat_Home%/common/lib/servlet-aip.jar,要将其导入到项目的构建路径中(如果你用的是Eclipse).
	
4、runas选项中没有run server，
查看工具栏中窗口——首选项中有无server选项，若无，解决方法如下：
	https://blog.csdn.net/weixin_43422355/article/details/83309750
	https://blog.csdn.net/losedguest/article/details/80010990?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1





