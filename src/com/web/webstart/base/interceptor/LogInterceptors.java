package com.web.webstart.base.interceptor;

import com.web.webstart.base.constant.XaConstant;
import com.web.webstart.base.entity.XaCmsLog;
import com.web.webstart.base.entity.XaCmsUser;
import com.web.webstart.base.repository.XaCmsLogRepository;
import com.web.webstart.base.util.TextUtils;
import com.web.webstart.base.util.XaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by Jason on 06/12/2016.
 */
public class LogInterceptors implements HandlerInterceptor {

    @Autowired
    private XaCmsLogRepository xaCmsLogRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {


        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String servletPath = request.getServletPath();

        HttpSession session = request.getSession();
        //读取session中的用户
        XaCmsUser user = (XaCmsUser) session.getAttribute(XaConstant.SessionKey.currentUser);
        //请求的IP
        String ip = request.getRemoteAddr();

        String userId = TextUtils.dealNull(request.getParameter("userId"),"0");
        String remark = "";
        String modName = buildPath(servletPath);
        try {
            if (modName.length() > 50) {
                modName = modName.substring(50);
            }
        }catch(Exception e){}



        XaCmsLog log = new XaCmsLog();
        log.setCreateTime(new Date());
        log.setUserId(Long.valueOf(userId));
        log.setModule(modName);
        log.setIp(ip);
        if(XaUtil.isNotEmpty(user)){
            log.setCreateUser(user.getUserId());
            remark = user.getRealName() + "(" + user.getUserName()
                    + ")--" + "操作了--" + buildPath2(servletPath);

            // 记录更多，有用日志
            if(TextUtils.isNotEmpty(request.getParameter("modelIds"))){
                remark += "-ids-" + request.getParameter("modelIds");
            }
            if(TextUtils.isNotEmpty(request.getParameter("status"))){
                remark += "-status-" + request.getParameter("status");
            }

            log.setRemark(remark +"" );
        }
//        xaCmsLogRepository.save(log);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }

    private String buildPath(String s2){
        try {
            s2 = s2.substring(1, s2.lastIndexOf("/"));
            return s2.replace("cms/","");
        }catch(Exception e){
            return "";
        }
    }

    private String buildPath2(String s2){
        try {
            return s2.substring(s2.lastIndexOf("/")+1);
        }catch(Exception e){
            return "";
        }
    }
}
