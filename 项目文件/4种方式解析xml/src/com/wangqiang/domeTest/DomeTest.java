package com.wangqiang.domeTest;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomeTest {
	public static void main(String[] args) throws SAXException, IOException {
		//创建一个DocumentBuilderFactory的对象
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			//创建一个DocumentBuilder对象
			DocumentBuilder db= dbf.newDocumentBuilder();
			//读取配置文件
			Document d = db.parse("test.xml");
			//获取所有此节点的集合
			NodeList  node = d.getElementsByTagName("parent");
			System.out.println(node.getLength());
			//遍历parent的每个节点
			for(int i =0;i<node.getLength();i++){
				Node n = node.item(i);
				String n1 = node.item(i).getNodeName();
				NodeList child =  n.getChildNodes();
				System.out.println(child.getLength());
				for(int j = 0 ;j<child.getLength();j++){
					Node nn = child.item(j);
					//区分出text类型的node以及element类型的node
					//System.out.println(nn.getNodeName());
					//获取了element类型的节点名
					if(nn.getNodeType() == Node.ELEMENT_NODE){
						
						System.out.println(child.item(j).getNodeName());
						System.out.println(nn.getFirstChild().getNodeValue());
						System.out.println(nn.getTextContent());
					}
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
