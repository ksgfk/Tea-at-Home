package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
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
		int userid=1;//((User)request.getSession().getAttribute("user")).getId();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream() ) );
		Map<String,Object> M= new TreeMap<String,Object>();
		if(new ControlUser().findid(userid)==null) {
			M.put("bok", new Message(false, "没有该用户"));
			M.put("data", null);
		} 
		Map<String , ShoppingCart> mCart =	shopcartInter.finduserIdName(userid);
		List<Temp> list= new ArrayList<QueryShoppingcarAllservlet.Temp>();
		if(mCart==null||mCart.size()==0) {
			M.put("bok", new Message(false, "该用户没有购物车"));
			M.put("data", null);
			return;
		}
		else {
			M.put("bok", new Message(true, "success"));
			for(Map.Entry<String, ShoppingCart> item:mCart.entrySet()) {
				list.add(new Temp(item));
			}
			M.put("data", list);
		}
		JsonUtility.toJson(M, M.getClass(), jsonWriter);
		jsonWriter.flush();
		jsonWriter.close();
	}
	private class Temp{
		String name=null;
		ShoppingCart car=null;
		public Temp(Map.Entry<String, ShoppingCart> item) {
			name=item.getKey();
			car=item.getValue();
		}
	}
}
