package com.hh.collection;

import java.util.concurrent.ConcurrentLinkedQueue;

public class UnvisitedQueue<E> {
	private ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<E>();
	
	/**
	 * 入队列
	 * @param t
	 */
	public void enQueue(E t) {
		queue.add(t);
	}
	
	/**
	 * 出队列
	 * @return
	 */
	public Object getQueue() {
		return queue.peek();
	}
	
	/**
	 * 出队列并删除
	 * @return
	 */
	public Object deQueue() {
		return queue.poll();
	}
	
	/**
	 * 移除元素
	 * @param e
	 * @return
	 */
	public boolean remove(E e) {
		return queue.remove(e);
	}
	/**
	 * 判断队列是否为空
	 * @return
	 */
	public boolean isQueueEmpty() {
		return queue.isEmpty();
	}
	
	/**
	 * 判断队列是否包含t
	 * @param t
	 * @return
	 */
	public boolean contians(Object t) {
		return queue.contains(t);
	}
	
	public boolean empty() {
		return queue.isEmpty();
	}
}
