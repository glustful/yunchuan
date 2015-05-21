package com.yun.chuan.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

public class FileService {

	private String mFile;
	private int length;
	private int pageNo = 0;
	private RandomAccessFile mRaf;
	private byte[] data;
	
	public void open(String path){
		mFile = path;
		try {
			mRaf = new RandomAccessFile(mFile, "r");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public void close(){
		try {
			mRaf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readFile(int direction){
		
		int tmp = length;
		if(direction>0){
			length++;
		}else if(direction<0){
			length--;
		}   
		try {
			   
			mRaf.seek(length*pageNo);
			mRaf.read(data);
			System.out.println("length="+pageNo+"=="+new String(data,"utf8"));
			return new String(data,"gbk");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		length = tmp;
		return null;
	}

	public FileService(int length, int pageNo) {
		super();
		this.length = length;
		this.pageNo = pageNo;
		this.data = new byte[length];
	}
}
