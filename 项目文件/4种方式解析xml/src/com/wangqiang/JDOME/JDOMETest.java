/*package com.wangqiang.JDOME;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.jdom2.input.SAXBuilder;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.output.XMLOutputter;

public class JDOMETest {

	//创建一个SAXBuilder对象
	@SuppressWarnings("deprecation")
	SAXBuilder saxBuilder = new SAXBuilder(false);
	InputStream input = new FileInputStream("test.xml");
	SAXBuilder.build(input);
	
	
	
	//（1）使用JDOM首先要指定使用什么解析器。如：
	SAXBuilder builder=new SAXBuilder(false);//
	//这表示使用的是默认的解析器
	//（2）得到Document，我们以后要进行的所有操作都是对这个Document操作的：
	Reader returnQuote = new StringReader("test.xml");
	org.jdom2.Document doc = builder.build(returnQuote);
	//（3）得到根元素：
	org.jdom2.Element books=doc.getRootElement();
	//在JDOM中所有的节点（DOM中的概念）都是一个org.jdom.Element 类，当然他的子节点也是一个org.jdom.Element类。
	//（4）得到元素（节点）的集合：
	List booklist=books.getChildren(“book”);
	//这表示得到“books”元素的所在名称为“book”的元素，并把这些元素都放到一个List集合中
	//得到单个元素Element segment= books.getChild(“Segment”);
	//（5）轮循List集合
	for (Iterator iter = booklist.iterator(); iter.hasNext();) {
	Element book = (Element) iter.next();
	｝
	//还有一种轮循方法是：
	for(int i=0;I
	Element book=(Element)booklist.get(i);
	}
	//（6）取得元素的属性：
	String email=book.getAttributeValue(“email”);
	//取得元素book的属性名为“email”的属性值。
    //（7）取得元素的子元素（为最低层元素）的值：
    String name=book.getChildTextTrim(“name”);
	//注意的是，必须确定book元素的名为“name”的子元素只有一个。
	//（8）改变元素（为最低层元素）的值：
	book.getChild(“name”).setText(“alterrjzjh”);
	//这只是对Document的修改，并没有在实际的XML文档中进行修改
	//（9）保存Document的修改到XML文件中：
	XMLOutputter outputter=new XMLOutputter();
	outputter.output(doc,new FileOutputStream(xmlpath));
	//我们先要有一个 XMLOutputter类，再把已经修改了的Document保存进XML文档中
}
*/