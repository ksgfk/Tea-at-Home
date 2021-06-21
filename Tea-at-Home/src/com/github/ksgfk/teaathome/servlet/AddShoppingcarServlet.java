package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class AddShoppingcarServlet
 */
@WebServlet("/shoppingcar/add")
public class AddShoppingcarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlShoppingcart cartinter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShoppingcarServlet() {
        super();
        cartinter= new ControlShoppingcart();
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
		JsonElement data=JsonUtility.read(request);
		JsonObject  root=data.getAsJsonObject();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream() ) );
		int productid=root.get("productid").getAsInt();
		int count=root.get("conut").getAsInt();
		Product product=new ControlProduct().findid(productid);
		Map<String, Object> M= new TreeMap<String,Object>();
		if(cartinter.find(userid, productid)!=null) {
			M.put("bok", new Message(false, "已存在"));
			M.put("data", null);
		}
		else if(product==null){
			M.put("bok", new Message(false, "没有该产品"));
			M.put("data", null);
		}
		else if(product!=null&&product.getCount()<count) {
			M.put("bok", new Message(false, "产品数量不足"));
			M.put("data", null);
		}
		else {
			ShoppingCart item= new ShoppingCart(0, userid, productid, count);
			M.put("bok", new Message(true, "success"));
			M.put("data", item);
		}
		JsonUtility.toJson(M, M.getClass(), jsonWriter);
		jsonWriter.flush();
		jsonWriter.close();
	}

}
