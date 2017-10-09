package com.hh.spider.parser;

import com.hh.collection.URLEntity;

/**
 * 页面解析类
 * @author hh
 * 2017-9-4 16:11:40
 */
public interface HtmlParser {	
	/**
	 * 获取下载链接
	 * @param ue
	 * @return
	 */
	public URLEntity getURLEntity(URLEntity ue);
	
}
