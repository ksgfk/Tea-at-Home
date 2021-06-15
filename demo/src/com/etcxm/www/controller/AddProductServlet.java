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

		//���ñ���
//		request.setCharacterEncoding("utf-8");
		//�ò���,opadd.jsp�л�ȡ��������һ�µġ�
		String productName=request.getParameter("productName");
    	String productPrice=request.getParameter("productPrice");
    	//תΪDouble
    	double dProductPrice=Double.parseDouble(productPrice);
    	String productStore=request.getParameter("productStore");
    	//תΪInteger
    	int iProductStore=Integer.parseInt(productStore);
    	//���ڴ���Ҳ��ͬ
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	String productPdate=simpleDateFormat.format(new Date());
    	//ʵ��һ��Entity��Product
    	Product product=new Product(null, productName, dProductPrice, iProductStore, productPdate);
    	
    	ProductService productService=new ProductServiceImpl();
    	boolean isok = productService.save(product);
    	
    	String msg="";
    	String url="";
    	String jumpUrl="";
    	if(isok){	//�ɹ�
    		msg="������Ʒ�ɹ���";
    		url="products.do";		//��ת����servlet��ַ��Ϊ�˻�ȡ���ݡ�
    		jumpUrl="view/success.jsp";
    	}else{	//ʧ��
    		msg="������Ʒʧ�ܣ�";
    		jumpUrl="view/fail.jsp";
    	}
    	request.setAttribute("msg", msg);	//setAttribute�������ԣ���һ�����������Ե����֣��ڶ������������Ե�ֵ��
    	request.setAttribute("url",url);
    	request.getRequestDispatcher(jumpUrl).forward(request, response);
    	
	
	}

}
