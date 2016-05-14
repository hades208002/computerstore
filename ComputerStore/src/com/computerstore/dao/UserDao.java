package com.computerstore.dao;

/*register
 *login
 *modify user information 
 *query user's orders
 *query oderItems
 */

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.computerstore.dbutils.DataSourceUtils;
import com.computerstore.domain.User;
import com.computerstore.domain.Products;
import com.computerstore.domain.Orders;
import com.computerstore.domain.OrdersItem;
import com.computerstore.exception.ComputerstoreException;

public class UserDao {
	
	/**
	 * register
	 * @param user
	 * @throws SQLException 
	 * @throws ComputerstoreException 
	 */
	public void register(User user) throws SQLException, ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into user values (?,?,?,?,?,?,?,?,?)";
		Object[] params = { user.getUid(), user.getUsername(),
				user.getPassword(), user.getGender(), user.getEmail(),
				user.getTelephone(), user.getIntroduce(),
				 user.getRole(), user.getRegistTime() };
		int row = runner.update(sql, params);
		if(row != 1){
			throw new ComputerstoreException("Wrong user name or password , fail to register");
		}
	}
	
	/**
	 * login
	 * @param user
	 * @return
	 */
	public User login(User user) {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		try {
			return runner.query(sql, new BeanHandler<User>(User.class),
					user.getUsername(), user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Fail to find the user！");
		}
	}
	
	/**
	 * modify user information
	 * @param user
	 * @throws SQLException 
	 * @throws ComputerstoreException 
	 */
	public void modifyUser(User user) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update user set password = ?,gender = ?,telephone = ?  where uid = ?";
		Object[] params = {user.getPassword(),user.getGender(),user.getTelephone(),user.getUid()};
		
		try {
			int row = runner.update(sql, params);
			if(row != 1){
				throw new ComputerstoreException("修改用户信息失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("网络异常！");
		}
		
	}
	
	/**
	 * view order list
	 * @param uid
	 * @return
	 * @throws ComputerstoreException 
	 */
	public List<Orders> orderList(String uid) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from orders where user_id = ?";
		List<Orders> oList = null;
		try {
			oList =  runner.query(sql, new BeanListHandler<Orders>(Orders.class), uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("网络异常！");
		}
		return oList;
	}
	/**
	 * view order information
	 * @param oid
	 * @return
	 * @throws ComputerstoreException 
	 */
	public Orders orderInfo(String oid) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// find order
		String orderSql = "select * from orders where oid = ?";
		// find orderitems in the order
		String oipSql = "select * from products p,ordersitem os where p.pid = os.pid and os.oid = ?";
		
		// order information
		Orders order = null;
		// store results in map, store map in list
		List<Map<String,Object>> list = null;
		try {
			order = runner.query(orderSql, new BeanHandler<Orders>(Orders.class), oid);
			list = runner.query(oipSql, new MapListHandler(), oid);
			
			// store product in orderitem , store orderitem in order
			for (Map<String, Object> map : list) {
				
				try {
					Products product = new Products();
					// store product information in productBean
					BeanUtils.populate(product, map);
					
					OrdersItem oi = new OrdersItem();
					// store orderitem in orderitemBean
					BeanUtils.populate(oi, map);
					
					// add product into oderitem将商品存到订单项中
					oi.setProduct(product);
					// add orderitem into orders
					order.setList(oi);
					
				} catch (Exception e) {
					e.printStackTrace();
					throw new RuntimeException("data encapsulation failed！");
				} 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomalies！");
		}
		// return order
		return order;
	}
	
}