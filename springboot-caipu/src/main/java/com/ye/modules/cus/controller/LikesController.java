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
import com.ye.modules.cus.entity.LikesEntity;
import com.ye.modules.cus.service.LikesService;

import com.ye.common.utils.Query;
import com.ye.common.utils.R;
import com.ye.common.validator.ValidatorUtils;
import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;

/**
 * 收藏
 * 
 */
@Controller
@RequestMapping("/cus/likes")
public class LikesController {
	@Autowired
	private LikesService likesService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<LikesEntity> likesList = likesService.queryList(query);
		int total = likesService.queryTotal(query);
		
		return R.ok().put("total", total).put("rows", likesList);
		//return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@ResponseBody
	public R info(@PathVariable("id") Integer id){
		LikesEntity likes = likesService.queryObject(id);
		
		return R.ok().put("likes", likes);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@ResponseBody
	public R save(@RequestBody LikesEntity likes){
			  		  		  		 likes.setCreateTime(new Date());
	 	  	ValidatorUtils.validateEntity(likes, AddGroup.class);
		likesService.save(likes);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@ResponseBody
	public R update(@RequestBody LikesEntity likes){
	ValidatorUtils.validateEntity(likes, UpdateGroup.class);
		likesService.update(likes);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public R delete(@RequestBody Integer[] ids){
		likesService.deleteBatch(ids);
		
		return R.ok();
	}
	@RequestMapping("/likesadd.html")
	public String likesadd(HttpServletRequest req) {

		return "modules/cus/likesadd";
	}

	@RequestMapping("/likesedit.html")
	public String likesedit(@RequestParam("id") Integer id,
			HttpServletRequest req) {
		LikesEntity likes = likesService.queryObject(id);
		
		req.setAttribute("likes", likes);
		return "modules/cus/likesedit";
	}
}
