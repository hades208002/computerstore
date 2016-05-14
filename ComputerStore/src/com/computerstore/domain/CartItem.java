package com.computerstore.domain;

/**
 * Cart items
 */
public class CartItem {
	// product
	private Products p;
	// buy number
	private int buyNum;
	// buytotal= number*price
	private double buyTotal;
	
	public Products getP() {
		return p;
	}
	public void setP(Products p) {
		this.p = p;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	// calculate buytotal
	public double getBuyTotal() {
		if(p!=null){
			buyTotal = p.getPrice() * buyNum;
		}
		return buyTotal;
	}
	
	
}

