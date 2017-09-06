package com.hh.sprider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.hh.collection.URLCollection;
import com.hh.entity.URLEntity;
import com.hh.parser.HtmlParser;
import com.hh.parser.impl.HtmlParserOfDYTT;

/**
 * 爬虫核心类
 * @author hh
 * 2017-9-4 16:22:13
 */
public class WebSprider {
	private URLCollection urlC = URLCollection.getInstance();	// URLEntity存储容器
	private HtmlParser hp = new HtmlParserOfDYTT();
	
	public URLCollection getUrlC() {
		return urlC;
	}
	
	/**
	 * 初始化URLEntity存储容器（未实现）
	 */
	private void initCrawlerWithSeeds() {
		
	}
	
	/**
	 * 抓取过程
	 */
	public void crawling() {
		initCrawlerWithSeeds();
//		Map<Object, Integer> map = new HashMap<Object, Integer>();	// 用来记录每个ue对象循环次数
		
		while (!urlC.getUnVisitedEmpty()) {
			URLEntity ue = urlC.removeUnVisited();
			ue = hp.getDownUrl(ue, "http://s.dydytt.net/plus/search.php", 0);
			if (ue.getDownURL() == null || "".equals(ue.getDownURL())) {
				/*// 用来防止一直循环下去
				Integer count = map.get(ue);
				if (count == null) {
					urlC.addUnVisited(ue);
					map.put(ue, 1);
				} else if (count < 5) {
					urlC.addUnVisited(ue);
					map.put(ue, count+1);
				}*/
			} else {
				urlC.addVisited(ue);
			}
		}
	}
}
