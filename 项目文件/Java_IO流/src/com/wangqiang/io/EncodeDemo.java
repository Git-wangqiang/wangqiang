package com.wangqiang.io;

import java.io.UnsupportedEncodingException;

public class EncodeDemo {

	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = "赵可敏abc";
		byte[] bytes = s.getBytes();
		for(byte b :bytes ){
			//把字节转化成了int并且已十六进制的方式显示
			System.out.print(Integer.toHexString(b & 0xff )+ " ");
		}
		System.out.println();
		byte[] byte1 = s.getBytes("utf-8");//utf-8编码方式中文占用三个字节，英文占用一个字节
		for(byte b :byte1 ){
			System.out.print(Integer.toHexString(b & 0xff )+ " ");
		}
		System.out.println();
	    byte[] byte2 = s.getBytes("gbk");//gbk编码方式中文占用两个字节，英文占用一个字节
		for(byte b :byte2 ){
			System.out.print(Integer.toHexString(b & 0xff )+ " ");
		}
		//java是双字节编码方式 utf-16be，中文占用两个字节英文占用两个字节
		System.out.println();
	    byte[] byte3 = s.getBytes("utf-16be");//gbk编码方式中文占用两个字节，英文占用一个字节
		for(byte b :byte3 ){
			System.out.print(Integer.toHexString(b & 0xff )+ " ");
		}
		
		/*
		 * 当你的字节序列是某种编码时，这个时候想把字节序列变成
		 * 字符串，也需要用这种编码方式，否则会出现乱码
		 */
		System.out.println();
		String str = new String(byte3);
		System.out.println(str);
		System.out.println();
		String st1r = new String(byte3,"utf-16be");
		System.out.println(st1r);
		/**
		 * 文本文件就是字节序列
		 * 可以是任意编码的字节序列
		 * 如果我们在中文集中器上直接创建文本文件，那么该I文本文件之认识ansi编码
		 */
	}
}
