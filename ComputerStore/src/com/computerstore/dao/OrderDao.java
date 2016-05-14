package com.computerstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.computerstore.dbutils.DataSourceUtils;
import com.computerstore.domain.Orders;
import com.computerstore.domain.OrdersItem;
import com.computerstore.domain.Products;
import com.computerstore.domain.User;
import com.computerstore.exception.ComputerstoreException;

public class OrderDao {
	/**
	 * add data to order
	 * 
	 * @param order
	 * @throws ComputerstoreException
	 */
	public void addOrder(Orders order) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into orders values (?,?,?,?,?,?,?,?)";
		Object[] params = { order.getOid(), order.getMoney(),
				order.getReceiverAddress(), order.getReceiverName(),
				order.getReceiverPhone(), order.getPaystate(),
				order.getOrdertime(), order.getUser().getUid() };
		try {
			int row = runner.update(DataSourceUtils.getConnection(), sql,
					params);
			if (row != 1) {
				throw new ComputerstoreException("add order failed£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomaly£¡");
		}
	}

	/**
	 * add item to order
	 * 
	 * @param ordersItem
	 * @throws ComputerstoreException
	 */
	public void addOrdersItem(OrdersItem ordersItem) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner();
		String sql = "insert into ordersitem values (?,?,?,?,?)";
		Object[] params = { ordersItem.getItemId(),
				ordersItem.getOrder().getOid(),
				ordersItem.getProduct().getPid(), ordersItem.getBuyCount(),
				ordersItem.getSubtotal() };
		try {
			int row = runner.update(DataSourceUtils.getConnection(), sql,
					params);
			if (row != 1) {
				throw new ComputerstoreException("all item to order failed£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomaly£¡");
		}
	}
	/**
	 * @throws ComputerstoreException 
	 * update payment state
	 * @param r6_Order
	 * @throws  
	 */
	public void updatePayState(String r6_Order) throws ComputerstoreException   {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update orders set paystate = 1 where oid = ?";
		try {
			int row = runner.update(sql, r6_Order);
			if(row != 1){
				throw new ComputerstoreException("update payment state failed£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomaly£¡");
		}
	}
	/**
	 * view order
	 * @return
	 */
	public List<Orders> showOrder() {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders o,user u where o.user_id = u.uid";
		List<Orders> oList= new ArrayList<Orders>();
		try {
			List<Map<String,Object>> list = runner.query(sql, new MapListHandler());
			for (Map<String, Object> map : list) {
				User user = new User();
				BeanUtils.populate(user, map);
				
				Orders order = new Orders();
				BeanUtils.populate(order, map);
				
				order.setUser(user);
				oList.add(order);
			}
			
			return oList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("query failed£¡");
		}
	}
	/**
	 * query order according to condition
	 * @return
	 */
	public List<Orders> showOrderByWhere(String oid, String receiverName) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		StringBuffer sb = new StringBuffer();
		sb.append("select * from orders o,user u where o.user_id = u.uid ");
		List<Object> paramsList = new ArrayList<Object>();
		
		if(oid != null && !oid.trim().isEmpty()){
			sb.append(" and o.oid like ?");
			paramsList.add("%"+oid+"%");
		}
		if(receiverName != null && !receiverName.trim().isEmpty()){
			sb.append(" and receiverName = ?");
			paramsList.add(receiverName);
		}
		
		List<Orders> oList= new ArrayList<Orders>();
		try {
			List<Map<String, Object>> List = runner.query(sb.toString(), new MapListHandler(), paramsList.toArray());
			for (Map<String, Object> map : List) {
				User user = new User();
				BeanUtils.populate(user, map);
				
				Orders order = new Orders();
				BeanUtils.populate(order, map);
				
				order.setUser(user);
				oList.add(order);
			}
			return oList;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("query failed£¡");
		}
	}
	/**
	 * show order details
	 * @param oid
	 * @return
	 */
	public Orders showOrderById(String oid) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders o join user u on o.user_id = u.uid and o.oid = ? join ordersitem os on os.oid = o.oid join products p on p.pid = os.pid";
		try {
			// order list 
			List<Map<String,Object>> list = runner.query(sql, new MapListHandler(), oid);
			Orders order  = new Orders();
			
			for (Map<String, Object> map : list) {
				// client
				User user = new User();
				BeanUtils.populate(user, map);
				
				// items
				Products product = new Products();
				BeanUtils.populate(product, map);
				
				// order
				OrdersItem ol = new OrdersItem();
				BeanUtils.populate(ol, map);
				// encapsulate items to order
				ol.setProduct(product);
				
				// encapsulate order
				BeanUtils.populate(order, map);
				order.setUser(user);
				order.setList(ol);
			}
			return order;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("query failed£¡");
		}
	}

}
