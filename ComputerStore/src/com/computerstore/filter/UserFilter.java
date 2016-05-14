package com.computerstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.computerstore.domain.User;
import com.computerstore.web.servlet.UserServlet;

public class UserFilter implements Filter{

	public void init(FilterConfig filterConfig) throws ServletException {
	}
	/**
	 * when user  login, do filter to judge the status 
	 * 当用户点击登陆界面的时候，就会先执行该方法，用户判断用是否记住了用户名，判断用户是否已经，如果是已经登陆的状态，则直接跳转到主页面
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		// get session，
		HttpServletRequest req = (HttpServletRequest)request;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		// if user exists in session, then already login, OK
		if(user != null){
			chain.doFilter(req, response);
		}else{
			user = new User();
			// if not, get cookie, if user name doesn't exist in cookie, means not remembered
			Cookie[] cookies = req.getCookies();
			if(cookies.length > 0 ){
				for (Cookie cookie : cookies) {
					// if exists, save the value in cookie in user
					if("username".equals(cookie.getName())){
						user.setUsername(cookie.getValue());
					}
					// if password exists, save the value in cookie in user
					if("password".equals(cookie.getName())){
						user.setPassword(cookie.getValue());
					}
				}
			}else{
				chain.doFilter(req, response);
			}
			
			// judge if both user name and password exist
			if(user.getUsername() != null && user.getPassword() != null){
				// query database
				UserServlet us = new UserServlet();
				user = us.findUser(user);
				// if user doesn't exists, let go
				if(user == null){
					chain.doFilter(req, response);
				}else{
					// save user in session, let go
					session.setAttribute("user", user);
					chain.doFilter(req, response);
				}
				
			}else{
				// if not get user name or password, let go
				chain.doFilter(req, response);
			}
		}
	}

	public void destroy() {
	}

}
