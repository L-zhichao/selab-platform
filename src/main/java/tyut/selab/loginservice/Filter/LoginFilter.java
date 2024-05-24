package tyut.selab.loginservice.Filter;
import tyut.selab.loginservice.utils.JwtHelper;
import tyut.selab.loginservice.utils.WebUtils;
import tyut.selab.utils.Result;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        String token = request.getHeader("token");

        boolean flag = null != token && (!JwtHelper.isExpiration(token));

        if (flag) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            WebUtils.writeJson(response, new Result(505, "token不存在或已过期"));
        }
    }
    @Override
    public void destroy() {
    }
}