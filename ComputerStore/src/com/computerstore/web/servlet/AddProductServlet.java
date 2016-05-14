package com.computerstore.web.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.computerstore.domain.Products;
import com.computerstore.service.ProductService;

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = -3727113192138902459L;
	/**
	 * receive parameters, add data and redirect page
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1£¬create factory, factory can get FileItem
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 1.1£¬Set up a temporary file directory and buffer size
		factory.setSizeThreshold(2*1024*1024);
		factory.setRepository(new File(this.getServletContext().getRealPath("/tmp")));
		
		// 2£¬Create upload core class, get entry for file
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 2.1£¬set encoding 
		upload.setHeaderEncoding("UTF-8");
		// 2.2£¬set file upload size limit
		upload.setFileSizeMax(2*1024*1024);
		
		// 3£¬Parse request object to obtain a List collection containing file entry
		List<FileItem> fileItems = null;
		try {
			fileItems = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
			throw new RuntimeException("get file anomalies£¡");
		}
		
		// 3.1£¬Create a Map collection, storing each file entry name and the value obtained
		Map<String,String> map = new HashMap<String,String>();
		// define file name
		String filename = null;
		// 4£¬traversal collection until no file
		for (FileItem fileItem : fileItems) {
			// 4.1£¬if it's normal text,get value, put into map collection
			if(fileItem.isFormField()){
				// get text box name
				String name = fileItem.getFieldName();
				// get text box value
				String value = fileItem.getString("UTF-8");
				// store name and value in map collection
				map.put(name, value);
			}else{
				// 4.2£¬if it's file, get file name, read it to server
				
				// get file name
				filename = fileItem.getName();
				if(filename != null && !filename.trim().isEmpty()){
					// IE6 solution: absolute file path
					if(filename.lastIndexOf("\\") != -1)
						filename = filename.substring(filename.lastIndexOf("\\")+1);
					
					// to prevent the same name and cover problem, rename file
					String uuidname = UUID.randomUUID().toString();
					filename = filename.substring(filename.lastIndexOf("."));
					filename = uuidname+filename;
					// get input stream, read file
					InputStream in = fileItem.getInputStream();
					// get the path to store the file
					String path = this.getServletContext().getRealPath("/computercover");
					// get output stream object
					OutputStream out = new FileOutputStream(path+"/"+filename);
					byte[] b = new byte[1024];
					int len = 0;
					while((len = in.read(b)) != -1){
						out.write(b,0,len);
					}
					
					// close stream
					out.close();
					in.close();
					// delete temporary file
					fileItem.delete();
				}else{
					System.out.println("file name is null!");
					return;
				}
			}
		}
		
		Products products = new Products();
		// 5£¬store the key-value pairs of Map into Bean
		try {
			BeanUtils.populate(products, map);
			products.setImgurl(filename);
			
			// service method addProduct()
			ProductService ps = new ProductService();
			ps.addProduct(products);
			
			// add succeed, jump to show page
			request.getRequestDispatcher("/product?method=showProductByPage1").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

