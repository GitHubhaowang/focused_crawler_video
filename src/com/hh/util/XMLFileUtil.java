package com.hh.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.hh.collection.URLCollection;
import com.hh.collection.URLEntity;

/**
 * xml文件的读取和写入
 * @author hh
 *
 */
public class XMLFileUtil {
	private final static String basePath = "D:\\workspace_Eclipse\\自己动手写网络爬虫\\focused_crawler_video\\";
	private static String inPath = "src\\video.xml";
	private static String outPath = "data\\data.xml";
	private static String readPath = basePath + inPath;
	private static String writePath = basePath + outPath;

	private static SAXReader reader;
	private static XMLWriter writer;
	private static Document readDoc;
	private static Document writeDoc;
	
	// 读取文件的基本设置
	static {
        try {
            File readFile = new File(readPath);
            if (!readFile.exists()) {
            	throw new RuntimeException("文件地址错误"+reader);
            }
            reader=new SAXReader();
            //读取xml文件到Document中
            readDoc=reader.read(readFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	// 写入文件的基本设置
	static {
        try {
        	// 创建文件输出的时候，自动缩进的格式        
            OutputFormat format = OutputFormat.createPrettyPrint();  
            format.setEncoding("UTF-8");
            
        	FileWriter file = new FileWriter(writePath);
        	
            writer = new XMLWriter(file, format);
            // 设置document
            writeDoc = DocumentHelper.createDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	/**
	 * 读取xml
	 * @return
	 */
	public static List<URLEntity> readXml() {
        // 获取xml文件的根节点
        Element rootElement=readDoc.getRootElement();
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
            
            URLCollection.getInstance().addUnVisited(ue);
        }
        return list;
    }
	
	/**
	 * 写入xml
	 * @param urlC
	 */
	public static void writeXml(URLCollection urlC) {
		if (urlC.getVisitedSize() < 1) {
			return ;			
		} else {
			//创建xml文件的根节点
			Element root = writeDoc.addElement("root");
	        
	        Element ageElm;
	        
	        Iterator<URLEntity> iter;
	        for (iter = urlC.getVisiteds().iterator(); iter.hasNext();) {
	        	URLEntity ue = iter.next();
	        	// 设置子节点
	        	ageElm = root.addElement("video");
		        // 设置属性
		        ageElm.addAttribute("name", ue.getName());
		        ageElm.addElement("downURL").setText(ue.getDownURL());
		        ageElm.addElement("downPageURL").setText(ue.getDownPageURL());
	        }
	        write();
	        writerClose();
		}
	}
	
	/**
	 * 写入操作
	 */
	public static void write() {
		try {
			writer.write(writeDoc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭writer流
	 */
	public static void writerClose() {
		try {
			if(writer != null) {
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
