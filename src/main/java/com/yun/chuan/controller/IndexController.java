package com.yun.chuan.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yun.chuan.service.FileService;

@Controller
public class IndexController extends BaseController{
	
	public static final String URL = "/function/";
	
	
	@Override
	protected String getViewPath(String path) {
		return URL + path;
	}  
	
	@RequestMapping("/index")
	public String index(ModelMap model,String pageNo,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {   
		int page = 0;
		if(pageNo!=null){  
			page = Integer.parseInt(pageNo);
		}
		System.out.println("pageno="+pageNo);
		FileService service = new FileService(1025, page);
		service.open("d:/txt/极品家丁.txt");
		model.addAttribute("content", service.readFile(0));  
		model.addAttribute("pageNo", page);
		service.close();
		return getViewPath("index");         
	}   
	   
	@ResponseBody
	@RequestMapping("/show")
	public String show(ModelMap model,int pageNo,HttpServletRequest request){
		return pageNo+"";     
	}

}
