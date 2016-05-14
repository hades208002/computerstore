package com.computerstore.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.computerstore.domain.User;
import com.computerstore.domain.Orders;
import com.computerstore.exception.ComputerstoreException;
import com.computerstore.service.UserService;

/**
 * user handler
 * @author carlo
 */
public class UserServlet extends BaseServlet {

	private static final long serialVersionUID = 4382063731237584909L;
	
	/**
	 * register
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void register(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get parameter
		Map<String,String[]> map = request.getParameterMap();
		User user = new User();
		// Encapsulates the data to javaBean
		try {
			BeanUtils.populate(user, map);
			
			UserService us = new UserService();
			us.register(user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("regMsg", e.getMessage());
			request.getRequestDispatcher("/register.jsp").forward(request, response);
			return;
		} 
		// After successful registration, jump, allow users to log in
		response.sendRedirect(request.getContextPath()+"/registersuccess.jsp");
	}
	
	/**
	 * login
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get input data and encapsulate the data to javaBean
		Map<String,String> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "Data exception!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		
		// Call the service layer, query the user exists, if it exists, return user 
		UserService us = new UserService();
		user = us.login(user);
		// if user is null, query fail
		if(user == null){
			request.setAttribute("msg", "Wrong username or password!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}else{
			//If user check "Remember user name", save the user name in a cookie and set saving time to 1 hour
			String remUsername = request.getParameter("remUsername");
			//If user checked "Automatically login", save name and password locally
			String autoLogin = request.getParameter("autoLogin");

			if("remUsername".equals(remUsername)){
				Cookie cookie = new Cookie("onlyname",user.getUsername());
				cookie.setMaxAge(60*60);
				response.addCookie(cookie);
			}
			
			if("autoLogin".equals(autoLogin)){
				Cookie username = new Cookie("username",user.getUsername());
				Cookie password = new Cookie("password",user.getPassword());
				response.addCookie(username);
				response.addCookie(password);
			}

			// save user in session, jump to home page
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			// judge, if user is admin, jump to back stage system
			if("admin".equals(user.getUsername()) && "admin".equals(user.getRole())){
				response.sendRedirect(request.getContextPath()+"/admin/login/home.jsp");
			}
			// if user is normal, jump to home page
			else{
				response.sendRedirect(request.getContextPath()+"/index.jsp");
			}
		}
	}
	
	/**
	 * user qiut
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void quitUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// get session
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		// jump back to home page after delete
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	}
	
	/**
	 * find user
	 * @param user
	 * @return
	 */
	public User findUser(User user) {
		UserService us = new UserService();
		return us.findUser(user);
	}
	
	/**
	 * modify user information
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void modifyUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map<String,String[]> map = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, map);
			UserService us = new UserService();
			us.modifyUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("modMsg", e.getMessage());
			request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
			return;
		} 
		// modify success
		response.sendRedirect(request.getContextPath()+"/modifySuccess.jsp");
	}
	
	/**
	 * view order details
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void orderInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		
		UserService us = new UserService();
		try {
			Orders order = us.orderInfo(oid);
			request.setAttribute("order", order);
			request.getRequestDispatcher("/orderInfo.jsp").forward(request, response);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("orMsg", e.getMessage());
			request.getRequestDispatcher("/orderList.jsp").forward(request, response);
		}
	}
	
	/**
	 * view client's order
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uid = request.getParameter("uid");
		
		UserService us = new UserService();
		List<Orders> oList = null;
		try {
			oList = us.orderList(uid);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("ordMsg", e.getMessage());
			request.getRequestDispatcher("/myAccount.jsp").forward(request, response);
			return;
		}
		// query success
		request.setAttribute("oList", oList);
		request.setAttribute("oListSize", oList.size());
		request.getRequestDispatcher("/orderList.jsp").forward(request, response);
	}

}