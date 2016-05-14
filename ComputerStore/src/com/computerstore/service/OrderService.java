package com.computerstore.service;

import java.sql.SQLException;
import java.util.List;

import com.computerstore.dao.OrderDao;
import com.computerstore.dao.ProductDao;
import com.computerstore.dbutils.DataSourceUtils;
import com.computerstore.domain.Orders;
import com.computerstore.domain.OrdersItem;
import com.computerstore.exception.ComputerstoreException;

public class OrderService {
	/**
	 * add order
	 * add data into order list , to orderitem, update product storage
	 * either all three transactions succeed or all fail simultaneously
	 * @param order
	 * @throws SQLException 
	 * @throws ComputerstoreException 
	 */
	public void addOrder(Orders order) throws SQLException, ComputerstoreException {
		// start transaction
		try {
			DataSourceUtils.startTransaction();
			
			// add data to order list
			OrderDao od = new OrderDao();
			od.addOrder(order);
			
			// add data to orderitem
			for (OrdersItem ordersItem : order.getList()) {
				od.addOrdersItem(ordersItem);
			}
			
			// update product storage
			ProductDao pd = new ProductDao();
			for(OrdersItem ordersItem : order.getList()) {
				//first parameter is primary key of product, the second is buy number of orderitem
				pd.updatePnum(ordersItem.getProduct().getPid(),ordersItem.getBuyCount());				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			// roll back when exception happens
			DataSourceUtils.rollback();
			throw e;
		} finally {
			// commit and release resource
			DataSourceUtils.commitAndRelease();
		}
	}
	
	/**
	 * update payment state
	 * @param r6_Order
	 * @throws ComputerstoreException 
	 */
	public void updatePayState(String r6_Order) throws ComputerstoreException {
		OrderDao od = new OrderDao();
		od.updatePayState(r6_Order);
	}
	
	/**
	 * show order
	 * @return
	 */
	public List<Orders> showOrder() {
		OrderDao od = new OrderDao();
		return od.showOrder();
	}
	
	/**
	 * show order by condition
	 * @param oid
	 * @param receiverName
	 * @return
	 */
	public List<Orders> showOrderByWhere(String oid, String receiverName) {
		OrderDao od = new OrderDao();
		return od.showOrderByWhere(oid,receiverName);
	}
	
	/**
	 *show order detail
	 * @param oid
	 * @return
	 */
	public Orders showOrderById(String oid) {
		OrderDao od = new OrderDao();
		return od.showOrderById(oid);
	}

}

