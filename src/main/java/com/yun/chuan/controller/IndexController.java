package com.yun.chuan.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public String index(ModelMap model,String direction,String offset,HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {   
		int direct = 1;
		if(direction!=null){  
			direct = Integer.parseInt(direction);
		}
		long mOffset = 0;
		if(offset != null){      
			mOffset = Long.parseLong(offset);
		}      
		
		FileService service = new FileService(1024);
		service.open("d:/txt/极品家丁.txt");
		model.addAttribute("content", service.readFile(direct,mOffset));  
		model.addAttribute("offset", service.getOffset());
		service.close();
		return getViewPath("index");            
	}   
	     
	
	@RequestMapping("/show")
	public String show(ModelMap model,HttpServletRequest request){
		return "/user/index";     
	}

}
