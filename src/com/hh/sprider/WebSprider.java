package com.hh.sprider;

import com.hh.collection.URLCollection;
import com.hh.collection.URLEntity;
import com.hh.sprider.parser.HtmlParser;
import com.hh.sprider.parser.impl.HtmlParserOfDYTT;

/**
 * 爬虫核心类
 * @author hh
 * 2017-9-4 16:22:13
 */
public class WebSprider {
	private HtmlParser hp = new HtmlParserOfDYTT();
	
	/**
	 * 抓取过程
	 */
	public void crawling() {
//		Map<Object, Integer> map = new HashMap<Object, Integer>();	// 用来记录每个ue对象循环次数
		URLCollection urlC = URLCollection.getInstance();
		while (!urlC.getUnVisitedEmpty()) {
			URLEntity ue = urlC.removeUnVisited();
			ue = hp.getDownUrl(ue);
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
