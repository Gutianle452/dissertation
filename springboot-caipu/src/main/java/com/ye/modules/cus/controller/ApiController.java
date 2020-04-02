package com.ye.modules.cus.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ye.common.exception.RRException;
import com.ye.common.utils.Query;
import com.ye.common.utils.R;
import com.ye.common.utils.ResourceUtil;
import com.ye.modules.cus.entity.GonggaoEntity;
import com.ye.modules.cus.service.GonggaoService;
import com.ye.modules.cus.service.UserService;
				
@RestController
@RequestMapping("/cus/api")
public class ApiController {
	public final String OBJ_TAG = "data";

	@Autowired
	public UserService userService;
	@Autowired
	private GonggaoService gonggaoService;
	 
	 

	private void initPage(Map<String, Object> params) {
		params.put("page", "1");
		params.put("limit", "100");
		params.put("sidx", "");
		params.put("order", "");

	}

	/**
	 * 上传文件	
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public R upload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		// 上传文件
		String suffix = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		System.out.println(suffix);
		// String url = OSSFactory.build().uploadSuffix(file.getBytes(),
		// suffix);

		String realPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/upload";// 文件的硬盘真实路径
		realPath = ResourceUtil.getConfigByName("basePath");
		File file0 = new File(realPath);
		if (!file0.exists()) {
			file0.mkdirs();// 创建根目录
		}

		File savefile = new File(realPath + "/" + System.currentTimeMillis()
				+ suffix);
		System.out.println(savefile.getPath());
		// 文件拷贝到指定硬盘目录
		FileCopyUtils.copy(file.getBytes(), savefile);

		String contextPath = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + contextPath;
		return R.ok().put("url", "/upload/" + savefile.getName());
	}
	/**
	 * 上传文件	
	 */
	@RequestMapping("/wxupload")
	@ResponseBody
	public String wxupload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		// 上传文件
		String suffix = file.getOriginalFilename().substring(
				file.getOriginalFilename().lastIndexOf("."));
		System.out.println(suffix);
		// String url = OSSFactory.build().uploadSuffix(file.getBytes(),
		// suffix);

		String realPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ "/upload";// 文件的硬盘真实路径
		realPath = ResourceUtil.getConfigByName("basePath");
		File file0 = new File(realPath);
		if (!file0.exists()) {
			file0.mkdirs();// 创建根目录
		}

		File savefile = new File(realPath + "/" + System.currentTimeMillis()
				+ suffix);
		System.out.println(savefile.getPath());
		// 文件拷贝到指定硬盘目录
		FileCopyUtils.copy(file.getBytes(), savefile);

		String contextPath = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + contextPath;
		return  "/upload/" + savefile.getName() ;
	}
 
	/**
	 * 公告列表
	 */
	@RequestMapping("/gonggaolist")
	@ResponseBody
	public R gonggaolist(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);

		List<GonggaoEntity> list = gonggaoService.queryList(query);

		return R.ok().put(OBJ_TAG, list);

	}

	// 公告信息
	@RequestMapping("/gonggaoinfo")
	@ResponseBody
	public R gonggaoinfo(@RequestParam Integer id) {

		GonggaoEntity good = gonggaoService.queryObject(id);

		return R.ok().put(OBJ_TAG, good);

	}

	 
}
