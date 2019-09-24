package com.c1801.spring.dzy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.nio.file.attribute.FileAttribute;

/**
 * user：少
 * dateTime: 2019/8/15 18:35
 * 过滤器
 */
public class LogCostFilter implements Filter {
    
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("来访ip = " + servletRequest.getLocalAddr()); //请求来源ip地址
        logger.info("协议 = " + servletRequest.getProtocol()); //请求协议
        logger.info("servletRequest = " + servletRequest.getRemoteHost());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
