package com.ye.modules.cus.entity;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.ye.common.validator.group.AddGroup;
import com.ye.common.validator.group.UpdateGroup;

/**
 * 
 * 
 * @author ye
 * @email
 * @date 2018-04-17 10:05:39
 */
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	// ID
	private Integer id;
	// 用户名
	private String username;
	// 密码
	private String password;
	// 昵称
	private String nickname;
	// 手机号
	private String phone;
	// 用户类型
	private String userType;
	// 用户头像
	private String pic;
	// 性别
	private String sex;
	// 创建时间
	private Date createTime;
	// 地址
	private int status;

	private String time;
	private String cert;
	private String lingyu;
	
	private String idcard;
	 

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCert() {
		return cert;
	}

	public void setCert(String cert) {
		this.cert = cert;
	}

	public String getLingyu() {
		return lingyu;
	}

	public void setLingyu(String lingyu) {
		this.lingyu = lingyu;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 设置：ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 获取：ID
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置：昵称
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 获取：昵称
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 设置：手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * 获取：手机号
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * 设置：用户类型
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	/**
	 * 获取：用户类型
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * 设置：用户头像
	 */
	public void setPic(String pic) {
		this.pic = pic;
	}

	/**
	 * 获取：用户头像
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * 设置：性别
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * 获取：性别
	 */
	public String getSex() {
		return sex;
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

}
