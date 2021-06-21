package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlShoppingcart;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class DeleteShopppingcarServlet
 */
@WebServlet("/shopping/delete")
public class DeleteShopppingcarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControlShoppingcart cartinter=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteShopppingcarServlet() {
        super();
        cartinter= new ControlShoppingcart();
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
		JsonElement data=JsonUtility.read(request);
		JsonObject  root=data.getAsJsonObject();
		JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(response.getOutputStream() ) );
		int shoppingcarid=root.get("shoppingcarid").getAsInt();
		if(cartinter.findid(shoppingcarid)==null) {
			JsonUtility.messagesuccess(jsonWriter, false,"没有该购物车");
		}
		else if(cartinter.delete(shoppingcarid)) {
			JsonUtility.messagesuccess(jsonWriter, true, "success");
		}
		else {
			JsonUtility.messagesuccess(jsonWriter, false,"删除失败");			
		}
		jsonWriter.flush();
		jsonWriter.close();
	}

}
