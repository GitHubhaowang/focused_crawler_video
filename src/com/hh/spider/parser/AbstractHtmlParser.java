package com.hh.spider.parser;

import java.util.Map;

import com.hh.collection.URLEntity;
import com.hh.util.Observable;


public abstract class AbstractHtmlParser extends Observable implements HtmlParser {
	public URLEntity getURLEntity(URLEntity ue) {
		
		URLEntity returnUE = getDownUrl(ue);
		notifyObservers(returnUE);
		
		return returnUE;
	}
	
	protected abstract URLEntity getDownUrl(URLEntity ue);
	
	/**
	 *  获取当前页面的Body
	 * @param map 条件
	 * @param charaset 字符集
	 * @param url 地址
	 * @return
	 */
	protected abstract String getBody(Map<String, Object> map, String charaset, String url);
	
	/**
	 * 解析当前页面
	 * @param url
	 * @return 
	 */
	protected abstract String analysisUrl(String page, String name);
	
	/**
	 * 是否为下载页
	 * @return
	 */
	protected abstract boolean isDownPage(String page);

	/**
	 * 从下载页面上获取下载连接
	 * @param page
	 * @return
	 */
	protected abstract String getDownUrlFromPage(String page);
	
	/**
	 * 获取开始条件
	 * @param name
	 * @return
	 */
	protected abstract Map<String, Object> getStartMap(String name);
}
