package com.happysoul.home.controller;

public class BaseController {

	private final Integer page = 1;
	private final Integer size = 10;
	
	//获取默认页面起始
	public Integer page(Integer page) {
		return (page!=null &&page>0)?page:this.page; 
	}
	
	//获取默认每页数目
	public Integer size(Integer size) {
		return (size!=null &&size>0)?size:this.size;
	}
	
}
