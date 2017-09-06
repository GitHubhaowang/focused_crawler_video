package com.hh.collection;

import java.util.HashSet;
import java.util.Set;

import com.hh.entity.URLEntity;

/**
 * URLEntity存储容器
 * @author hh
 * 2017-9-4 15:06:02
 * 单例
 */
public class URLCollection {
	private static URLCollection instance = null;
	private Set<URLEntity> visitedUrl = new HashSet<URLEntity>();	// 已访问URL
	private UnvisitedQueue<URLEntity> unvisitedQueue = new UnvisitedQueue<URLEntity>();		// 未访问URL 
	
	public static URLCollection getInstance() {
		if (instance == null) {
			instance = new URLCollection();
		}
		return instance;
	}
	
	/**
	 * 添加到访问过的URL队列
	 * @param ue
	 */
	public void addVisited(URLEntity ue) {
		visitedUrl.add(ue);
	}
	
	/**
	 * 获取已经访问的URL数目
	 * @return
	 */
	public int getVisitedSize() {
		return visitedUrl.size();
	}
	
	/**
	 * 判断未访问的URL是否被访问过(未实现)
	 * @param ue
	 */
	public boolean isVisitedRepeat(URLEntity ue) {
		return false;
	}
	 
	/**
	 * 判断未访问的URL是否在队列中
	 * @param ue
	 * @return
	 */
	public boolean isUnVisitedRepeat(URLEntity ue) {
		return unvisitedQueue.contians(ue);
	}
	
	/**
	 * 添加未访问URL
	 * @param ue
	 */
	public void addUnVisited(URLEntity ue){
		if (!isVisitedRepeat(ue) && !isUnVisitedRepeat(ue)) {
			unvisitedQueue.enQueue(ue);
		}
	}
	
	/**
	 * 获取未访问的URL是否为空
	 * @return
	 */
	public boolean getUnVisitedEmpty() {
		return unvisitedQueue.empty();
	}
	 
	/**
	 * 获取未访问的URL队列并删除
	 * @return
	 */
	public URLEntity removeUnVisited() {
		return (URLEntity)unvisitedQueue.deQueue();
	}
	 
	/**
	 * 移除未访问过的URL队列
	 * @param ue
	 */
	public void removeUnVisited(URLEntity ue) {
		unvisitedQueue.remove(ue);
	}
	
	/**
	 * 获得访问过的URL队列
	 * @return
	 */
	public Set<URLEntity> getVisiteds() {
		return this.visitedUrl;
	}

	@Override
	public String toString() {
		return "URLCollection [unvisitedQueue=" + unvisitedQueue + "]";
	}
	
}
