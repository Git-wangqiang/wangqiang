package com.wangqiang.saxTest;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import com.wangqiang.handler.SAXParserHandler;

public class SAXTest {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		//获取SAXParserFactory对象
		SAXParserFactory factory = SAXParserFactory.newInstance();
		//通过factory获取SAXParser实例
		SAXParser  sax = factory.newSAXParser();
		
		SAXParserHandler handler = new SAXParserHandler();
		sax.parse("test.xml", handler);
	}
}
