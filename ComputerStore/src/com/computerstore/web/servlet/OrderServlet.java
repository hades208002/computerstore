package com.computerstore.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.computerstore.dbutils.PaymentUtil;
import com.computerstore.dbutils.UUIDUtils;
import com.computerstore.domain.Cart;
import com.computerstore.domain.CartItem;
import com.computerstore.domain.Orders;
import com.computerstore.domain.OrdersItem;
import com.computerstore.domain.User;
import com.computerstore.exception.ComputerstoreException;
import com.computerstore.service.OrderService;
import com.computerstore.dbutils.MailUtils;

public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 6007577430399276068L;
	
	/**
	 * show order detail
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showOrderById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		
		OrderService os = new OrderService();
		Orders order = os.showOrderById(oid);
		
		request.setAttribute("order", order);
		request.getRequestDispatcher("/admin/orders/view.jsp").forward(request, response);
	}
	/**
	 * show order by condition
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showOrderByWhere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		String receiverName = request.getParameter("receiverName");
		
		OrderService os = new OrderService();
		List<Orders> oList = os.showOrderByWhere(oid,receiverName);
		
		request.setAttribute("oid", oid);
		request.setAttribute("receiverName", receiverName);
		request.setAttribute("oList", oList);
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
	}
	
	/**
	 * show order
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		OrderService os = new OrderService();
		List<Orders> oList  = os.showOrder();
		
		request.setAttribute("oList", oList);
		request.getRequestDispatcher("/admin/orders/list.jsp").forward(request, response);
	}

	/**
	 * after send order, direct to initial payment page 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void initPay(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		String smoney = request.getParameter("money");
		
		request.setAttribute("oid", oid);
		request.setAttribute("smoney", smoney);
		request.getRequestDispatcher("/pay.jsp").forward(request, response);
	}
	/**
	 * add order
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void addOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// add an order
		Orders order = new Orders();
		// get cart from session
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		// get the client that owns the order from session
		User user = (User) request.getSession().getAttribute("user");
		
		// set primary key to order
		order.setOid(UUIDUtils.getUUID());
		// set total count to order
		order.setMoney(cart.getTotal());
		// get cartitem from cart and put it into orderitem and order
		for ( CartItem  cartItem : cart.getMap().values()) {
			// add orderitem
			OrdersItem ordersItem = new OrdersItem();
			// set value to orderitem
			ordersItem.setItemId(UUIDUtils.getUUID());
			ordersItem.setOrder(order);
			ordersItem.setProduct(cartItem.getP());
			ordersItem.setBuyCount(cartItem.getBuyNum());
			ordersItem.setSubtotal(cartItem.getBuyTotal());
			
			// add orderitem to order
			order.setList(ordersItem);
		}
		// set pay state as null, not payed yet
		order.setPaystate(0);
		// set pay time as null, database automatically set as current time
		order.setOrdertime(null);
		order.setReceiverAddress(request.getParameter("receiverAddress"));
		order.setReceiverName(request.getParameter("receiverName"));
		order.setReceiverPhone(request.getParameter("receiverPhone"));
		// set the client that owns the order
		order.setUser(user);
		
		// send order to service
		OrderService os = new OrderService();
		
		try {
			os.addOrder(order);
			
			//send order email
			//String emailMsg="This is an order confirmation email";
			//String emailMsg = "This is an order confirmation email,please click <a href=¡®orderCheck.jsp¡¯>here</a> to check your order! ";
			StringBuffer emailMsg = new StringBuffer();
		    emailMsg.append("<h2><font color=red>You have submitted an order and listed below is the detailed order:</font></h2>");
		    emailMsg.append("<hr>");
		    emailMsg.append("<i>Order Information</i>");
		    emailMsg.append("<table border='1'><tr><td>OrderID</td><td>OrderTime</td><td>ReceiverName</td><td>Address</td><td>Contact</td><td>ClientID</td><td>TotalCost</td><td>PayState</td></tr>" +
		    		                          "<tr><% <td> order.getOid(); </td> %><td>order.getOrdertime()</td><td>order.getReceiverName()</td><td>order.getReceiverAddress</td><td>order.getReceiverPhone()</td><td>order.getUser()</td><td>order.getMoney()</td><td>order.getPaystate()</td></tr></table>");
			String email = user.getEmail();
			MailUtils.sendMail(email, emailMsg.toString());
			
			// add succeed, clear shopping cart and back to cart page
			cart.getMap().clear();
			response.sendRedirect(request.getContextPath()+"/createOrderSuccess.jsp");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("orderMsg", e.getMessage());
			request.getRequestDispatcher("/order.jsp").forward(request, response);
			return;
		} 
		
	}
}
