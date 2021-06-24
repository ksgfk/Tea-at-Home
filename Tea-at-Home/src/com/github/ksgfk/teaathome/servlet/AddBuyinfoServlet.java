package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
 * Servlet implementation class BuyShoppingToBuyinfoServlet
 */
@WebServlet("/buyinfo/add")
public class AddBuyinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlBuyinfoInter buyinfoInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBuyinfoServlet() {
        super();
        buyinfoInter=new ControlBuyinfo();
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
		//产生一个新订单 
		 JsonElement data = JsonUtility.read(request);
         JsonObject root = data.getAsJsonObject();
         ServletOutputStream outputStream =response.getOutputStream();
         JsonWriter jsonWriter = new JsonWriter( new OutputStreamWriter(outputStream));
         String receive="集美大学诚毅学院";
         String logistics="正在路上";
         int state=0;
         double pay=root.get("pay").getAsDouble();
         int userid=((User)request.getSession().getAttribute("user")).getId();
         int productid=root.get("productid").getAsInt();
         if( buyinfoInter.add(new BuyInfo(0, userid, productid, receive, logistics, state, pay) ) ) {
        	 JsonUtility.messagesuccess(jsonWriter, true,null);
         }
         else {
        	 JsonUtility.messagesuccess(jsonWriter, false,"订单异常");
         }
         jsonWriter.flush();
         jsonWriter.close();
		
	}
	//检查
}
