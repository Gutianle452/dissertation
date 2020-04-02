package com.ye.modules.sys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统页面视图
 * 
 * @author ye
 *  
 * @date 2016年11月24日 下午11:05:27
 */
@Controller
public class SysPageController {
 

	 
	@RequestMapping("modules/{module}/{url}.html")
	public String module(@PathVariable("module") String module, @PathVariable("url") String url){
		return "modules/" + module + "/" + url + "";
	}

	@RequestMapping("{url}.html")
	public String url(@PathVariable("url") String url){
		return url + "";
	}
//	@RequestMapping("index.html")
//	public String url(HttpServletRequest req){
//		List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(ShiroUtils.getUserId());
//		req.setAttribute("menuList", menuList);
//		req.setAttribute("menu", JSONArray.parseArray(JSON.toJSONString(menuList)));
//		System.out.println(JSONArray.parseArray(JSON.toJSONString(menuList))+"------");
//		return  "index.html";
//	}
 
	
 

	@RequestMapping(value = {"/", "index.html"})
	public String index(HttpServletRequest req){
	 
		
		return "index";
	}

	@RequestMapping("index1.html")
	public String index1(){
		return "index1";
	}

	@RequestMapping("login.html")
	public String login(){
	 
		return "login";
	}
	@RequestMapping("register.html")
	public String register(){
	 
		return "register";
	}
	@RequestMapping("main.html")
	public String main(){
		return "main";
	}

	@RequestMapping("404.html")
	public String notFound(){
		return "404";
	}
	
}
