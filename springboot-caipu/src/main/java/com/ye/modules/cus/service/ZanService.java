package com.ye.modules.cus.service;

import com.ye.modules.cus.entity.ZanEntity;

import java.util.List;
import java.util.Map;

/**
 * 点赞
 */
public interface ZanService {
	
	ZanEntity queryObject(Integer id);
	
	List<ZanEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ZanEntity zan);
	
	void update(ZanEntity zan);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
