package com.ye.modules.cus.service;

import com.ye.modules.cus.entity.CategoryEntity;

import java.util.List;
import java.util.Map;

/**
 * 分类
 */
public interface CategoryService {
	
	CategoryEntity queryObject(Integer id);
	
	List<CategoryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CategoryEntity category);
	
	void update(CategoryEntity category);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
