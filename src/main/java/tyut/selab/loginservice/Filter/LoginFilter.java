package tyut.selab.loginservice.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.JwtHelperUtils;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

import java.io.IOException;

import static tyut.selab.loginservice.common.Constant.STATUS_CODE_NON_TOKEN;

/**
 * Description: 所有请求都走此过滤器来判断用户是否登录
 **/
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter{
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserLocal userLocal =new UserLocal();
        String token = request.getHeader("token");

        boolean flag = null != token && (!JwtHelperUtils.isExpiration(token));

        if (flag) {
            userLocal.setUserId(JwtHelperUtils.getUserId(token));
            userLocal.setUserName(JwtHelperUtils.getUsername(token));
            userLocal.setGroupId(JwtHelperUtils.getGroupId(token));
            userLocal.setRoleId(JwtHelperUtils.getRoleId(token));
            userLocal.setToken(token);
            //将Token传给实体类对象UserLocal，并存入到ThreadLocal中，把该对象传给前端
            SecurityUtil.setUser(userLocal);
            filterChain.doFilter(servletRequest, servletResponse);
            //将token写到响应头里
            response.addHeader("Authorization", "Bearer " + token);
        } else {
            WebUtils.writeJson(response, Result.error(STATUS_CODE_NON_TOKEN, ""));
        }
    }

}