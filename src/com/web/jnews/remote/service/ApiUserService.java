package com.web.jnews.remote.service;

import com.web.jnews.business.entity.User;
import com.web.jnews.remote.vo.UserVo;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.XaResult;

public interface ApiUserService  {

	 XaResult<User> regist(String uPhone, String uName,String uPwd) throws BusinessException;

}
