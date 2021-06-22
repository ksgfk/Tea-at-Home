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

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class BuyInfoAllServlet
 */
@WebServlet("/buyinfo/queryall")
public class QueryBuyInfoAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private ControlBuyinfoInter buyinfointer=null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryBuyInfoAllServlet() {
        super();
        buyinfointer=new ControlBuyinfo();
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
          int userid = ((User) request.getSession().getAttribute("user")).getId();
          Map<BuyInfo, String> info=buyinfointer.findToProduct(userid);
          Map<String ,Object> M= new TreeMap<String,Object>();
          List<Temp> list=new ArrayList<Temp>();
          if(info==null||info.size()==0) {
        	  M.put("bok", new Message(false,"未找到"));
        	  M.put("data",null);
          }
          else {
        	  M.put("bok", new Message(true,"success"));
        	  for(Map.Entry<BuyInfo, String> item:info.entrySet()) {
        		  list.add(new Temp(item));
        	  }
        	  M.put("data",list);
          }
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

