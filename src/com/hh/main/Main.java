package com.hh.main;
import java.util.Iterator;
import java.util.Set;

import com.hh.collection.URLCollection;
import com.hh.collection.URLEntity;
import com.hh.sprider.WebSpider;
import com.hh.sprider.parser.impl.HtmlParserOf80s;
import com.hh.sprider.parser.impl.HtmlParserOfDYTT;
import com.hh.util.XMLFileUtil;

public class Main {
	public static void main(String[] args) {
		XMLFileUtil.readXml();
		
		new WebSpider(HtmlParserOf80s.class).start();
		new WebSpider(HtmlParserOfDYTT.class).crawling();
		
		XMLFileUtil.writeXml(URLCollection.getInstance());
		
		Set<URLEntity> set = URLCollection.getInstance().getVisiteds();
		Iterator<URLEntity> iter = set.iterator();
		while (iter.hasNext()) {
			System.out.println("1");
			System.out.println(iter.next());
		}
	}
}
