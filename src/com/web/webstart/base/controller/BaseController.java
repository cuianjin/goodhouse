package com.web.webstart.base.controller;

import com.web.webstart.base.entity.XaCmsLog;
import com.web.webstart.base.repository.XaCmsLogRepository;
import com.web.webstart.base.util.TextUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.web.webstart.base.exception.BusinessException;
import com.web.webstart.base.exception.DatabaseException;
import com.web.webstart.base.exception.ValidationException;
import com.web.webstart.base.util.XaResult;

import java.util.Date;

/**
 * 基础控制器类
 * @author zj
 *
 */
public abstract class BaseController {

    protected static final Logger LOGGER = Logger.getLogger(BaseController.class);

    @Autowired
    private XaCmsLogRepository xaCmsLogRepository;

    @ExceptionHandler(Exception.class)
    public @ResponseBody
    XaResult<T> handleUncaughtException(Exception ex) {			//系统异常
        LOGGER.error(ex.getMessage(), ex.getCause());
        ex.printStackTrace();
//        addErrorLog(ex.getMessage());
        return new XaResult<T>(ex.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    public @ResponseBody
    XaResult<T> handleValidationException(ValidationException validationEx) {		//数据校验异常
        LOGGER.error(validationEx.getMessage(), validationEx.getCause());
        validationEx.printStackTrace();
//        addErrorLog(validationEx.getMessage());
        return new XaResult<T>(validationEx.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public @ResponseBody
    XaResult<T> handleBusinessException(BusinessException businessEx) {	//业务逻辑异常
        LOGGER.error(businessEx.getMessage(), businessEx.getCause());
        businessEx.printStackTrace();
//        addErrorLog(businessEx.getMessage());
        return new XaResult<T>(businessEx.getMessage());
    }

    @ExceptionHandler(DatabaseException.class)
    public @ResponseBody
    XaResult<T> handleValidationException(DatabaseException dbEx) {		//数据库操作异常
        LOGGER.error(dbEx.getMessage(), dbEx.getCause());
        dbEx.printStackTrace();
//        addErrorLog(dbEx.getMessage());
        return new XaResult<T>(dbEx.getMessage());
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public @ResponseBody
    XaResult<T> handleJSONConvertException(HttpMessageNotWritableException jsonEx) {	//JSON格式转换异常
        LOGGER.error(jsonEx.getMessage(), jsonEx.getCause());
        jsonEx.printStackTrace();
//        addErrorLog(jsonEx.getMessage());
        return new XaResult<T>("JSON格式转换异常");
    }

//    //异常记录库存
//    private void addErrorLog(String exceptionMsg){
//            String ip = "localhost";
//            XaCmsLog log = new XaCmsLog();
//            log.setCreateTime(new Date());
//            log.setUserId(0l);
//            log.setModule("base");
//            log.setIp(ip);
//            log.setCreateUser(0l);
//            log.setRemark( "操作时产生了异常error：" + exceptionMsg );
//            xaCmsLogRepository.save(log);
//    }
}
