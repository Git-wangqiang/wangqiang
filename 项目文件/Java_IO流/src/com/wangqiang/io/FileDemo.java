package com.wangqiang.io;

import java.io.File;
import java.io.IOException;

public class FileDemo {

	
	public static void main(String[] args) throws IOException {
		File file = new File("D:\\javaio");
		//判断文件是否存在
		// System.out.println(file.exists());
		if(!file.exists()){
			file.mkdir();//创建文件
		}else{
			file.delete();//删除文件
		}
		//是否是一个目录
		System.out.println(file.isDirectory());
		//是否是一个文件
		System.out.println(file.isFile());
		
		File file2 = new File("D:\\javaio\\日记.txt");
		if(file2.exists()){
			file2.createNewFile();
		}else{
			file2.delete();
		}
		System.out.println(file2);
		System.out.println(file2.getName());
	}
}
