package com.web.webstart.base.service;

import com.web.webstart.base.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;



public interface XaCmsLogService {



	/**
	 * 根据条件分页查询日志列表
	 * @param filterParams
	 * @param pageable
     * @return
     */
	Page<XaCmsLog> getCmsLogByCondition(Map<String, Object> filterParams, Pageable pageable);
	}

