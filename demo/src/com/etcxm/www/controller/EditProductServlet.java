package com.etcxm.www.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etcxm.www.entity.Product;
import com.etcxm.www.service.ProductService;
import com.etcxm.www.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/edt.do")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//获取id
		String id=request.getParameter("id");
		//通过id获取该条产品的信息
		//1. 实例Service
		ProductService productService=new ProductServiceImpl();
		try {
			//2. 把id装成整数
			int iId=Integer.parseInt(id);	//有可能造成空指针错误
			//3. 调用方法，获取该条产品
			Product product = productService.findById(iId);
			//把product传给对应jsp页面
			request.setAttribute("product", product);
			request.getRequestDispatcher("view/editproduct.jsp")
			.forward(request, response);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			//跳转到错误提示页面
			String msg="请输入正确的参数！";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("view/fail.jsp")
			.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
