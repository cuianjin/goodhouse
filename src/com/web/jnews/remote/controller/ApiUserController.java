package com.web.jnews.remote.controller;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.web.jnews.business.constant.HttpSender;
import com.web.jnews.business.entity.User;
import com.web.jnews.remote.service.ApiUserService;
import com.web.jnews.remote.vo.UserVo;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.controller.BaseController;
import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.util.TextUtils;
import com.web.webstart.base.util.Validator;
import com.web.webstart.base.util.XaResult;
import com.web.webstart.base.util.XaUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @Title: ApiUserController.java
 * @Package com.web.jnews.remote.controller
 * @Description: 用户信息接口
 * @author eason.zt
 * @date 2015年3月23日 下午1:00:00
 * @version V1.0
 */
@Api(value = "User", description = "用户相关", position = 10)
@Controller
@RequestMapping("/api/user")
public class ApiUserController extends BaseController {
	@Autowired
	private ApiUserService userService;



	@ApiOperation(value="注册",notes="用户注册,当返回的code=1时，登录成功，返回对象实体")
	@ResponseBody
	@RequestMapping(value="codeLogin",method=RequestMethod.POST)
	public  XaResult<User> codeLogin (
		@ApiParam("手机号,字段名:uPhone") @RequestParam(value = "uPhone") String uPhone,
		@ApiParam("会员名,会员名") @RequestParam() String uName,
		@ApiParam("会员密码,会员密码") @RequestParam() String uPwd
	) throws BusinessException{
		XaResult<User> xr = new XaResult<User>();
		if(XaUtil.isEmpty(uPhone)){
			xr.error("手机号码不能为空");
			return xr;
		}
		if(!Validator.isMobile(uPhone)){
			xr.error("手机号码格式不正确");
			return xr;
		}
		
		
		

		return userService.regist(uPhone,uName,uPwd);
	}


}

