package com.computerstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.computerstore.dbutils.DataSourceUtils;
import com.computerstore.domain.PageBean;
import com.computerstore.domain.Products;
import com.computerstore.exception.ComputerstoreException;

public class ProductDao {

	/**
	 * add data into database
	 * @param products
	 * @throws ComputerstoreException
	 */
	public void addProduct(Products products) throws ComputerstoreException {
		// 1. Create QueryRunner object, call the update method to add product
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		// 2.insert sql 
		String sql = "insert into products values(?,?,?,?,?,?,?,?,?,?,?)";

		// 3. encapsulate data into Object[]
		Object[] params = { products.getPid(), products.getPname(),
				products.getPrice(), products.getCategory(),products.getCputype(),products.getGputype(),products.getMemtype(),
				products.getPnum(), products.getImgurl(),
				products.getDescription(),1};

		// 4. insert
		try {
			int i = runner.update(sql, params);
			if (i != 1)
				throw new ComputerstoreException("insert product failed£¡");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("Network anomalies, please try again later£¡");
		}
	}

	/**
	 * find all products
	 * @return
	 * @throws ComputerstoreException
	 */
	public List<Products> findAll() throws ComputerstoreException {
		// Create QueryRunner object
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// sql
		String sql = "select * from products where tag = ?";
		List<Products> list = null;
		// do sql
		try {
			list = runner.query(sql, new BeanListHandler<Products>(Products.class),1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("Network anomalies");
		}
		return list;
	}

	/**
	 * get product information by id
	 * @param pid
	 * @return
	 * @throws ComputerstoreException
	 */
	public Products getProductById(String pid) throws ComputerstoreException {
		// create QueryRunner object
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// sql
		String sql = "select * from products where pid = ? and tag = ?";
		Products p = null;
		// do sql
		try {
			p = runner.query(sql, new BeanHandler<Products>(Products.class),pid,1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("Network anomalies");
		}
		return p;
	}

	/**
	 * update changes to product
	 * @param products
	 * @throws ComputerstoreException
	 */
	public void editProduct(Products products) throws ComputerstoreException {
		// 1. create QueryRunner object£¬update()
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		// 2. sql
		String sql = "update products set pname = ?,price = ?,category = ?,cputype = ?,gputype = ? ,memtype = ?,pnum = ?,imgurl = ?,description = ? where pid = ?";

		// 3. encapsulate data into Object[]
		Object[] params = { products.getPname(), products.getPrice(),
				products.getCategory(),products.getCputype(),products.getGputype(),products.getMemtype(), products.getPnum(),
				products.getImgurl(), products.getDescription(),products.getPid() };

		// 4. do sql
		try {
			int i = runner.update(sql, params);
			if (i != 1)
				throw new ComputerstoreException("product edit failed");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomalies, please try again later£¡");
		}
	}

	public List<Products> findProductByWhere(String pid, String category,String cputype,String gputype,String memtype,
			String pname, String minprice, String maxprice) throws ComputerstoreException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// Create a collection to store the matching data
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		// splice sql
		StringBuilder sb = new StringBuilder("select * from products where 1=1 and tag = ? ");
		
		if(pid != null && !pid.trim().isEmpty()){
			sb.append(" and pid like ?");
			params.add("%"+pid+"%");
		}
		if(category != null && !category.trim().isEmpty()){
			sb.append(" and category = ?");
			params.add(category);
		}
		if(cputype != null && !cputype.trim().isEmpty()){
			sb.append(" and cputype = ?");
			params.add(cputype);
		}
		if(gputype != null && !gputype.trim().isEmpty()){
			sb.append(" and gputype = ?");
			params.add(gputype);
		}
		if(memtype != null && !memtype.trim().isEmpty()){
			sb.append(" and memtype = ?");
			params.add(memtype);
		}
		if(pname != null && !pname.trim().isEmpty()){
			sb.append(" and pname = ?");
			params.add(pname);
		}
		if(minprice != null && !minprice.trim().isEmpty()){
			sb.append(" and price > ?");
			params.add(minprice);
		}
		if(maxprice != null && !maxprice.trim().isEmpty()){
			sb.append(" and price < ?");
			params.add(maxprice);
		}
		
		List<Products> list = null;
		try {
			list = runner.query(sb.toString(),new BeanListHandler<Products>(Products.class),params.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomalies£¡");
		}
		return list;
	}

	public int delById(String pid) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		//set record to null
		String sql = "update products set tag = ? where pid = ?";
		Object[] params = {0,pid};
		int row = 0;
		try {
			row = runner.update(sql,params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomalies£¡");
		}
		return row;
	}
	
	/**
	 * get current page information and page number
	 * @param pageSize
	 * @param pageCode
	 * @return
	 * @throws ComputerstoreException 
	 */
	public PageBean<Products> showProductByPage(int pageSize, int pageCode) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// create a PageBean object to encapsulate data
		PageBean<Products> page = new PageBean<Products>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		// total records sql
		String countSql = "select count(*) from products where tag = ?";
		try {
			long totalCount = (Long) runner.query(countSql,new ScalarHandler(), 1);
			page.setTotalCount((int)totalCount);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("countSql error");
		}
		//show data on every page sql
		String showSql = "select * from products where tag = ? limit ?,?";
		List<Products> pList = null;
		try {
			pList = runner.query(showSql,new BeanListHandler<Products>(Products.class), 1,(pageCode-1)*pageSize,pageSize);
			page.setpList(pList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("showSql error");
		}
		return page;
	}
	
	
	/**
	 * get page information by categories
	 * @param category
	 * @param cputype
	 * @param gputype
	 * @param memtype
	 * @param pageSize
	 * @param pageCode
	 * @return
	 * @throws ComputerstoreException
	 */
	public PageBean<Products> showProductByPage(String category, String cputype,String gputype,String memtype,int pageSize,
			int pageCode) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// create a PageBean object to encapsulate data
		PageBean<Products> page = new PageBean<Products>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//create a list collection to encapsulate data
		List<Object> showParams = new ArrayList<Object>();
		List<Object> countParams = new ArrayList<Object>();
		showParams.add(1);
		countParams.add(1);
		
		String countSql = null;
		String showSql = null;
		if(category != null){
			// total records sql
			countSql = "select count(*) from products where tag = ? and category = ?";
			// show data at every page sql
			showSql = "select * from products where tag = ? and category = ? limit ?,?";
			countParams.add(category);
			showParams.add(category);
		}
		else if(cputype!=null){
			countSql = "select count(*) from products where tag = ? and cputype = ?";
			// every page data sql
			showSql = "select * from products where tag = ? and cputype = ? limit ?,?";
			countParams.add(cputype);
			showParams.add(cputype);
		}
		else if(gputype!=null){
			countSql = "select count(*) from products where tag = ? and gputype = ?";
			// every page data sql
			showSql = "select * from products where tag = ? and gputype = ? limit ?,?";
			countParams.add(gputype);
			showParams.add(gputype);
		}
		else if(memtype!=null){
			countSql = "select count(*) from products where tag = ? and memtype = ?";
			// every page data sql
			showSql = "select * from products where tag = ? and memtype = ? limit ?,?";
			countParams.add(memtype);
			showParams.add(memtype);
		}
		else {
			// total records sql
			countSql = "select count(*) from products where tag = ?";
			// every page data sql
			showSql = "select * from products where tag = ? limit ?,?";
		}
		showParams.add((pageCode-1)*pageSize);
		showParams.add(pageSize);
		
		
		try {
			long totalCount = (Long) runner.query(countSql,new ScalarHandler(), countParams.toArray());
			page.setTotalCount((int)totalCount);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("countSql error");
		}
		
		List<Products> pList = null;
		try {
			pList = runner.query(showSql,new BeanListHandler<Products>(Products.class), showParams.toArray());
			page.setpList(pList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("showSql error");
		}
		return page;
	}
	
	/**
	 * get product by primary key (id)
	 * @param pid
	 * @return
	 * @throws ComputerstoreException
	 */
	public Products findById(String pid) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from products where pid = ?";
		Products p = null;
		try {
			p = runner.query(sql, new BeanHandler(Products.class), pid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("sql error 4");
		}
		return p;
	}
	
	/**
	 * find product by search box
	 * @param pname
	 * @return
	 * @throws ComputerstoreException
	 */
	public List<Products> findBySearch(String pname) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		
		String sql = "select * from products where pname like ?";
		List<Products> list = null;
		try {
			list = runner.query(sql, new BeanListHandler<Products>(Products.class), pname+"%");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("sql error 4");
		}
		return list;
	}
	
	/**
	 * update product storage number
	 * @param pid
	 * @param buyCount
	 * @throws ComputerstoreException 
	 */
	public void updatePnum(String pid, int buyCount) throws ComputerstoreException {
		QueryRunner runner = new QueryRunner();
		String sql = "update products set pnum = pnum - ? where pid = ?";
		try {
			int row = runner.update(DataSourceUtils.getConnection(), sql, buyCount,pid);
			if(row != 1){
				throw new ComputerstoreException("update storage failed£¡");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("network anomalies£¡");
		}
	}
	
	/**
	 * page by categories
	 * @param pageSize
	 * @param pageCode
	 * @param pid
	 * @param category
	 * @param cputype
	 * @param gputype
	 * @param memtype
	 * @param pname
	 * @param minprice
	 * @param maxprice
	 * @return
	 * @throws ComputerstoreException
	 */
	public PageBean<Products> showProductByPage(int pageSize, int pageCode,
			String pid, String category, String cputype, String gputype, String memtype,String pname, String minprice,
			String maxprice) throws ComputerstoreException {
		
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		// create a PageBean object to encapsulate data
		PageBean<Products> page = new PageBean<Products>();
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		// create a list collection to encapsulate data
		List<Object> showParams = new ArrayList<Object>();
		List<Object> countParams = new ArrayList<Object>();
		showParams.add(1);
		countParams.add(1);
		// splice sql
		StringBuilder sb = new StringBuilder("select count(*) from products where 1=1 and tag = ? ");
		StringBuilder sb2 = new StringBuilder("select * from products where 1=1 and tag = ? ");
		
		if(pid != null && !pid.trim().isEmpty()){
			sb.append(" and pid like ?");
			sb2.append(" and pid like ?");
			showParams.add("%"+pid+"%");
			countParams.add("%"+pid+"%");
		}
		if(category != null && !category.trim().isEmpty()){
			sb.append(" and category = ?");
			sb2.append(" and category = ?");
			showParams.add(category);
			countParams.add(category);
		}
		
		//¡¾categorize by cputype, gputype or memtype¡¿
		if(cputype != null && !cputype.trim().isEmpty()){
			sb.append(" and cputype = ?");
			sb2.append(" and cputype = ?");
			showParams.add(cputype);
			countParams.add(cputype);
		}
		if(gputype != null && !gputype.trim().isEmpty()){
			sb.append(" and gputype = ?");
			sb2.append(" and gputype = ?");
			showParams.add(gputype);
			countParams.add(gputype);
		}
		if(memtype != null && !memtype.trim().isEmpty()){
			sb.append(" and memtype = ?");
			sb2.append(" and memtype = ?");
			showParams.add(memtype);
			countParams.add(memtype);
		}
		
		if(pname != null && !pname.trim().isEmpty()){
			sb.append(" and pname = ?");
			sb2.append(" and pname = ?");
			showParams.add(pname);
			countParams.add(pname);
		}
		if(minprice != null && !minprice.trim().isEmpty()){
			sb.append(" and price > ?");
			sb2.append(" and price > ?");
			showParams.add(minprice);
			countParams.add(minprice);
		}
		if(maxprice != null && !maxprice.trim().isEmpty()){
			sb.append(" and price < ?");
			sb2.append(" and price < ?");
			showParams.add(maxprice);
			countParams.add(maxprice);
		}
		
		if(sb2 != null){
			sb2.append(" limit ?,?");
			showParams.add((pageCode-1)*pageSize);
			showParams.add(pageSize);
		}
		
		try {
			long totalCount = (Long) runner.query(sb.toString(),new ScalarHandler(), countParams.toArray());
			page.setTotalCount((int)totalCount);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("countSql error");
		}
		
		List<Products> pList = null;
		try {
			pList = runner.query(sb2.toString(),new BeanListHandler<Products>(Products.class), showParams.toArray());
			page.setpList(pList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ComputerstoreException("showSql error");
		}
		return page;
	}

}
