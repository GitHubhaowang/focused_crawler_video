package com.hh.util;

/**
 * 观察者角色接口
 * @author hh
 * 2017-10-9 11:30:37
 */
public interface Observer {
	/**
	 * 观察方法
	 * @param obj 主题对象
	 * @param msg 信息对象（没有为null）
	 */
	public void observer(Object obj, Object msg);
}
