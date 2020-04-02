package com.ye.modules.cus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.ye.modules.cus.dao.LikesDao;
import com.ye.modules.cus.entity.LikesEntity;
import com.ye.modules.cus.service.LikesService;



@Service("likesService")
public class LikesServiceImpl implements LikesService {
	@Autowired
	private LikesDao likesDao;
	
	@Override
	public LikesEntity queryObject(Integer id){
		return likesDao.queryObject(id);
	}
	
	@Override
	public List<LikesEntity> queryList(Map<String, Object> map){
		return likesDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return likesDao.queryTotal(map);
	}
	
	@Override
	public void save(LikesEntity likes){
		likesDao.save(likes);
	}
	
	@Override
	public void update(LikesEntity likes){
		likesDao.update(likes);
	}
	
	@Override
	public void delete(Integer id){
		likesDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		likesDao.deleteBatch(ids);
	}
	
}
