package com.computerstore.service;

import java.util.List;

import com.computerstore.dao.ProductDao;
import com.computerstore.dbutils.UUIDUtils;
import com.computerstore.domain.PageBean;
import com.computerstore.domain.Products;
import com.computerstore.exception.ComputerstoreException;

public class ProductService {

	/**
	 * use ProductDao, addProduct()
	 * @param products
	 * @throws ComputerstoreException 
	 */
	public void addProduct(Products products) throws ComputerstoreException {
		
		// product primary key is varchar, we maintain the primary key
		products.setPid(UUIDUtils.getUUID());
		// haven't uploaded images yet, so we left Imgurl null
		// products.setImgurl(null);
		
		// use ProductDao, add product 
		ProductDao pd = new ProductDao();
		pd.addProduct(products);
	}
	
	/**
	 * query all products
	 * @return 
	 * @throws ComputerstoreException 
	 */
	public List<Products> findAll() throws ComputerstoreException {
		
		// use ProductDao, findAll()
		return new ProductDao().findAll();
	}
	
	/**
	 * get product by id
	 * @param pid
	 * @return
	 * @throws ComputerstoreException
	 */
	public Products getProductById(String pid) throws ComputerstoreException {
		// use ProductDao getProductByID()
		return new ProductDao().getProductById(pid);
	}
	
	/**
	 * edit product
	 * @param products
	 * @throws ComputerstoreException
	 */
	public void editProduct(Products products) throws ComputerstoreException {
		// use ProductDao to update changes
		new ProductDao().editProduct(products);
	}
	
	/**
	 * get products by condition
	 * @param pid
	 * @param category
	 * @param pname
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws ComputerstoreException
	 */
	public List<Products> findProductByWhere(String pid, String category,String cputype,String gputype,String memtype,
			String pname, String minprice, String maxprice) throws ComputerstoreException {
		return new ProductDao().findProductByWhere(pid,category,cputype,gputype,memtype,pname,minprice,maxprice);
	}
	
	/**
	 * delete product by id
	 * @param pid
	 * @return
	 * @throws ComputerstoreException
	 */
	public int delById(String pid) throws ComputerstoreException {
		return new ProductDao().delById(pid);
	}
	
	/**
	 *  get current page information and page number
	 * @param pageSize
	 * @param pageCode
	 * @return
	 * @throws ComputerstoreException 
	 */
	public PageBean<Products> showProductByPage(int pageSize, int pageCode) throws ComputerstoreException {
		return new ProductDao().showProductByPage(pageSize,pageCode);
	}
	
	/**
	 * get page information by category
	 * @param category
	 * @param pageSize
	 * @param pageCode
	 * @return
	 * @throws ComputerstoreException
	 */
	public PageBean<Products> showProductByPage(String category,String cputype,String gputype,String memtype, int pageSize,
			int pageCode) throws ComputerstoreException {
			return new ProductDao().showProductByPage(category,cputype,gputype,memtype,pageSize,pageCode);
	}
	
	/**
	 * get product by id
	 * @param pid
	 * @return
	 * @throws ComputerstoreException
	 */
	public Products findById(String pid) throws ComputerstoreException {
		
		return new ProductDao().findById(pid);
	}
	
	/**
	 * get value from search box
	 * @param pname
	 * @return
	 * @throws ComputerstoreException 
	 */
	public List<Products> findBySearch(String pname) throws ComputerstoreException {
		
		return new ProductDao().findBySearch(pname);
	}

	public PageBean<Products> showProductByPage(int pageSize, int pageCode,
			String pid, String category, String cputype,String gputype,String memtype,String pname, String minprice,
			String maxprice) throws ComputerstoreException {
		
		return new ProductDao().showProductByPage(pageSize, pageCode, pid,category,cputype,gputype,memtype,pname, minprice,
				maxprice);
	}


}
