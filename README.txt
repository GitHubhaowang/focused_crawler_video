目标：
	- 具有写日志、自动下载、可扩展的多线程的电影链接提取
	
使用说明：
	- 在 video.xml 里根据格式写下电影名称
	- 运行com.hh.main.Main的main方法
		
1.0 电影天堂（http://www.dytt8.net/）的单线程链接提取
	- 问题：
		- （√）待访问队列写成了栈
		- 没有规避错误（404、500）
		- 连接超时错误
		- 找不到资源后一直循环
		- （√）筛选资源不够精准(初步可以，以后优化)
		- Dom4j的地址不应该用字符串手动输入
		- 将所有的地址全部封装到xml文件中
		- 迅雷SDK不能从ftp上下载文件了
	- 待开发：
		- （√）DOM4J利用xml文件（基本实现）
		- （×）调用下载器下载
		- 多线程、多个网站的爬虫
		- 集合Maven
		- （正在解决）自动写日志
	- 正在解决：
		- 写 80s 网站的电解提取
	
		