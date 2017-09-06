package com.hh.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.hh.collection.URLCollection;
import com.hh.entity.URLEntity;

public class ReadXML {
	private final static String basePath = "D:\\workspace_Eclipse\\自己动手写网络爬虫\\focused_crawler_video_1.0\\src\\";
	private static String inPath = "video.xml";
	private static String path = basePath + inPath;
	
	private static SAXReader reader;
	private static Document doc;
	
	private static URLCollection urlC = URLCollection.getInstance();
	
	static {
        try {
            File file = new File(path);
            reader=new SAXReader();
            //读取xml文件到Document中
            doc=reader.read(file);
            if (doc == null) {
                throw new RuntimeException("文件地址错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static List<URLEntity> init() {
        // 获取xml文件的根节点
        Element rootElement=doc.getRootElement();
        // 定义一个Element用于遍历
        Element fooElement;
        // 定义一个Attribute获取属性值
        Attribute attribute;

        List<URLEntity> list = new ArrayList<URLEntity>();
        // 遍历所有名叫“video”的节点
        for(Iterator i = rootElement.elementIterator("video"); i.hasNext();){
            fooElement=(Element)i.next();
            attribute=fooElement.attribute("name");
            
            URLEntity ue = new URLEntity();
            ue.setName(attribute.getText());
            System.out.println(ue);
            
            urlC.addUnVisited(ue);
        }
        return list;
    }
}
