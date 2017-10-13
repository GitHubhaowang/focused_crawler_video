package com.hh.config;

import com.hh.collection.ParserCollection;
import com.hh.collection.URLCollection;
import com.hh.spider.WebSpider;
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
		new InitXml().getData();
		
		for (int i=0; i<ParserCollection.getInstance().getSize(); i++) {
			if (i == ParserCollection.getInstance().getSize()-1) {
				new WebSpider(ParserCollection.getInstance().getParser(i)).run();
			} else {
				new WebSpider(ParserCollection.getInstance().getParser(i)).start();
			}
		}
		
		destory();
	}
}
