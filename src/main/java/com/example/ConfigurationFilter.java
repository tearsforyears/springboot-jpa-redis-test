package com.example;

import com.example.component.RequestUtils;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author zhanghaoyang
 */
@Configuration
public class ConfigurationFilter {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    // 下面这个bean比较特别 因为是要在listener之后就加入web context之前进行初始化的 所以必须告诉
    // springboot这个是过滤器 得提前初始化
    @Bean
    public FilterRegistrationBean testFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());// 添加过滤器
        registration.addUrlPatterns("/*");// 设置过滤路径，/*所有路径
        registration.addInitParameter("name", "myFilter");// 添加默认参数
        registration.setName("MyFilter");
        registration.setOrder(1);// 设置优先级
        return registration;

    }

    @Bean
    public FilterRegistrationBean LoginFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new LoginFilter());// 添加过滤器
        registration.addUrlPatterns("/user/*");// 设置过滤路径，/*所有路径
        registration.addInitParameter("name", "loginFilter");// 添加默认参数
        registration.setName("LoginFilter");
        registration.setOrder(2);// 设置优先级
        return registration;
    }

    public class LoginFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpSession sess = req.getSession();
            String str = (String) sess.getAttribute("username");
            if (null != str && !"".equals(str) && str.equals(req.getParameter("username"))) {// had login
                String msg = "{state:200,msg:'login success by filter'}";
                servletRequest.getRequestDispatcher("/msg?type=forward&msg=" + msg).forward(servletRequest, servletResponse);// send msg
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        }

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void destroy() {

        }
    }

    public class MyFilter implements Filter {
        @Override
        public void destroy() {
        }


        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain
                filterChain) throws IOException, ServletException {
            HttpServletRequest request = (HttpServletRequest) srequest;
            //打印请求Url
            System.out.println(RequestUtils.getIpAddress(request) + "url :" + request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }


        @Override
        public void init(FilterConfig arg0) throws ServletException {
        }

    }
}
