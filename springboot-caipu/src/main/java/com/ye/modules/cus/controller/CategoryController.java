package com.ye.modules.cus.controller;

import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import com.ye.modules.cus.entity.CategoryEntity;
import com.ye.modules.cus.service.CategoryService;

import com.ye.common.utils.Query;
import com.ye.common.utils.R;
import com.ye.common.validator.ValidatorUtils;
import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;

/**
 * 分类
 * 
 */
@Controller
@RequestMapping("/cus/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CategoryEntity> categoryList = categoryService.queryList(query);
		int total = categoryService.queryTotal(query);
		
		return R.ok().put("total", total).put("rows", categoryList);
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

		List<CategoryEntity> categoryList = categoryService.queryList(query);
		CategoryEntity category1=new CategoryEntity();
		category1.setId(0);
		category1.setName("推荐");
		categoryList.add(0,category1 );
		CategoryEntity category=new CategoryEntity();
		category.setId(1000);
		category.setName("全部");
		categoryList.add(0,category );
	 
		CategoryEntity category2=new CategoryEntity();
		category2.setId(-1);
		category2.setName("");
//		categoryList.add(0,category2);
		int total =categoryList.size();
		return R.ok().put("total", total).put("rows", categoryList);
		//return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable("id") Integer id){
		CategoryEntity category = categoryService.queryObject(id);
		
		return R.ok().put("category", category);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public R save(@RequestBody CategoryEntity category){
			  		  	ValidatorUtils.validateEntity(category, AddGroup.class);
		categoryService.save(category);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody CategoryEntity category){
	ValidatorUtils.validateEntity(category, UpdateGroup.class);
		categoryService.update(category);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestBody Integer[] ids){
		categoryService.deleteBatch(ids);
		
		return R.ok();
	}
	@RequestMapping("/categoryadd.html")
	public String categoryadd(HttpServletRequest req) {

		return "modules/cus/categoryadd";
	}

	@RequestMapping("/categoryedit.html")
	public String categoryedit(@RequestParam("id") Integer id,
			HttpServletRequest req) {
		CategoryEntity category = categoryService.queryObject(id);
		
		req.setAttribute("category", category);
		return "modules/cus/categoryedit";
	}
}
