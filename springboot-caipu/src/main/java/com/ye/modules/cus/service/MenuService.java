package com.ye.modules.cus.service;

import java.util.List;
import java.util.Map;

import com.ye.modules.cus.entity.MenuEntity;

/**
 * 菜谱
 */
public interface MenuService {
	
	MenuEntity queryObject(Integer id);
	
	List<MenuEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MenuEntity menu);
	
	void update(MenuEntity menu);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
	
	 
	  List<MenuEntity> recommendList(Integer userId);
}
