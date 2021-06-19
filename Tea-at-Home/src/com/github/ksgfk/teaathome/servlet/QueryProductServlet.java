package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.util.List;

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
 * Servlet implementation class QueryProductServlet
 */
@WebServlet("/product/fuzzy_query")
public class QueryProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlProductInter productInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryProductServlet() {
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
		response.setContentType("application/json"); 
		JsonWriter jsonWriter= new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
			JsonElement userData = JsonUtility.read(request);
	        JsonObject root = userData.getAsJsonObject();
	        String name = root.get("key").getAsString();
	        List<Product> list=productInter.findname(name);
	        if(list==null&&list.size()==0) {
	        	response.setStatus(404);
	        	jsonWriter.flush();
	   	        jsonWriter.close();
	        	return;
	        }
	        JsonUtility.toJson(list, list.getClass(), jsonWriter);
	        jsonWriter.flush();
	        jsonWriter.close();
	}

}
