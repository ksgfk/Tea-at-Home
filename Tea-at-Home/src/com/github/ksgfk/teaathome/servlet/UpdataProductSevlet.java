package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.inter.ControlProductInter;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class UpdataProductSevlet
 */
@WebServlet("/product/fuzzy_updata")
public class UpdataProductSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlProductInter productInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdataProductSevlet() {
        super();
        // TODO Auto-generated constructor stub
        productInter = new ControlProduct();
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
			JsonElement userData = JsonUtility.read(request);
	        JsonObject root = userData.getAsJsonObject();
	        String name = root.get("name").getAsString();
	        int  count= root.get("count").getAsInt(); 
			int productId=root.get("productid").getAsInt();
			int depositoryId=root.get("depositoryid").getAsInt();
			BigDecimal price =root.get("price").getAsBigDecimal();
			if(!productInter.updata(new Product(productId, name, count, depositoryId, price))) {
				JsonUtility.messagesuccess(jsonWriter, false, "异常");
			}
			else{
				JsonUtility.messagesuccess(jsonWriter, true, "success");
			};
			   jsonWriter.flush();
		        jsonWriter.close();
		
	}
   

}
