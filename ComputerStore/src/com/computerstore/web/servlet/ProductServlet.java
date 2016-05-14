package com.computerstore.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;

import com.computerstore.domain.PageBean;
import com.computerstore.domain.Products;
import com.computerstore.exception.ComputerstoreException;
import com.computerstore.service.ProductService;

public class ProductServlet extends BaseServlet {

	private static final long serialVersionUID = 5469189693411016843L;

	/**
	 * get result by search box
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findBySearch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		// get parameters
		String pname = request.getParameter("pname");
		if (pname == null || pname.trim().isEmpty()) {
			return;
		}
		// System.out.println("user input£º"+pname);
		// query data in database
		ProductService ps = new ProductService();
		List<Products> list = null;
		try {
			list = ps.findBySearch(pname);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			throw new RuntimeException("Abnormal run error£¡");
		}
		if (list == null || list.size() == 0) {
			return;
		}

		// convert to json format
		// JSONObject jsonObject = JSONObject.fromObject(list);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("list", list);
		// System.out.println(jsonObject);
		response.getWriter().print(jsonObject.toString());

	}

	/**
	 * add product
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 1. get parameter
		Map<String, String[]> map = request.getParameterMap();
		// 1.1 save it into JavaBean
		Products products = new Products();
		try {
			BeanUtils.populate(products, map);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("ap_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/add.jsp").forward(
					request, response);
			return;
		}

		// 2. data validation

		// 3. use service
		ProductService ps = new ProductService();
		try {
			ps.addProduct(products);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("ap_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/add.jsp").forward(
					request, response);
			return;
		}
		// 4. add succeed, redirect
		response.sendRedirect(request.getContextPath()
				+ "/product?method=findAll");
		// System.out.println("ok");
	}

	/**
	 * find all products
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findAll(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1. service findAll()
		ProductService ps = new ProductService();
		List<Products> pList = null;
		try {
			pList = ps.findAll();
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("fp_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/add.jsp").forward(
					request, response);
			return;
		}
		// 2. show result
		request.setAttribute("pList", pList);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(
				request, response);
	}

	/**
	 * initial update
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initUpdate(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get product id
		String pid = request.getParameter("pid");
		if (pid == null) {
			request.setAttribute("p_msg", "product doesn't exist£¡");
			request.getRequestDispatcher("/admin/products/list.jsp").forward(
					request, response);
			return;
		}

		// service, get the product
		ProductService ps = new ProductService();
		Products p = null;
		try {
			p = ps.getProductById(pid);
			if (p == null) {
				request.setAttribute("p_msg", "product doesn't exist£¡");
				request.getRequestDispatcher("/admin/products/list.jsp")
						.forward(request, response);
				return;
			}
			request.setAttribute("p", p);
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(
					request, response);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("p_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/list.jsp").forward(
					request, response);
			return;
		}
	}

	/**
	 * commit the edited product information
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void editProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// set encoding
		request.setCharacterEncoding("utf-8");

		// 1. receive parameter
		Map<String, String[]> map = request.getParameterMap();
		// 1.1 save it into JavaBean
		Products products = new Products();
		try {
			BeanUtils.populate(products, map);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("p", products);
			request.setAttribute("ap_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(
					request, response);
			return;
		}

		// 2. data validation

		// 3. service
		ProductService ps = new ProductService();
		try {
			ps.editProduct(products);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("p", products);
			request.setAttribute("ap_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/edit.jsp").forward(
					request, response);
			return;
		}
		// 4. update succeed, redirect
		response.sendRedirect(request.getContextPath()
				+ "/product?method=showProductByPage2");
	}

	/**
	 * find product by condition
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findProductByWhere(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// receive parameters
		String pid = request.getParameter("pid");
		String category = request.getParameter("category");
		String cputype = request.getParameter("cputype");
		String gputype = request.getParameter("gputype");
		String memtype= request.getParameter("memtype");
		String pname = request.getParameter("pname");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");

		// service, findProductByWhere()
		ProductService ps = new ProductService();
		List<Products> pList = null;
		try {
			pList = ps.findProductByWhere(pid, category,cputype,gputype,memtype, pname, minprice,
					maxprice);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("fp_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/add.jsp").forward(
					request, response);
			return;
		}
		// 2. show query result
		request.setAttribute("pList", pList);
		request.getRequestDispatcher(
				"/product?method=showProductByPage3&pid="+pid+"&category=" + category +"&cputype=" + cputype + "&gputype=" + gputype +"&memtype=" + memtype 
				+"&pname=" + pname+"&minprice="+minprice+"&maxprice="+maxprice).forward(request, response);
	}
	
	/**
	 * page by categories
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showProductByPage3(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// receive parameters
		String pid = request.getParameter("pid");
		String category = request.getParameter("category");
		String cputype = request.getParameter("cputype");
		String gputype = request.getParameter("gputype");
		String memtype = request.getParameter("memtype");
		String pname = request.getParameter("pname");
		String minprice = request.getParameter("minprice");
		String maxprice = request.getParameter("maxprice");
		
		// number of records showed on each page
		int pageSize = 10;
		// current page number
		String pc = request.getParameter("pc");
		int pageCode = getPagecodeByPc(pc);

		// send parameters, return a Bean
		ProductService ps = new ProductService();
		PageBean<Products> page = null;
		try {
			page = ps.showProductByPage(pageSize, pageCode, pid, category, cputype,gputype,memtype,pname, minprice,
					maxprice);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
		}
		
		// get required url and parameters
		String url = getUrl(request, pid, category, pname, minprice, maxprice);
		page.setUrl(url);

		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(
				request, response);
	}
	
	/**
	 * get url enhanced
	 */
	public String getUrl(HttpServletRequest request, String pid, String category, String pname, String minprice,
			String maxprice) {
		String path1 = request.getRequestURI();
		// get session, record category
		HttpSession session  = request.getSession();
		String params = (String) session.getAttribute("params");
		// if session not null, exists parameter in session
		
		// splice parameter
		StringBuilder sb = new StringBuilder();
		if(pid != null && !pid.trim().isEmpty()){
			sb.append("&pid="+pid);
		}
		if(category != null && !category.trim().isEmpty()){
			sb.append("&category="+category);
		}
		if(pname != null && !pname.trim().isEmpty()){
			sb.append("&pname="+pname);
		}
		if(minprice != null && !minprice.trim().isEmpty()){
			sb.append("&minprice="+minprice);
		}
		if(maxprice != null && !maxprice.trim().isEmpty()){
			sb.append("&maxprice="+maxprice);
		}
		sb.append("&method=showProductByPage3");
		
		String newParams = sb.toString();
		if(params != null){
			// if old parameters same as new ones, let it be
			if(newParams.equals(params)){
				return path1 + "?book" + params;
			}else{
				// if different, set new parameters, update data in session
				session.setAttribute("params", newParams);
				return path1 + "?book" + newParams;
			}
		}else{
			// set session
			session.setAttribute("params", sb.toString());
			// return new url
			return path1 + "?book" + newParams;
		}
	}

