package com.wangqiang.dom4j;

import java.io.*;
import java.util.List;
import org.dom4j.*;
import org.dom4j.io.*;

public class Dom4jTest {

	public void createXml(String fileName) {

		DocumentFactory factory = new DocumentFactory();
		Document document = factory.createDocument();
		Element root = document.addElement("root");
		for (Integer i = 0; i < 2; ++i) {
			Element book = root.addElement("book");
			book.addAttribute("id", i.toString());
			book.addAttribute("name", "bookname");

			Element author = book.addElement("author");
			author.setText("dalangge");

			Element price = book.addElement("price");
			price.setText("15");
		}

		try {
			FileWriter fw = new FileWriter(fileName);
			OutputFormat format = new OutputFormat("  ", true, "gb2312");
			XMLWriter writer = new XMLWriter(fw, format);
			writer.write(document);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parserXml(String fileName) {
		try {
			File f = new File(fileName);
			InputStream in = new FileInputStream(f);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(in);
			Element root = doc.getRootElement();
			readNode(root);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void readNode(Element root) {
		if (root == null)
			return;

		System.out.println(root.getName() + ":" + (String) root.getData());

		// 获取属性
		List<Attribute> attrs = root.attributes();
		if (attrs != null && attrs.size() > 0) {
			for (Attribute attr : attrs) {
				System.out.println(attr.getName() + ": " + attr.getValue());
			}
		}
		// 获取他的子节点
		List<Element> childNodes = root.elements();
		for (Element e : childNodes) {
			readNode(e);
		}
	}

}
