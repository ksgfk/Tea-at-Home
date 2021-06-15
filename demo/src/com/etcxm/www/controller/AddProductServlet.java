package com.etcxm.www.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etcxm.www.entity.Product;
import com.etcxm.www.service.ProductService;
import com.etcxm.www.service.impl.ProductServiceImpl;
import com.etcxm.www.utils.DBUtil;

/**
 * Servlet implementation class AddProductServlet
 */
@WebServlet("/add.do")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//设置编码
//		request.setCharacterEncoding("utf-8");
		//拿参数,opadd.jsp中获取参数，是一致的。
		String productName=request.getParameter("productName");
    	String productPrice=request.getParameter("productPrice");
    	//转为Double
    	double dProductPrice=Double.parseDouble(productPrice);
    	String productStore=request.getParameter("productStore");
    	//转为Integer
    	int iProductStore=Integer.parseInt(productStore);
    	//日期处理也相同
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	String productPdate=simpleDateFormat.format(new Date());
    	//实例一个Entity：Product
    	Product product=new Product(null, productName, dProductPrice, iProductStore, productPdate);
    	
    	ProductService productService=new ProductServiceImpl();
    	boolean isok = productService.save(product);
    	
    	String msg="";
    	String url="";
    	String jumpUrl="";
    	if(isok){	//成功
    		msg="新增产品成功！";
    		url="products.do";		//跳转的是servlet地址。为了获取数据。
    		jumpUrl="view/success.jsp";
    	}else{	//失败
    		msg="新增产品失败！";
    		jumpUrl="view/fail.jsp";
    	}
    	request.setAttribute("msg", msg);	//setAttribute保存属性，第一个参数是属性的名字，第二个参数是属性的值。
    	request.setAttribute("url",url);
    	request.getRequestDispatcher(jumpUrl).forward(request, response);
    	
	
	}

}