	/**
	 * remove product by pid
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get pid
		String pid = request.getParameter("pid");
		// service
		ProductService ps = new ProductService();
		try {
			int row = ps.delById(pid);
			if (row != 1) {
				request.setAttribute("del_msg", "remove failed£¡");
				request.getRequestDispatcher("/admin/products/list.jsp")
						.forward(request, response);
				return;
			}
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			request.setAttribute("del_msg", e.getMessage());
			request.getRequestDispatcher("/admin/products/list.jsp").forward(
					request, response);
			return;
		}
		response.sendRedirect(request.getContextPath()
				+ "/product?method=findAll");
	}

	/**
	 * show products by current page
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showProductByPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// set record number showed on every page
		int pageSize = 4;
		// current page number
		String pc = request.getParameter("pc");
		int pageCode = getPagecodeByPc(pc);

		// send parameters, return a Bean
		ProductService ps = new ProductService();
		PageBean<Products> page = null;
		try {
			page = ps.showProductByPage(pageSize, pageCode);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
		}

		// rend result into request, send to showing page
		request.setAttribute("page", page);
		request.getRequestDispatcher("/productList.jsp").forward(request,
				response);
	}

	/**
	 * show product by current page
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showProductByPage1(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// set record number of each page
		int pageSize = 10;
		//current page number
		String pc = request.getParameter("pc");
		int pageCode = getPagecodeByPc(pc);

		// send parameters, return a Bean
		ProductService ps = new ProductService();
		PageBean<Products> page = null;
		try {
			page = ps.showProductByPage(pageSize, pageCode);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
		}

		// get required url and parameters
		page.setUrl(getUrl(request));

		request.setAttribute("page", page);
		request.getRequestDispatcher("/admin/products/list.jsp").forward(
				request, response);
	}

	/**
	 * paging with search conditon
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showProductByPage2(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		// get request encoding set
		String category = request.getParameter("category");
		if (category != null) {
			category = new String(category.getBytes("iso-8859-1"), "utf-8");
		}
		
		String cputype = request.getParameter("cputype");
		if (cputype != null) {
			cputype = new String(cputype.getBytes("iso-8859-1"), "utf-8");
		}
		String gputype = request.getParameter("gputype");
		if (gputype != null) {
			gputype = new String(gputype.getBytes("iso-8859-1"), "utf-8");
		}
		String memtype = request.getParameter("memtype");
		if (memtype != null) {
			memtype = new String(memtype.getBytes("iso-8859-1"), "utf-8");
		}

		// set record number on each page
		int pageSize = 4;
		// current page number
		String pc = request.getParameter("pc");
		int pageCode = getPagecodeByPc(pc);

		// send parameters and return a Bean
		ProductService ps = new ProductService();
		PageBean<Products> page = null;
		try {
			page = ps.showProductByPage(category, cputype, gputype, memtype, pageSize, pageCode);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
		}

		// get required url and parameters
		page.setUrl(getUrl(request));

		// put result into request and send to showing page
		request.setAttribute("page", page);
		request.getRequestDispatcher("/productList.jsp").forward(request,
				response);
	}

	/**
	 * get url
	 */
	public String getUrl(HttpServletRequest request) {
		String path1 = request.getRequestURI();
		String path2 = request.getQueryString();
		if (path2 != null && path2.contains("&pc=")) {
			path2 = path2.substring(0, path2.lastIndexOf("&pc="));
		}
		return path1 + "?" + path2;
	}

	/**
	 * get current page number
	 * 
	 * @param pc
	 * @return
	 */
	public int getPagecodeByPc(String pc) {
		if (pc == null) {
			pc = new String("1");
		}
		return Integer.parseInt(pc);
	}

	/**
	 * find product by pid
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 * @throws ComputerstoreException
	 */
	public Products findByid(HttpServletRequest request)
			throws ServletException, IOException, ComputerstoreException {
		String pid = request.getParameter("pid");
		ProductService ps = new ProductService();
		return ps.findById(pid);
	}

	/**
	 * show product information chosen
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showProduct(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Products p = null;
		// get product
		try {
			p = this.findByid(request);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
		}
		request.setAttribute("p", p);
		request.getRequestDispatcher("/productInfo.jsp").forward(request,
				response);
	}
}
