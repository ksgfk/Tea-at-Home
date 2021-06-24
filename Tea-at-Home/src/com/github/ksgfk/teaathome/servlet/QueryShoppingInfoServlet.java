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

import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.control.inter.ControlProductInter;
import com.github.ksgfk.teaathome.control.inter.ControlShoppingcartInter;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class ShoppingInfoServlet
 */
@WebServlet("/shoppingcar/query")
public class QueryShoppingInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private ControlProductInter productInter=null;
     //private ControlShoppingcartInter shoppingcatInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryShoppingInfoServlet() {
        super();
        productInter=new ControlProduct();
        //shoppingcatInter=new ControlShoppingcart();
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
		JsonWriter jsonwriter=new JsonWriter(new OutputStreamWriter(response.getOutputStream())); 
		JsonElement element = JsonUtility.read(request);
        JsonObject root = element.getAsJsonObject();
        int productid = root.get("Shoppingcarid").getAsInt();
        int userid= ((User)request.getSession().getAttribute("uesr")).getId();
        ShoppingCart item =null; 
        List<ShoppingCart> list= new ControlShoppingcart().finduserid(userid);
        Map<String,Object> M= new TreeMap<String,Object>();
        if(list==null||list.size()==0) {
        	M.put("bok",new Message(false, "该用户没有购物车" ));
        	M.put("data", null);
        }
        else {
        	for(ShoppingCart iter:list) {
        		if(iter.getProductId()==productid) {
        			item=iter;
        			break;
        		}
        	}
        	if(item==null) {
            	M.put("bok",new Message(false, "匹配不到购物车" ));
            	M.put("data", null);
        	}
        	else {
        		
        		M.put("bok", new Message(true, "success"));
        		Product pro=productInter.findid(item.getProductId());
        		M.put("data", new Temp(pro.getName(),item));
        	}
        }
        JsonUtility.toJson(M, M.getClass(),jsonwriter);
        jsonwriter.flush();
        jsonwriter.close();
	}
	private class Temp{
		private String name;
		private ShoppingCart cart;
		public Temp(String name,ShoppingCart car) {
			this.name=name;
			this.cart=car;
		}
	}
}
