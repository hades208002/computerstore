package com.computerstore.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerstore.exception.ComputerstoreException;
/**
 * service base servlet
 * @author carlo
 */
public class BaseServlet extends HttpServlet{

	private static final long serialVersionUID = -4497067185593767554L;
	
	/**
	 * base servlet of all 
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		
		/*
		 * 2. receive parameter , the parameter is the function name passed by user
		 * 	  get  subclass servlet method by reflection, if this method exists(),calls through invoke 
		 */
		String method = request.getParameter("method");
		
		// judge if method is null 
		if(method == null){
			throw new RuntimeException("Method can't be null£¡");
		}
		
		Method m = null;
		try {
			// get method object 
			m = this.getClass().getMethod(method, HttpServletRequest.class,HttpServletResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(method+"method doesn't exist£¡");
		}
		
		try {
			// call the method
			m.invoke(this, request,response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(method+"Method internal error£¡");
		} 
	}
	
}