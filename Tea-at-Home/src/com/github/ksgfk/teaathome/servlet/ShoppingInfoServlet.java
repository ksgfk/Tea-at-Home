package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;

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
 * Servlet implementation class ShoppingInfoServlet
 */
@WebServlet("/ShoppingInfo")
public class ShoppingInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     private ControlProductInter productInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingInfoServlet() {
        super();
        productInter=new ControlProduct();
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
		JsonWriter jsonwriter=new JsonWriter(new OutputStreamWriter(response.getOutputStream())); 
		JsonElement element = JsonUtility.read(request);
        JsonObject root = element.getAsJsonObject();
        int productid = root.get("productid").getAsInt();
        Product item = productInter.findid(productid);
        if(item==null) {
        	response.setStatus(501);
        	return ;
        }
        JsonUtility.toJson(item, Product.class,jsonwriter);
        jsonwriter.flush();
        jsonwriter.close();
	}
}
