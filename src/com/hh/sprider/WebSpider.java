package com.hh.sprider;

import java.util.HashMap;
import java.util.Map;

import com.hh.collection.URLCollection;
import com.hh.collection.URLEntity;
import com.hh.sprider.parser.HtmlParser;
import com.hh.sprider.parser.impl.HtmlParserOfDYTT;

/**
 * 爬虫核心类
 * @author hh
 * 2017-9-4 16:22:13
 */
public class WebSpider extends Thread{
	private HtmlParser hp;
	Map<Object, Integer> map = new HashMap<Object, Integer>();	// 用来记录每个ue对象循环次数
	
	public WebSpider(Class<? extends HtmlParser> objClass) {
		try {
			hp = objClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 抓取过程
	 */
	public void crawling() {
		URLCollection urlC = URLCollection.getInstance();
		while (!urlC.getUnVisitedEmpty()) {
			URLEntity ue = urlC.removeUnVisited();
			ue = hp.getDownUrl(ue);
			if (ue.getDownURL() == null || "".equals(ue.getDownURL())) {
				// 用来防止一直循环下去
				Integer count = map.get(ue);
				if (count == null) {
					map.put(ue, 1);
				} else if (count < 5) {
					map.put(ue, count+1);
				} else {
					continue ;
				}

				urlC.addUnVisited(ue);
			} else {
				urlC.addVisited(ue);
			}
		}		
	}


	@Override
	public void run() {
		crawling();
	}
}
