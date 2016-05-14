package com.computerstore.service;

import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.computerstore.dao.UserDao;
import com.computerstore.dbutils.DataSourceUtils;
import com.computerstore.dbutils.UUIDUtils;
import com.computerstore.domain.User;
import com.computerstore.domain.Orders;
import com.computerstore.exception.ComputerstoreException;

public class UserService {
	
	/**
	 * register
	 * @param user
	 * @throws ComputerstoreException 
	 */
	public void register(User user) throws ComputerstoreException {
		UserDao ud = new UserDao();
		user.setUid(UUIDUtils.getUUID());
		user.setRole("normal");
		// start transaction
		try {
			DataSourceUtils.startTransaction();
			ud.register(user);
			
		} catch (SQLException e1) {
			e1.printStackTrace();
			// if register failed , roll back
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new ComputerstoreException("Network anomalies, registration failed!");
		} catch (ComputerstoreException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw e;
		} finally {
			// commit transaction and close resources
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * login
	 * @param user
	 * @return
	 */
	public User login(User user) {
		UserDao ud = new UserDao();
		return ud.login(user);
	}
	
	/**
	 * find user
	 * @param user
	 * @return
	 */
	public User findUser(User user) {
		return new UserDao().login(user);
	}
	
	/**
	 * modify user
	 * @param user
	 * @throws BookstoreException 
	 * @throws SQLException 
	 */
	public void modifyUser(User user) throws SQLException, ComputerstoreException {
		UserDao ud = new UserDao();
		ud.modifyUser(user);
	}
	
	/**
	 * view order list
	 * @param uid
	 * @return
	 * @throws ComputerstoreException 
	 */
	public List<Orders> orderList(String uid) throws ComputerstoreException {
		UserDao ud = new UserDao();
		return ud.orderList(uid);
	}
	
	/**
	 * view order information 
	 * @param oid
	 * @return
	 * @throws ComputerstoreException 
	 */
	public Orders orderInfo(String oid) throws ComputerstoreException {
		UserDao ud = new UserDao();
		return ud.orderInfo(oid);
	}

}
