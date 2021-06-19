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
@WebServlet("/buyinfo/Queryall")
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
          JsonWriter jsonWriter= new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
          int userid = ((User) request.getSession().getAttribute("user")).getId();
          List<BuyInfo> list=buyinfointer.findUesrid(userid);
          Map<String ,Object> M= new TreeMap<String,Object>();
          if(list==null||list.size()==0) {
        	  M.put("bok", new Message(false,"未找到"));
          }
          else {
        	  M.put("bok", new Message(true,"success"));
        	  M.put("data",list);
          }
          JsonUtility.toJson(M, M.getClass(), jsonWriter);
          jsonWriter.flush();
          jsonWriter.close();
	}

}
