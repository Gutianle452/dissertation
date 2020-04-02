package com.ye.modules.cus.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;

/**
 * 菜谱
 */
public class MenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//教程名称
	private String name;
	//教程介绍
	private String content;
	//封面
	private String pic;
	//创建时间
	private Date createTime;
	//分类
	private Integer categoryId;
private String category;
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
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
	 * 设置：教程名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：教程名称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：教程介绍
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：教程介绍
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
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：分类
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：分类
	 */
	public Integer getCategoryId() {
		return categoryId;
	}
}
