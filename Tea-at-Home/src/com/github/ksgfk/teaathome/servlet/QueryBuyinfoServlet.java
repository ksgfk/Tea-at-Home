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

import org.apache.catalina.valves.rewrite.Substitution.MapElement;

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class QueryBuyinfoServlet
 */
@WebServlet("/buyinfo/query")
public class QueryBuyinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private ControlBuyinfoInter buyinfointer=null;  
	   private ControlProduct productInter=null;
 /**
  * @see HttpServlet#HttpServlet()
  */
 public QueryBuyinfoServlet() {
     super();
     buyinfointer=new ControlBuyinfo();
     productInter= new ControlProduct();
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
		 response.setContentType("application/json");
		 JsonWriter jsonWriter= new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
		 JsonElement userData = JsonUtility.read(request);
         JsonObject root = userData.getAsJsonObject();
		 int userid =1;// ((User) request.getSession().getAttribute("user")).getId();
         int productId=root.get("key").getAsInt();
         Map<BuyInfo, String> info=buyinfointer.findToProduct(userid);
         Map.Entry<BuyInfo,String> item=null;
         for(Map.Entry<BuyInfo,String> iter:info.entrySet()) {
        	 if(iter.getKey().getProductId() ==productId) {
        		 item=iter;
        		 break;
        	 }
         }
         Map<String ,Object> M= new TreeMap<String,Object>();
       
         if(item==null) {
        	 M.put("bok", new Message(false,"未找到"));
         }
         else {
        	 M.put("bok", new Message(true,"success"));
        	 M.put("data",new Temp(item));
         }
         //System.out.print(JsonUtility.toJson(M, M.getClass()));
         JsonUtility.toJson(M, M.getClass(), jsonWriter);
         jsonWriter.flush();
         jsonWriter.close();
	}
	private class Temp {
		private BuyInfo info;
		private String name;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Temp(BuyInfo info, String name) {
			super();
			this.info = info;
			this.name = name;
		}
		public Temp(Map.Entry<BuyInfo,String> item) {
			this.info = item.getKey();
			this.name = item.getValue();
		}
	}
	
}
