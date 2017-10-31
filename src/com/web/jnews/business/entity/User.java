package com.web.jnews.business.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.web.jnews.business.constant.JConstant;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.entity.BaseEntity;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
* @ClassName: User 
* @Description: 用户定义表
* @author eason
* @date 2015年3月23日 下午1:00:00 
* 状态： -1：锁定 0：正常
 */
@Entity
@Table(name = "gr_ol_users")
@ApiModel(value="会员表")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value="主键,自动增长")
	private Integer uId;
	@ApiModelProperty(value="会员名,会员名")
	private String uName;
	@ApiModelProperty(value="手机号码,手机号码")
	private String uPhone;
	@ApiModelProperty(value="会员密码,会员密码")
	private String uPwd;
	@ApiModelProperty(value="密码辅助码,密码辅助码")
	private String uPwdCode;
	@ApiModelProperty(value="创建者,创建者")
	private String creator;
	@ApiModelProperty(value="创建时间,创建时间")
	private Integer createTime;
	@ApiModelProperty(value="操作者,操作者")
	private Integer operator;
	@ApiModelProperty(value="操作时间,操作时间")
	private Integer updateTime;
	@ApiModelProperty(value="是否删除,是否删除")
	private Integer isDelete;
	
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Basic(optional = false)
	@Column(name = "uId", nullable = false, columnDefinition = "BIGINT UNSIGNED")
	public Integer getuId() {
		return uId;
	}



	public void setuId(Integer uId) {
		this.uId = uId;
	}



	@Column(nullable=true,length=50)
	public String getuName() {
		return uName;
	}
	
	

	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	@Column(nullable=true,length=255)
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	@Column(nullable=true,length=255)
	public String getuPwdCode() {
		return uPwdCode;
	}
	public void setuPwdCode(String uPwdCode) {
		this.uPwdCode = uPwdCode;
	}
	@Column(nullable=true,length=255)
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	@Column(nullable=true,length=255)
	public Integer getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}
	@Column(nullable=true,length=255)
	public Integer getOperator() {
		return operator;
	}
	public void setOperator(Integer operator) {
		this.operator = operator;
	}
	@Column(nullable=true,length=255)
	public Integer getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Integer updateTime) {
		this.updateTime = updateTime;
	}
	@Column(nullable=true,length=255)
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
}
