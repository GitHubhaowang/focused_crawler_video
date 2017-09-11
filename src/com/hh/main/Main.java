package com.hh.main;
import java.util.Iterator;
import java.util.Set;

import com.hh.collection.URLCollection;
import com.hh.collection.URLEntity;
import com.hh.sprider.WebSprider;
import com.hh.util.XMLFileUtil;

public class Main {
	public static void main(String[] args) {
		XMLFileUtil.readXml();
		
		WebSprider ws = new WebSprider();
		ws.crawling();
		
		XMLFileUtil.writeXml(URLCollection.getInstance());
		
		Set<URLEntity> set = URLCollection.getInstance().getVisiteds();
		Iterator<URLEntity> iter = set.iterator();
		while (iter.hasNext()) {
			System.out.println("1");
			System.out.println(iter.next());
		}
	}
}
