package com.ye.modules.cus.dao;

import org.apache.ibatis.annotations.Param;

import com.ye.modules.cus.entity.UserEntity;
import com.ye.modules.sys.dao.BaseDao;
/**
 * 
 * 
 * @author ye
 * @email 
 * @date 2018-01-16 10:06:49
 */
public interface UserDao extends BaseDao<UserEntity> {
	 
	public UserEntity login(@Param("username") String username,@Param("password")String password);
	public UserEntity findByUsername(String username);
	public UserEntity loginByType(@Param("username") String username,@Param("password")String password,@Param("userType")String type);
}
