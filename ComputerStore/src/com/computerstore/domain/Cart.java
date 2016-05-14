package com.computerstore.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Shopping Cart
 * contains cartitems and totalcount
 */
public class Cart {
	// map collection to store all cart items
	private Map<String,CartItem> map = new HashMap<String,CartItem>();
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	
	// calculate totalcount
	public double getTotal() {
		double total = 0;
		if(map != null){
			for (String key : map.keySet()) {
				//将所有的小计相加即为合计
				total += map.get(key).getBuyTotal();
			}
		}
		return total;
	}
	
	// add product to cart
	// add cartitem
	public void addCart(CartItem item){
		// judge if product exists, if exists,update buy number
		String pid = item.getP().getPid();
		if(map.containsKey(pid)){
			// buy number + 1
			CartItem hisItem = map.get(pid);
			hisItem.setBuyNum(item.getBuyNum()+hisItem.getBuyNum());
		}
		// if not exist , add the item and set buy number to 1  
		else{
			map.put(pid, item);
		}
	}
	
	
}
