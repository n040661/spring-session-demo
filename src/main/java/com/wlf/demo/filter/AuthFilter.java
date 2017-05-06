package com.wlf.demo.filter;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		HttpSession session = req.getSession();  
		if(session.isNew()){
			System.out.println("²»ÊÇµÇÂ¼×´Ì¬£¬Ìø×ªµ½µÇÂ¼Ò³Ãæ!");
			resp.sendRedirect("/index.html");
		}else{
			chain.doFilter(request, response);
		}
	    
	}

	@Override
	public void destroy() {
		
	}

}
