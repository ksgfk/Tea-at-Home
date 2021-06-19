package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.control.inter.ControlShoppingcartInter;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class ShoppingcarAllservlet
 */
@WebServlet("/ShoppingcarAllservlet")
public class ShoppingcarAllservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlShoppingcartInter shopcartInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingcarAllservlet() {
        super();
        shopcartInter=new  ControlShoppingcart();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid=((User)request.getSession().getAttribute("user")).getId();
		List<ShoppingCart> list =	shopcartInter.finduserid(userid);
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream() ) );
		if(list==null||list.size()==0) {
			response.setStatus(404);
			return;
		}
		JsonUtility.toJson(list, list.getClass(), jsonWriter);
		jsonWriter.flush();
		jsonWriter.close();
	}
}
