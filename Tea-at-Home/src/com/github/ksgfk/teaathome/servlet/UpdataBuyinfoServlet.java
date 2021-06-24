package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;

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
 * Servlet implementation class UpdataBuyinfoServlet
 */
@WebServlet("/buyinfo/updata")
public class UpdataBuyinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlBuyinfoInter buyinfoInter=null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataBuyinfoServlet() {
        super();
        buyinfoInter =  new ControlBuyinfo();
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
		//转换Json,以及输出准备
		 response.setContentType("application/json");
		JsonElement data= JsonUtility.read(request);;
		JsonObject root=data.getAsJsonObject();
		ServletOutputStream outputStream =response.getOutputStream();
        JsonWriter jsonWriter = new JsonWriter( new OutputStreamWriter(outputStream));
        //
        int buyinfoId= root.get("key").getAsInt();
        int userId= ((User)request.getSession().getAttribute("user")).getId();
        int productId=root.get("productid").getAsInt();
        String receive=root.get("receive").getAsString();
        String logistics=root.get("logistics").getAsString();
        int state = root.get("state").getAsInt();
        double pay =root.get("pay").getAsDouble();
        BuyInfo item= new BuyInfo(buyinfoId, userId, productId, receive, logistics, state, pay);
        if(buyinfoInter.upDate(item)) {
        	JsonUtility.messagesuccess(jsonWriter, true, null);
        }else {
        	JsonUtility.messagesuccess(jsonWriter, false, "失败");
        }
        jsonWriter.flush();
        jsonWriter.close();
	}

}
