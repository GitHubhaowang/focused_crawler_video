package com.hh.config;

import com.hh.collection.URLCollection;
import com.hh.spider.WebSpider;
import com.hh.spider.parser.impl.HtmlParserOf80s;
import com.hh.spider.parser.impl.HtmlParserOfDYTT;
import com.hh.util.XMLFileUtil;

public class WpiderConfig {
	public void init() {
		XMLFileUtil.readXml();
	}
	
	public void destory() {
		XMLFileUtil.writeXml(URLCollection.getInstance());
	}
	
	public void run() {
		init();
		new WebSpider(HtmlParserOf80s.class).start();
		new WebSpider(HtmlParserOfDYTT.class).crawling();
		destory();
	}
}
