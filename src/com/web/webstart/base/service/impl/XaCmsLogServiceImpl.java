package com.web.webstart.base.service.impl;

import com.alibaba.fastjson.JSON;
import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.entity.*;
import com.web.webstart.base.repository.*;
import com.web.webstart.base.service.XaCmsLogService;
import com.web.webstart.base.service.XaCmsUserService;
import com.web.webstart.base.util.DynamicSpecifications;
import com.web.webstart.base.util.MD5Util;
import com.web.webstart.base.util.SearchFilter;
import com.web.webstart.base.util.XaUtil;
import com.web.webstart.base.vo.FirstLevelMenu;
import com.web.webstart.base.vo.MenuData;
import com.web.webstart.base.vo.SecondLevelMenu;
import com.web.webstart.base.vo.ThirdLevelMenu;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;


@Service("xaCmsLogService")
public class XaCmsLogServiceImpl extends BaseService<XaCmsLog> implements XaCmsLogService {


	@Autowired
	private XaCmsLogRepository xaCmsLogRepository;


	@Override
	public Page<XaCmsLog> getCmsLogByCondition(Map<String, Object> filterParams, Pageable pageable) {
		Map<String, SearchFilter> filters = SearchFilter.parse(filterParams);

		return xaCmsLogRepository.findAll(DynamicSpecifications.bySearchFilter(filters.values(), XaCmsLog.class), pageable);
	}
}

