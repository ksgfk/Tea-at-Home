package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class BuyInfoServlet
 */
@WebServlet("/BuyInfo")
public class BuyInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlBuyinfoInter buyinfointer=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyInfoServlet() {
        super();
        buyinfointer=new ControlBuyinfo();
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
		  JsonElement userData = JsonUtility.read(request);
          JsonObject root = userData.getAsJsonObject();
          JsonWriter jsonWriter= new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
          int buyinfoid = root.get("buyinfoid").getAsInt();
          BuyInfo item=buyinfointer.findid(buyinfoid);
          if(item==null) {
        	  response.setStatus(404);
        	  return;
          }
          JsonUtility.toJson(item, item.getClass() ,jsonWriter);
          jsonWriter.flush();
          jsonWriter.close();
	}

}
