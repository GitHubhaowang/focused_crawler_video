package com.hh.spider.parser;

import java.util.Map;

import com.hh.collection.URLEntity;

/**
 * 页面解析类
 * @author hh
 * 2017-9-4 16:11:40
 */
public interface HtmlParser {
	/**
	 *  获取当前页面的Body
	 * @param map 条件
	 * @param charaset 字符集
	 * @param url 地址
	 * @return
	 */
	public String getBody(Map<String, Object> map, String charaset, String url);
	
	/**
	 * 解析当前页面
	 * @param url
	 * @return 
	 */
	public abstract String analysisUrl(String page, String name);
	
	/**
	 * 是否为下载页
	 * @return
	 */
	public boolean isDownPage(String page);
	
	/**
	 * 获取下载链接
	 * @param ue
	 * @return
	 */
	public URLEntity getDownUrl(URLEntity ue);
	
	/**
	 * 从下载页面上获取下载连接
	 * @param page
	 * @return
	 */
	public String getDownUrlFromPage(String page);
	
	/**
	 * 获取开始条件
	 * @param name
	 * @return
	 */
	public Map<String, Object> getStartMap(String name);
}
