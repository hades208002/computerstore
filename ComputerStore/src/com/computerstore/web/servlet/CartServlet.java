package com.computerstore.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.computerstore.domain.Cart;
import com.computerstore.domain.CartItem;
import com.computerstore.domain.Products;
import com.computerstore.exception.ComputerstoreException;
import com.computerstore.service.ProductService;
/**
 * Cart servlet
 * @author carlo
 */
public class CartServlet extends BaseServlet {

	private static final long serialVersionUID = 2205241901877099734L;
	
	/**
	 * get product by id
	 * @param request
	 * @return
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
	 * update buy number
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void newBuyNum(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// get product id
		String pid = request.getParameter("pid");
		// get number
		int bNum = Integer.parseInt(request.getParameter("bBum"));
		
		// get cart in session
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		
		// get map collection , update catitem number
		cart.getMap().get(pid).setBuyNum(bNum);
		// update succeed , jump to cart page
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}
	
	/**
	 *remove product from cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void removeItemFromCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		// get product id to be moved
		String pid = request.getParameter("pid");
		// get cart from session
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// remove item from cart in map collection
		cart.getMap().remove(pid);
		
		// remove succeed, jump to cart page
		//response.sendRedirect(request.getContextPath()+"/cart.jsp");
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}
	
	/**
	 * add product into cart
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addToCart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get product by id
		Products p = null;
		try {
			 p = this.findByid(request);
		} catch (ComputerstoreException e) {
			e.printStackTrace();
		}
		// ´´create a cartitem
		CartItem item = new CartItem();
		// add product to cartitem
		item.setP(p);
		// set default buy number as 1
		item.setBuyNum(1);
		// get cart from session, not a new cart
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		// if no cart exist, create one
		if(cart == null){
			cart = new Cart();
		}
		cart.addCart(item);
		
		//put cart into session
		session.setAttribute("cart", cart);
		// add succeed, jump to cart page and show products 
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
		
	}

}
