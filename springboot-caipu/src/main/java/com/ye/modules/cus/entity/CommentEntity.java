package com.ye.modules.cus.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;

/**
 * 评论
 */
public class CommentEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//评论内容
	private String content;
	//菜谱
	private Integer menuId;
	//用户
	private Integer userId;
	//评论时间
	private Date createTime;
	private String user;
	private String pic;
	private String menu;
	

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
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
	 * 设置：评论内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：评论内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：菜谱
	 */
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
	/**
	 * 获取：菜谱
	 */
	public Integer getMenuId() {
		return menuId;
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
	 * 设置：评论时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：评论时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
