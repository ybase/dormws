package com.ybase.demo.vo;

import com.ybase.bas.annotation.Column;
import com.ybase.bas.annotation.Table;
import com.ybase.bas.vo.BasVO;

/**
 * 用户类<br/>
 *
 * @bas_V1.0, yangxb, 2014-7-16<br/>
 */
@Table("dr_user")
public class User extends BasVO{

	/** 编号 */
	@Column(name = "id", type = "int", key = true)
	private Integer id;

	/** 密码 */
	@Column(name = "passwd", type = "string")
	private String passwd;

	/** 名称 */
	@Column(name = "name", type = "string")
	private String name;

	/** 邮箱 */
	@Column(name = "email", type = "string")
	private String email;

	/** 电话 */
	@Column(name = "phone", type = "string")
	private String phone;

	/** 联系地址 */
	@Column(name = "address", type = "string")
	private String address;

	/** 访问系统次数 */
	@Column(name = "visit", type = "int")
	private Integer visit;

	/** 账号状态 0:未登录 1:已登录 2:停用 */
	@Column(name = "status", type = "int")
	private Integer status;

	/** 系统角色 admin-管理员 */
	@Column(name = "role", type = "string")
	private String role;

	/** 最后登录系统日期 */
	@Column(name = "logindate", type = "string")
	private String loginDate;

	/** 最后登录系统时间 */
	@Column(name = "logintime", type = "string")
	private String loginTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

}
