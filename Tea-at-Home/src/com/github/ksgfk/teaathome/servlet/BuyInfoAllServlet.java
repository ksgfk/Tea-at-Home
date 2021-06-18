package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlBuyinfo;
import com.github.ksgfk.teaathome.control.inter.ControlBuyinfoInter;
import com.github.ksgfk.teaathome.models.BuyInfo;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class BuyInfoAllServlet
 */
@WebServlet("/BuyInfoAllServlet")
public class BuyInfoAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private ControlBuyinfoInter buyinfointer=null;  
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BuyInfoAllServlet() {
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
          JsonWriter jsonWriter= new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
          int userid = ((User) request.getSession().getAttribute("user")).getId();
          List<BuyInfo> list=buyinfointer.findUesrid(userid);
          if(list==null||list.size()==0) {
        	  response.setStatus(404);
          }
          JsonUtility.toJson(list, list.getClass() ,jsonWriter);
          jsonWriter.flush();
          jsonWriter.close();
		  
	}

}
