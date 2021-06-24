package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
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

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.control.inter.ControlProductInter;
import com.github.ksgfk.teaathome.control.inter.ControlShoppingcartInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.models.ShoppingCart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;


/**
 * Servlet implementation class BuyProductInshoppingcarServlet
 */
@WebServlet("/buy/shoppingcar")
public class BuyProductInShoppingcarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    private ControlShoppingcartInter carInter=null;   
    private ControlBuyinfoInter buyinfoInter=null;
    private ControlProductInter productInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductInShoppingcarServlet() {
        super();
        carInter=new ControlShoppingcart();
        buyinfoInter= new ControlBuyinfo();
        productInter= new ControlProduct();
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
		JsonElement  data = JsonUtility.read(request);
		JsonObject root = data.getAsJsonObject();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
		JsonArray carlist = root.get("shoppingcarid").getAsJsonArray();
		List<ShoppingCart> list = new ArrayList<ShoppingCart>();
		int userid = ((User) request.getSession().getAttribute("user")).getId();
		double money=0;
		
		int carNumber[]=new int[carlist.size()];
		int productNumber[]=new int[carlist.size()];
		int i=0;
		for (JsonElement iter : carlist) {
			carNumber[i++]=iter.getAsInt();
		}
		Map<String, ShoppingCart> lists = carInter.querybatch(carNumber, userid);
		List<ShoppingCart> array= new ArrayList<ShoppingCart>();
		if (lists == null || lists.size() == 0 ||lists.size()!=i) {
			JsonUtility.messagesuccess(jsonWriter, false, "找不到购物车");
			jsonWriter.flush();
			jsonWriter.close();
			return;
		} else {
			i=0;
			for (Map.Entry<String, ShoppingCart> item : lists.entrySet()) {
				productNumber[i++]=item.getValue().getId();
				array.add(item.getValue());
			}
			carInter.deletebatch(array);
		}
		List<BuyInfo> buyinfoList=new ArrayList<BuyInfo>();
		List<Product> productList=productInter.findBatch(productNumber);
		Iterator<Product> iter=productList.iterator();
		for(ShoppingCart item:list) {
			Product temp=iter.next();
			double price=temp.getPrice().doubleValue()*item.getCount();
			buyinfoList.add(new BuyInfo(0, userid, item.getProductId(), "集美大学诚毅学院", "正在路上", 0, price)) ;
			money+=price;
			iter.next();
		}
		if(carInter.deletebatch(list)&&buyinfoInter.addBatch(buyinfoList)) {
			JsonUtility.messagesuccess(jsonWriter, true, Double.toString(money));
		}else {
			JsonUtility.messagesuccess(jsonWriter, false, "找不到购物车");
		}
		jsonWriter.flush();
		jsonWriter.close();

	}

}
