package tyut.selab.loginservice.Filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebFilter(filterName = "CrosFilter", urlPatterns = {"/*"})
public class CrosFilter implements Filter {
    public List<String> ignoreUrl
            = Arrays.asList(
            "/LoginController","login","/register","/sendEmail");
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request =(HttpServletRequest) servletRequest;
        String requestURI =request.getRequestURI();
        if (!ignoreUrl.contains(requestURI)) {


            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With");
            // 非预检请求,放行即可,预检请求,则到此结束,不需要放行
            if(!request.getMethod().equalsIgnoreCase("OPTIONS")){
                filterChain.doFilter(servletRequest, servletResponse);
            }

        }
        //放行
        filterChain.doFilter(request, response);
    }

}