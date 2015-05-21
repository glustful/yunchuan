package com.yun.chuan.controller;

import javax.servlet.ServletContext;

import org.springframework.web.context.ServletContextAware;

public abstract class BaseController implements ServletContextAware{
	
	public static final String RELOAD = "redirect:";// RELOAD + URL
	public static final String FORWARD = "forward:";// RELOAD + URL
	
	protected ServletContext servletContext;
	
	public String getContextPath(){
		return servletContext.getContextPath();
	}
	
	public void setServletContext(ServletContext servletContext) {
		 this.servletContext = servletContext;
		
	}
	
	/**
	 * 判断字符串是否为数字
	 * @param num
	 * @return
	 */
	protected Boolean isNumber(String num){
		try {  
            Integer.valueOf(num);//把字符串强制转换为数�?  
            return true;//如果是数字，返回True  
        } catch (Exception e) {  
            return false;//如果抛出异常，返回False  
        }  
	}
	
	/**
	 * 传入相对路径，获取结果绝对路�?
	 * @param path
	 * @return
	 */
	protected abstract String getViewPath(String path);
	protected String reload(String path) {
		return RELOAD + path;
	}
	protected String forward(String path) {
		return FORWARD + path;
	}
}
