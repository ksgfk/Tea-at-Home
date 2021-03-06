package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.inter.ControlProductInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class BuyProductinproduct
 */
@WebServlet("/buy/product")
public class BuyProductInProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlProductInter productInter=null;
    private ControlBuyinfo buyinfoInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyProductInProduct() {
        super();
        productInter= new ControlProduct();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 JsonElement Data = JsonUtility.read(request);
         JsonObject root = Data.getAsJsonObject();
         ServletOutputStream outputstream =response.getOutputStream();
         JsonWriter jsonWriter = new JsonWriter( new OutputStreamWriter(outputstream));
         int productid=root.get("productid").getAsInt();
         int count=root.get("count").getAsInt();
         Product item = productInter.findid(productid);
         Map<String, Object> M= new TreeMap<String, Object>();
         int userid = ((User)request.getSession().getAttribute("user")).getId();
         if(item==null||item.getCount()<count) {
        	 M.put("bok", new Message(false, "??????????????????"));
        	 M.put("data", null);
         }
         else {
        	 M.put("bok", new Message(true,"success"));
        	 BuyInfo goods=new BuyInfo(0, userid, productid, "????????????????????????","?????????",0,item.getPrice().multiply(BigDecimal.valueOf(count) ).doubleValue() );
        	 M.put("data", goods);
        	 buyinfoInter.add(goods);
         }
         JsonUtility.toJson(M, M.getClass(), jsonWriter);
         jsonWriter.flush();
         jsonWriter.close();
	}

}
