package tyut.selab.loginservice.Filter;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlShowGrantsStatement;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.JwtHelper;
import tyut.selab.loginservice.utils.JwtHelperUtils;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static tyut.selab.loginservice.common.Constant.STATUS_CODE_NON_TOKEN;

/**
 * Description: 所有请求都走此过滤器来判断用户是否登录
 **/
public class LoginFilter implements Filter{
    private String sessionKey;
    private String redirectUrl;
    private String uncheckedUrls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        //获取XML文件中配置参数
        sessionKey = servletContext.getInitParameter("userSessionKey");
        //System.out.println("sessionKey======" + sessionKey);//调试用
        redirectUrl = servletContext.getInitParameter("redirectPage");
        //System.out.println("redirectPage======" + redirectUrl);
        uncheckedUrls = servletContext.getInitParameter("uncheckedUrls");
        //System.out.println("uncheckedUrls=====" + uncheckedUrls);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        UserLocal userLocal =new UserLocal();
        String token = request.getHeader("token");

        boolean flag = null != token && (!JwtHelper.isExpiration(token));

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
    @Override
    public void destroy() {
    }
}