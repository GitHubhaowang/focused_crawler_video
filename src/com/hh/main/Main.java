package com.hh.main;
import java.util.Iterator;
import java.util.Set;

import com.hh.collection.URLCollection;
import com.hh.collection.URLEntity;
import com.hh.config.WpiderConfig;

public class Main {
	public static void main(String[] args) {
		new WpiderConfig().run();
		
		Set<URLEntity> set = URLCollection.getInstance().getVisiteds();
		Iterator<URLEntity> iter = set.iterator();
		while (iter.hasNext()) {
			System.out.println("1");
			System.out.println(iter.next());
		}
	}
}
