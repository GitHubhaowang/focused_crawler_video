package com.hh.config;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hh.collection.ParserCollection;
import com.hh.spider.parser.AbstractHtmlParser;
import com.hh.util.Observer;

/**
 * 解析init.xml的解析类
 * @author hh
 *
 */
public class InitXml {
	private static String xmlPath = "D:\\workspace_Eclipse\\自己动手写网络爬虫\\focused_crawler_video\\init.xml";
	private static SAXReader reader;
	private static Document doc;
	
	public InitXml() {
		try {
            File readFile = new File(xmlPath);
            if (!readFile.exists()) {
            	throw new RuntimeException("文件地址错误"+reader);
            }
            reader=new SAXReader();
            //读取xml文件到Document中
            doc=reader.read(readFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public void getData() {
		// 获取xml文件的根节点
        Element rootElement=doc.getRootElement();
        // 定义一个Element用于遍历
        Element fooElement;
        
        // 获取spider
        Map<String, AbstractHtmlParser> spiderMap = new HashMap<String, AbstractHtmlParser>();	// 用来保存spider
        // 遍历所有名叫“spider”的节点
        for(Iterator i = rootElement.elementIterator("spider"); i.hasNext();){
            fooElement = (Element)i.next();
            spiderMap.put(fooElement.elementText("name"), (AbstractHtmlParser) getNewInstance(fooElement.elementText("class")));
        }
        
        // 获取Observer
        Map<String, Observer> observerMap = new HashMap<String, Observer>();	// 用来保存observer
        // 遍历所有名叫“observer”的节点
        for(Iterator i = rootElement.elementIterator("observer"); i.hasNext();){
            fooElement = (Element)i.next();
            observerMap.put(fooElement.elementText("name"), (Observer) getNewInstance(fooElement.elementText("class")));
        }
        
        // 组装spider
        AbstractHtmlParser spider = null;
        Observer observer = null;
        int load_up = 0;
        // 遍历所有名叫“spider-observer”的节点
        for(Iterator i = rootElement.elementIterator("spider-observer"); i.hasNext();){
            fooElement = (Element)i.next();
            spider = spiderMap.get(fooElement.elementText("ref-spider"));
            observer = observerMap.get(fooElement.elementText("ref-observer"));
            if (spider != null && observer != null) {
            	if (fooElement.elementText("load-up") == null) {
            		load_up = 3;
            	} else {
            		load_up = Integer.parseInt(fooElement.elementText("load-up"));
            	}
            } else {
            	throw new RuntimeException("init.xml中<spider-observer>中之前没有定义");
            } 
            // 添加观察者
            spider.addObservable(load_up, observer);
        }
        
        // 将spider放入ParserCollection中
        ParserCollection.getInstance().addParser(spider);
	}
	
	/**
	 * 通过反射获取对象
	 * @param str
	 * @return
	 */
	private Object getNewInstance(String str) {
		try {
			return Class.forName(str).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
