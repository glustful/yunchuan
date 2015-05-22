package com.yun.chuan.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

public class FileService {

	private String mFile;
	private InputStreamReader mRaf;
	private char[] data;
	private long mOffset = 0;
	private long mAvaible = 0;
	
	public void open(String path){
		mFile = path;
		try {
			FileInputStream fs = new FileInputStream(mFile);
			mAvaible = fs.available();
			mRaf = new InputStreamReader(fs, "gbk");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	public void close() {
		try {
			mRaf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public String readFile(int direction,long offset){
		
		try {
			if(direction<0){
				offset -= 2*data.length;
			}
			if(offset<0){
				mOffset = 0;
				return "已经是第一页了";
			}else if(offset >mAvaible){
				mOffset = mAvaible;
				return "已经是最后一页了";
			}
			mRaf.skip(offset);
			int len = mRaf.read(data);
			mOffset = offset+len;
			return new String(data,0,len);
		

		} catch (IOException e) {
			
			e.printStackTrace();
		}

		
		return "没有内容了！！！！！！！！！！！！！！";
	}
	
	public long getOffset(){
		
		return mOffset;
	}

	public FileService(int length) {
		super();
		
		this.data = new char[length];

	}


}
