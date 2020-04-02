package com.ye.modules.cus.service;

import com.ye.modules.cus.entity.LikesEntity;

import java.util.List;
import java.util.Map;

/**
 * 收藏
 */
public interface LikesService {
	
	LikesEntity queryObject(Integer id);
	
	List<LikesEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LikesEntity likes);
	
	void update(LikesEntity likes);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
