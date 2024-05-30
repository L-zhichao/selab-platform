package tyut.selab.loginservice.Filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.JwtHelperUtils;
import tyut.selab.loginservice.utils.SecurityUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Description: 所有请求都走此过滤器来判断用户是否登录
 **/
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {
    public List<String> ignoreUrl
            = Arrays.asList(
            "/LoginController","/login","/register","/sendEmail");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String requestURI =request.getRequestURI();
        if (ignoreUrl.contains(requestURI)) {
            //放行
            filterChain.doFilter(request, response);
        }
        UserLocal userLocal =new UserLocal();
        String token = request.getHeader("Authorization");

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
        }
    }

}