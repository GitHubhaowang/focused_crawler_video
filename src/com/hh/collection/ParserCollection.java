package com.hh.collection;

import java.util.ArrayList;
import java.util.List;

import com.hh.spider.parser.HtmlParser;

/**
 * 页面解析类对象集合
 * @author hh
 * 2017-10-13
 */
public class ParserCollection {
	private static ParserCollection instance = null;	// 单例模式
	
	private List<HtmlParser> parserList = new ArrayList<HtmlParser>(); 
	
	private ParserCollection() {
		
	}
	
	public static ParserCollection getInstance() {
		if (instance == null) {
			instance = new ParserCollection();
		}
		return instance;
	}
	
	/**
	 * 添加元素
	 * @param htmlParser
	 */
	public void addParser(HtmlParser htmlParser) {
		parserList.add(htmlParser);
	}
	
	/**
	 * 获取元素
	 * @param index
	 * @return
	 */
	public HtmlParser getParser(int index) {
		return parserList.get(index);
	}
	
	/**
	 * 获取大小
	 * @return
	 */
	public int getSize() {
		return parserList.size();
	}
}
