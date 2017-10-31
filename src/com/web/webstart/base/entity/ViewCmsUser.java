package com.web.webstart.base.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Date;

/**
 * 职员视图，用于后台列表
 */

@Entity
@Table(name = "view_cms_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ViewCmsUser implements java.io.Serializable {

	private static final long serialVersionUID = -2588141205901945887L;

	/** 用户Id. */
	private Long userId;

	/** 用户名. */
	private String userName;

	/** 真实姓名. */
	private String realName;

	/**
	 * 用户状态. 1.正常 0.锁定 9.删除
	 */
	private int status;


	/** 手机号. */
	private String mobile;

	/** 角色信息. */
	private String rolenames;

	/** 最后登录时间 */
	private Date lastLoginTime;

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "user_name", nullable = false, length = 20)
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "real_name", nullable = false, length = 20)
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Column(name = "status")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name = "mobile", length = 20)
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "lastLoginTime")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	@Column
	public String getRolenames() {
		return rolenames;
	}

	public void setRolenames(String rolenames) {
		this.rolenames = rolenames;
	}
}
