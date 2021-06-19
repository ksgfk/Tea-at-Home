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
    private ControlBuyinfoInter buyinfointer=null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataBuyinfoServlet() {
        super();
        buyinfointer =  new ControlBuyinfo();
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
		JsonElement Data= JsonUtility.read(request);;
		JsonObject root=Data.getAsJsonObject();
		ServletOutputStream outputstream =response.getOutputStream();
        JsonWriter jsonwriter = new JsonWriter( new OutputStreamWriter(outputstream));
        //
        int buyinfoid= root.get("key").getAsInt();
        int user_id= ((User)request.getSession().getAttribute("user")).getId();
        int productid=root.get("productid").getAsInt();
        String receive=root.get("receive").getAsString();
        String logistics=root.get("logistics").getAsString();
        int state = root.get("state").getAsInt();
        double pay =root.get("pay").getAsDouble();
        BuyInfo item= new BuyInfo(buyinfoid, user_id, productid, receive, logistics, state, pay);
        if(buyinfointer.updata(item)) {
        	JsonUtility.messagesuccess(jsonwriter, true, null);
        }else {
        	JsonUtility.messagesuccess(jsonwriter, false, "失败");
        }
        jsonwriter.flush();
        jsonwriter.close();
	}

}
