package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Sockaddr;

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import junit.framework.Test;


/**
 * Servlet implementation class BuyProductInshoppingcarServlet
 */
@WebServlet("/buy/shoppingcar")
public class BuyProductInshoppingcarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ControlShoppingcart carInter=null;   
    private ControlBuyinfoInter buyinfoInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductInshoppingcarServlet() {
        super();
        carInter=new ControlShoppingcart();
        buyinfoInter= new ControlBuyinfo();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("application/json");
		JsonElement Data = JsonUtility.read(request);
		JsonObject root = Data.getAsJsonObject();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
		JsonArray carlist = root.get("data").getAsJsonArray();
		BigDecimal money = BigDecimal.valueOf(0);
		List<ShoppingCart> list = new ArrayList<ShoppingCart>();
		int userid = ((User) request.getSession().getAttribute("user")).getId();
		List<ShoppingCart> array = carInter.finduserid(userid);
		for (JsonElement iter : carlist) {
			JsonObject item = iter.getAsJsonObject();
			int userids = item.get("userid").getAsInt();
			int productid = item.get("productid").getAsInt();
			int count = item.get("count").getAsInt();
			list.add(new ShoppingCart(0, userids, productid, count));
		}
		if (array == null || array.size() == 0 || list.size() == 0) {
			JsonUtility.messagesuccess(jsonWriter, false, "找不到购物车");
			jsonWriter.flush();
			jsonWriter.close();
			return;
		} else {
			for (ShoppingCart item : list) {
				if (!array.contains(item)) {
					JsonUtility.messagesuccess(jsonWriter, false, "找不到购物车");
					jsonWriter.flush();
					jsonWriter.close();
					
					return;
				}
				item.setId(array.get(array.indexOf(item)).getId());
			}
			carInter.deletebatch(list);
		}
		List<BuyInfo> buyinfolist=new ArrayList<BuyInfo>();
		for(ShoppingCart item:list) {
			buyinfolist.add(new BuyInfo(0, userid, item.getProductId(), "集美大学诚毅学院", "正在路上", 0, item.getCount())) ;
		}
		if(carInter.deletebatch(list)&&buyinfoInter.updataBatch(buyinfolist)) {
			JsonUtility.messagesuccess(jsonWriter, true, "success");
		}else {
			JsonUtility.messagesuccess(jsonWriter, false, "找不到购物车");
		}
		jsonWriter.flush();
		jsonWriter.close();

	}

}
