package com.ye.modules.cus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ye.common.utils.Query;
import com.ye.common.utils.R;
import com.ye.common.validator.ValidatorUtils;
import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;
import com.ye.modules.cus.entity.MenuEntity;
import com.ye.modules.cus.service.CategoryService;
import com.ye.modules.cus.service.CommentService;
import com.ye.modules.cus.service.MenuService;

/**
 * 菜谱
 * 
 */
@Controller
@RequestMapping("/cus/menu")
public class MenuController {
	@Autowired
	private MenuService menuService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CommentService commentService;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<MenuEntity> menuList = menuService.queryList(query);
		int total = menuService.queryTotal(query);
		
		return R.ok().put("total", total).put("rows", menuList);
		//return R.ok().put("page", pageUtil);
	}
	/**
	 * 列表
	 */
	@RequestMapping("/list1")
	@ResponseBody
	public R list1(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
        
        List<MenuEntity> diyList=new ArrayList<MenuEntity>();
        if(Integer.parseInt(query.get("categoryId").toString())==0){
        	 diyList= menuService.recommendList(Integer.parseInt(params.get("userId").toString()));
        }
        else  if(Integer.parseInt(query.get("categoryId").toString())>=1000){
        	query.remove("categoryId");
        } 
        else  diyList= menuService.queryList(query);
		int total = diyList.size();
		
		 	return R.ok().put("total", total).put("rows", diyList);
		//return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable("id") Integer id){
		MenuEntity menu = menuService.queryObject(id);
		HashMap<String, Object> comments=new HashMap<String,Object>();
		comments.put("menuId", id);
		return R.ok().put("menu", menu).put("comment", commentService.queryList(comments));

	 
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public R save(@RequestBody MenuEntity menu){
			  		  		  		  		 menu.setCreateTime(new Date());
	 	  		  	ValidatorUtils.validateEntity(menu, AddGroup.class);
		menuService.save(menu);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody MenuEntity menu){
	ValidatorUtils.validateEntity(menu, UpdateGroup.class);
		menuService.update(menu);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestBody Integer[] ids){
		menuService.deleteBatch(ids);
		
		return R.ok();
	}
	@RequestMapping("/menuadd.html")
	public String menuadd(HttpServletRequest req) {
		HashMap<String, Object> map=new HashMap<String, Object>();
		 
		req.setAttribute("list",categoryService.queryList(map));

		return "modules/cus/menuadd";
	}

	@RequestMapping("/menuedit.html")
	public String menuedit(@RequestParam("id") Integer id,
			HttpServletRequest req) {
		MenuEntity menu = menuService.queryObject(id);
		HashMap<String, Object> map=new HashMap<String, Object>();
		 
		req.setAttribute("list",categoryService.queryList(map));

		req.setAttribute("menu", menu);
		return "modules/cus/menuedit";
	}
}
