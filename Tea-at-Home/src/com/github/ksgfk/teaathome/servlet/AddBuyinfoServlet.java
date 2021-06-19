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
		 JsonElement Data = JsonUtility.read(request);
         JsonObject root = Data.getAsJsonObject();
         ServletOutputStream outputstream =response.getOutputStream();
         JsonWriter jsonwriter = new JsonWriter( new OutputStreamWriter(outputstream));
         String receive="集美大学诚毅学院";
         String logistics="正在路上";
         int state=0;
         double pay=root.get("pay").getAsDouble();
         int userid=((User)request.getSession().getAttribute("user")).getId();
         int productid=root.get("productid").getAsInt();
         if(check(jsonwriter, productid)&& buyinfoInter.add(new BuyInfo(0, userid, productid, receive, logistics, state, pay) ) ) {
        	 JsonUtility.messagesuccess(jsonwriter, true,null);
         }
         else {
        	 JsonUtility.messagesuccess(jsonwriter, false,"订单异常");
         }
         jsonwriter.flush();
         jsonwriter.close();
		
	}
	//检查
	private boolean check(JsonWriter jsonwriter,int productid) {
		return true;
	}

}
