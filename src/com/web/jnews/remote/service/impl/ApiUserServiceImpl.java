package com.web.jnews.remote.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.web.jnews.business.constant.JConstant;
import com.web.jnews.business.entity.User;
import com.web.jnews.business.repository.UserRepository;
import com.web.jnews.remote.service.ApiUserService;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.service.impl.BaseService;
import com.web.webstart.base.util.DateUtils;
import com.web.webstart.base.util.MD5Util;
import com.web.webstart.base.util.NumberUtils;
import com.web.webstart.base.util.SaltUtil;
import com.web.webstart.base.util.TextUtils;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;

/**
 * @Autor: zhangl
 * @times: 2015-5-15下午06:46:35
 * 类的说明：前端APIUser接口实现类
 **/
@Service("ApiUserService")
@Transactional(readOnly = false)
public class ApiUserServiceImpl extends BaseService<User> implements ApiUserService{

	

	@Autowired
	UserRepository userRepository;
	
	
	

	@Override
	public XaResult<User> regist(String uPhone,String uName,String uPwd)throws BusinessException  {
		XaResult<User> xr = new XaResult<User>();
		User user = new User();
		user.setuPhone(uPhone);
		user.setuName(uName);
		user.setuPwd(SaltUtil.addSalt(uPhone, uPwd));
		user= userRepository.save(user);
		xr.success(user);
		return xr;
	}

	
}
