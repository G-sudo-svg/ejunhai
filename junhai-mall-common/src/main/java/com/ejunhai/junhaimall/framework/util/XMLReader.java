package com.ejunhai.junhaimall.framework.util;

import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

/**
 *<P>
 * 2011-5-10上午09:44:43
 *<P>
 * @author liudi
 */

public class XMLReader {

	private static XMLReader xMLReader = null;
	Document doc = null;

	public static synchronized  XMLReader getInstance(){
		if(null == xMLReader){
			xMLReader = new XMLReader();
		}
		return xMLReader;
	}
	public void parse(String xml) {
		try {
			InputSource in = new InputSource(new StringReader(xml));  
			in.setEncoding("UTF-8"); 
			SAXReader reader = new SAXReader();
			doc = reader.read(in);
		} catch (DocumentException e) {
			throw new RuntimeException("解析xml文件异常", e);
		}
	}

	public String []  getElements(String text1,String text2,String text3) {
		Element root = doc.getRootElement();
		Element result = root.element(text1);
		Element txorder = root.element(text2);
		Element code = root.element(text3);
		return new String[]{result.getText(),txorder.getText(),code.getText()};
	}
	
	public String []  getElements(String text1,String text2,String text3,String text4) {
		Element root = doc.getRootElement();
		Element state = root.element(text1);
		Element msgId = root.element(text2);
		Element msgState = root.element(text3);
		Element reserve = root.element(text4);
		return new String[]{state.getText(),msgId.getText(),msgState.getText(),reserve.getText()};
	}

	public String [] getElements(String text1,String text2,String text3,String text4,String text5,String text6){
		Element root = doc.getRootElement();
		Element tradeStatus = root.element(text1);
		Element totalFee = root.element(text2);
		Element subject = root.element(text3);
		Element outTradeNo = root.element(text4);
		Element notifyRegTime = root.element(text5);
		Element tradeNo = root.element(text6);
		return new String[]{tradeStatus.getText(),totalFee.getText(),subject.getText(),outTradeNo.getText(),notifyRegTime.getText(),tradeNo.getText()};
	}
}

