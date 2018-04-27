package com.wangqiang.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class RafDemo {

	public static void main(String[] args) throws IOException{
		File demo = new File("demo");
		if(!demo.exists()){
			demo.mkdir();
			File file =  new File(demo,"raf.dat");
			if(!file.exists()){
				file.createNewFile();
			}
		}
		
		File file1 = new File("/demo/raf.dat");
		RandomAccessFile raf = new RandomAccessFile(file1, "rw");
		//指针的位置
		System.out.println(raf.getFilePointer());
		raf.write('A');//只写一个字节
		System.out.println(raf.getFilePointer());
		raf.write('B');
		
		String s = "中";
		byte[] b = s.getBytes("gbk");
		raf.write(b);
		System.out.println(raf.length());
		
		//读文件必须要将指针移动到
		raf.seek(0);
		//一次性读取,把文件都读取到字节数组中
		byte[] bb = new byte[(int) raf.length()];
		raf.read(bb);
		System.out.println(Arrays.toString(bb));
		String buf = new String(bb,"gbk");
		System.out.println(buf);
		raf.close();
	}
}
