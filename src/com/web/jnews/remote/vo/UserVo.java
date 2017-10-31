package com.web.jnews.remote.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class UserVo {
		
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
		public Integer getuId() {
			return uId;
		}
		public void setuId(Integer uId) {
			this.uId = uId;
		}
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
		public String getuPwd() {
			return uPwd;
		}
		public void setuPwd(String uPwd) {
			this.uPwd = uPwd;
		}
		public String getuPwdCode() {
			return uPwdCode;
		}
		public void setuPwdCode(String uPwdCode) {
			this.uPwdCode = uPwdCode;
		}
		public String getCreator() {
			return creator;
		}
		public void setCreator(String creator) {
			this.creator = creator;
		}
		public Integer getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Integer createTime) {
			this.createTime = createTime;
		}
		public Integer getOperator() {
			return operator;
		}
		public void setOperator(Integer operator) {
			this.operator = operator;
		}
		public Integer getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(Integer updateTime) {
			this.updateTime = updateTime;
		}
		public Integer getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}
		
	}


