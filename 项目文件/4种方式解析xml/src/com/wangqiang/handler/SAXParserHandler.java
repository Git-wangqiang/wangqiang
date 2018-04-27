package com.wangqiang.handler;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler extends DefaultHandler {
	//用来遍历开始标签
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		super.startElement(uri, localName, qName, attributes);
		//获取节点
		if(qName.equals("parent")){
			//获取属性值,使用与已知元素属性名称
			String value = attributes.getValue("name");
			System.out.println(value);
			
			int sum = attributes.getLength();
			for(int i=0;i<sum;i++){
				System.out.println(attributes.getQName(i));
				System.out.println(attributes.getValue(i));
			}
		}else{
			System.out.println("节点名是:"+qName);
		}
	}
	
	//用来遍历结束标签
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		super.endElement(uri, localName, qName);
		if(qName.equals("parent")){
			System.out.println("parent属性已经遍历完成");
		}
	}
	
	//用来表示解析开始
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
		System.out.println("解析开始");
		
	}
	//用来表示解析结束
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
		System.out.println("解析结束");
	}
	
	//获取节点中元素的值
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		super.characters(ch, start, length);
		String value = new String(ch, start, length);
		if(value.trim()!=""){
			System.out.println("元素的值为:"+value);
		}
		
	}
}
