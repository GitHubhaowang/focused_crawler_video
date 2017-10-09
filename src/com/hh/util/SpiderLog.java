package com.hh.util;

import org.apache.log4j.Logger;

import com.hh.collection.URLEntity;

public class SpiderLog implements Observer {
	public static Logger logger = Logger.getLogger("log4j.properties");

	@Override
	public void observer(Object obj, Object msg) {
		URLEntity ue = (URLEntity) msg;
		if (ue.getDownURL() == null || ue.getDownURL() == "") {
			logger.info("未查找到 - "+ue.getName()+" - ");
		} else {
			logger.info("已查找到 - "+ue.getName()+" - | 下载地址 ："+ue.getDownURL());
		}
	}
}
