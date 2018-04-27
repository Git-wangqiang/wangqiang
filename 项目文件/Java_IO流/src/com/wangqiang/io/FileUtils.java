package com.wangqiang.io;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	/**
	 * 列出指定目录下（包括其子目录）的所有文件
	 * 
	 * @param dir
	 * @throws IOException
	 */
	// 列出File的一些常用操作比如过滤，遍历等操作
	public static void listDirectory(File dir) throws IOException {

		if (!dir.exists()) {
			throw new IllegalArgumentException("目录：" + dir + "不存在");
		}
		if (!dir.isDirectory()) {
			throw new IllegalArgumentException(dir + "不是目录");
		}
		String[] fileNames = dir.list();
		for (String string : fileNames) {
			System.out.println(string);
		}

		/*
		 * 如果要遍历子目录下的内容就需要构造成FIle对象做递归,File提供了直接返回File对象的
		 */

		File[] files = dir.listFiles();// 返回的是知己子目录的抽象
		if (files != null && files.length > 0) {
			for (File file : files) {
				// System.out.println(file);
				if(file.isDirectory()){
					//递归 
					listDirectory(file);
				}else{
					System.out.println(file);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		listDirectory(new File("D:\\javaio"));
	}
}
