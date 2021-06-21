package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.control.impl.ControlUser;
import com.github.ksgfk.teaathome.control.inter.ControlShoppingcartInter;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class ShoppingcarAllservlet
 */
@WebServlet("/shoppingcar/queryall")
public class QueryShoppingcarAllservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlShoppingcartInter shopcartInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryShoppingcarAllservlet() {
        super();
        shopcartInter=new  ControlShoppingcart();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userid=((User)request.getSession().getAttribute("user")).getId();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream() ) );
		Map<String,Object> M= new TreeMap<String,Object>();
		if(new ControlUser().findid(userid)==null) {
			M.put("bok", new Message(false, "没有该用户"));
			M.put("data", null);
		} 
		List<ShoppingCart> list =	shopcartInter.finduserid(userid);
		if(list==null||list.size()==0) {
			M.put("bok", new Message(false, "该用户没有购物车"));
			M.put("data", null);
			return;
		}
		else {
			M.put("bok", new Message(true, "success"));
			M.put("data", list);
		}
		JsonUtility.toJson(M, M.getClass(), jsonWriter);
		jsonWriter.flush();
		jsonWriter.close();
	}
}
