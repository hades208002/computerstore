package com.computerstore.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Order Bean
 */
public class Orders {
	private String oid;
	private double money;//total count
	
	private String receiverAddress;
	private String receiverName;
	private String receiverPhone;
	// payment state, default 0,means not paid yet
	private int paystate;
	// this list collection is used to store orders
	private List<OrdersItem> list = new ArrayList<OrdersItem>();
	private String ordertime;
	// the user that the orders belong to
	//private String user_id;
	private User user;
	
	public List<OrdersItem> getList() {
		return list;
	}
	public void setList(OrdersItem ordersItem) {
		list.add(ordersItem);
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public int getPaystate() {
		return paystate;
	}
	public void setPaystate(int paystate) {
		this.paystate = paystate;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}