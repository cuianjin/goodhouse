package com.web.webstart.remote.interceptor;


import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.entity.XaCmsLog;
import com.web.webstart.base.entity.XaCmsUser;
import com.web.webstart.base.repository.XaCmsLogRepository;
import com.web.webstart.base.util.NumberUtils;
import com.web.webstart.base.util.TextUtils;
import com.web.webstart.base.util.XaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Enumeration;

/**
 * Created by Jason on 06/12/2016.
 */
public class VerInterceptors implements HandlerInterceptor {


    @Autowired
    private XaCmsLogRepository xaCmsLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String servletPath = request.getServletPath();
        XaCmsLog log = new XaCmsLog();
        // 前端 会员 登录日志
//        if(servletPath.contains("codeLogin")
//                || servletPath.contains("isBindThreeAccount")){
            String ip = request.getRemoteAddr();
            String mobile = TextUtils.dealNull(request.getParameter("mobile"),"0");
            String userId = TextUtils.dealNull(request.getParameter("userId"),"0");

            log.setCreateTime(new Date());
            log.setUserId(Long.valueOf(NumberUtils.parseInt(userId)));
            log.setModule("api");
            log.setIp(ip);
            log.setCreateUser(0l);
            log.setRemark( mobile + "--操作了--" + servletPath +"-by-"
                    + TextUtils.dealNull(request.getParameter("IMEI"),"**"));
//            xaCmsLogRepository.save(log);
//        }

//        if(TextUtils.isNotEmpty(request.getParameter("IMEI"))){
//            if(request.getParameter("IMEI").length() > 32){
//                throw
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {


    }

}
