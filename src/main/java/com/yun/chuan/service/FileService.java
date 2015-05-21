package com.yun.chuan.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;

public class FileService {

	private String mFile;

	private RandomAccessFile mRaf;
	private byte[] data;

	public void open(String path) {
		mFile = path;
		try {
			mRaf = new RandomAccessFile(mFile, "r");

		} catch (FileNotFoundException e) {
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

	public String readFile(int page, long offset) {

		try {
			if(page<0){
				offset -= data.length;
			}
			mRaf.seek(offset);
			int len = mRaf.read(data);
			if (page > 0) {
				for (int i = (len - 1); i > 0; i--) {

					if (data[i] == -93 && data[i - 1] == -95) {

						mRaf.seek(offset + i + 1);
						len = i;
						break;
					}
				}
				return new String(data, 0, len + 1, "gbk");
			} else if (page < 0) {
				mRaf.seek(len);
				int index = 0;
				for (int i = 0; i < len - 1; i++) {

					if (data[i] == -95 && data[i + 1] == -93) {

						index = i;
						break;
					}
				}
				return new String(data, index, len-index, "gbk");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public long getOffset() {
		try {
			return mRaf.getFilePointer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public FileService(int length) {
		super();

		this.data = new byte[length];
	}
}
