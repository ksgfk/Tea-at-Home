package com.etcxm.www.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.etcxm.www.entity.Product;
import com.etcxm.www.service.ProductService;
import com.etcxm.www.service.impl.ProductServiceImpl;

/**
 * Servlet implementation class AllProductsByPageServlet
 */
@WebServlet("/allpage.do")
public class AllProductsByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllProductsByPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pageNum=request.getParameter("pageNum");		//����ڼ�ҳ
		String pageSize=request.getParameter("pageSize");	//ÿҳ��С
		
		int iPageNum=1;			//��ʼ��ҳ��Ϊ��һҳ��
		int iPageSize=5;
		
		try{
			iPageNum=Integer.parseInt(pageNum);		//���ַ���ǿ��ת��Ϊ����
			iPageSize=Integer.parseInt(pageSize);
		}catch(Exception e){
			e.printStackTrace();
		}
		if(iPageNum<1)iPageNum=1;	//���ҳ�������ܵ��ڵ�1ҳ��
		ProductService productService=new ProductServiceImpl();
		List<Product> list=productService.findByPage(iPageNum, iPageSize);
		request.setAttribute("list", list);
		request.setAttribute("currPage", iPageNum);		//�ѵ�ǰ�����ҳ��д�뵽request��
		
		//��ȡ�ܹ���������¼
		int allCount=productService.count();
		request.setAttribute("allCount", allCount);
		//�����ܹ�����ҳ
		int allPage=1;
		if(allCount%iPageSize>0){
			allPage=allCount/iPageSize+1;
		}else{
			allPage=allCount/iPageSize;
		}
		request.setAttribute("allPage", allPage);
		//��ÿҳ����д�뵽request�У����͵�jsp����
		request.setAttribute("pageSize", iPageSize);
		
		request.getRequestDispatcher("view/allproductspage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
