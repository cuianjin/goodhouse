package com.web.webstart.base.controller;

import com.web.webstart.base.entity.XaCmsLog;
import com.web.webstart.base.service.XaCmsLogService;
import com.web.webstart.base.util.WebUitl;
import com.web.webstart.base.util.XaResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@Deprecated
@Controller
@RequestMapping("xaCmsLog")
public class XaCmsLogController {
	
	@Autowired
	private XaCmsLogService xaCmsLogService;


	@ResponseBody
	@RequestMapping(value ="showCmsLog",method=RequestMethod.GET)
	public XaResult<Page<XaCmsLog>> showCmsLog(
			@RequestParam(defaultValue="0") Integer nextPage,
			@RequestParam(defaultValue="10") Integer pageSize,
			@RequestParam String sortDate,
			@RequestParam String jsonFilter){
		
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize, sortDate);
		Map<String,Object> filterParams =  WebUitl.getParametersStartingWith(jsonFilter, "search_");
		Page<XaCmsLog> data = xaCmsLogService.getCmsLogByCondition(filterParams, pageable);
		XaResult<Page<XaCmsLog>> xr = new XaResult<>();
		xr.setObject(data);
		return xr;
	}

}

