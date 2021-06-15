package com.etcxm.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etcxm.www.service.ProductService;
import com.etcxm.www.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class DeleteProductServlet
 */
@WebServlet("/del.do")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService productService=new ProductServiceImpl();
		String id=request.getParameter("id");		//获取id
		boolean isok=false;
		//把字符串id转成整形
		try {
			int iId=Integer.parseInt(id);
			isok=productService.delete(iId);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		String msg="";
    	String url="";
    	String jumpUrl="";
    	if(isok){	//成功
    		msg="删除产品成功！";
    		url="products.do";		//跳转的是servlet地址。为了获取数据。
    		jumpUrl="view/success.jsp";
    	}else{	//失败
    		msg="删除产品失败！";
    		jumpUrl="view/fail.jsp";
    	}
    	request.setAttribute("msg", msg);	//setAttribute保存属性，第一个参数是属性的名字，第二个参数是属性的值。
    	request.setAttribute("url",url);
    	request.getRequestDispatcher(jumpUrl).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
