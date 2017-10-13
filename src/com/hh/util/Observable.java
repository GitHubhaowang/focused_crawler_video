package com.hh.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 观察者主题角色
 * @author hh
 * 2017-10-9 11:30:02
 */
public class Observable implements Product {
	private Map<Integer, List<Observer>> map = new HashMap<Integer, List<Observer>>();
	
	/**
	 * 添加观察者
	 * @param observer
	 */
	public void addObservable(Observer observer) {
		addObservable(3, observer);
	}
	
	/**
	 * 添加带权值观察者
	 * @param n	权值
	 * @param observer
	 */
	public void addObservable(int n, Observer observer) {
		if (map != null && map.containsKey(n)) {
			map.get(n).add(observer);
		} else {
			List<Observer> list = new ArrayList<Observer>();
			list.add(observer);
			map.put(n, list);
		}
	}
	
	/**
	 * 删除所有观察者
	 */
	public void deleteObservables() {
		map.clear();
	}
	
	/**
	 * 通知所有观察者
	 */
	public void notifyObservers() {
		notifyObservers(null);
	}
	
	/**
	 * 通知所有观察者
	 * @param msg 信息对象
	 */
	public void notifyObservers(Object msg) {
		for (int i=0; i<6; i++) {
			if (map.containsKey(i)) {
				List<Observer> list = map.get(i);
				for (int j=0; j<list.size(); j++) {
					list.get(j).observer(this, msg);
				}
			}
		}
	}
}
