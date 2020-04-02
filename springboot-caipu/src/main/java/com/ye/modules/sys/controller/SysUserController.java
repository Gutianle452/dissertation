package com.ye.modules.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ye.common.utils.Query;
import com.ye.common.utils.R;
import com.ye.common.validator.Assert;
import com.ye.common.validator.ValidatorUtils;
import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;
import com.ye.modules.sys.entity.SysUserEntity;
import com.ye.modules.sys.service.SysUserService;
import com.ye.modules.sys.shiro.ShiroUtils;

/**
 * 系统用户
 * 
 * @author ye
 * 
 * @date 2016年10月31日 上午10:40:10
 */
@Controller
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
 
 
 

	/**
	 * 所有用户列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public R list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<SysUserEntity> userList = sysUserService.queryList(query);
		int total = sysUserService.queryTotal(query);

	 
		return R.ok().put("total", total).put("rows", userList);
		// return R.ok().put("page", pageUtil);
	}

	/**
	 * 获取登录的用户信息
	 */
	@RequestMapping("/info")
	@ResponseBody
	public R info() {
		return R.ok().put("user", getUser());
	}

	@RequestMapping("/useradd.html")
	public String useradd(HttpServletRequest req) {
		Map<String, Object> map=new HashMap<String, Object>();  
		return "modules/sys/useradd";
	}

	@RequestMapping("/useredit.html")
	public String useredit(@RequestParam("id") Long userId,
			HttpServletRequest req) {
		SysUserEntity user = sysUserService.queryObject(userId);

	 
		req.setAttribute("user", user);
		Map<String, Object> map=new HashMap<String, Object>(); 
		return "modules/sys/useredit";
	}
	@RequestMapping("/userinfo.html")
	
	public String userinfo(  
			HttpServletRequest req) {
		SysUserEntity user = sysUserService.queryObject(ShiroUtils.getUserId());

		 
		req.setAttribute("user", user);
		return "modules/sys/userinfo";
	}
	/**
	 * 修改登录用户密码
	 */
	@RequestMapping("/password")
	@ResponseBody
	public R password(String password, String newPassword) {
		Assert.isBlank(newPassword, "新密码不为能空");

		// 原密码
		password = ShiroUtils.sha256(password, getUser().getSalt());
		// 新密码
		newPassword = ShiroUtils.sha256(newPassword, getUser().getSalt());

		// 更新密码
		int count = sysUserService.updatePassword(getUserId(), password,
				newPassword);
		if (count == 0) {
			return R.error("原密码不正确");
		}

		return R.ok();
	}

	/**
	 * 用户信息
	 */
	@RequestMapping("/info/{userId}")
	@ResponseBody

	public R info(@PathVariable("userId") Long userId) {
		SysUserEntity user = sysUserService.queryObject(userId);

		 

		return R.ok().put("user", user);
	}

	/**
	 * 保存用户
	 */
	@RequestMapping("/save")
	@ResponseBody
 
	public R save(@RequestBody SysUserEntity user) {
		 
		ValidatorUtils.validateEntity(user, AddGroup.class);

		sysUserService.save(user);

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@RequestMapping("/update")
	@ResponseBody
 
	public R update(@RequestBody SysUserEntity user) {
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */

	@RequestMapping("/delete")
	@ResponseBody
 
	public R delete(@RequestBody Long[] userIds) {
		if (ArrayUtils.contains(userIds, 1L)) {
			return R.error("系统管理员不能删除");
		}

		if (ArrayUtils.contains(userIds, getUserId())) {
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return R.ok();
	}
}
