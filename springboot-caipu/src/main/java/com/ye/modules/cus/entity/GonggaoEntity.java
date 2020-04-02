package com.ye.modules.cus.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;

/**
 * 文章
 */
public class GonggaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//标题
	private String name;
	//内容
	private String content;
	//封面
	private String pic;
	//用户
	private Integer userId;
	//
	private Date createTime;
	private String user;
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：标题
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：封面
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}
	/**
	 * 获取：封面
	 */
	public String getPic() {
		return pic;
	}
	/**
	 * 设置：用户
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户
	 */
	public Integer getUserId() {
		return userId;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
