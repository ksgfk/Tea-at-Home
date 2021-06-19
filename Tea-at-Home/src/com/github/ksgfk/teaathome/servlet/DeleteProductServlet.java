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
 * Servlet implementation class deleteProductServlet
 */
@WebServlet("/product/fuzzy_delete")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  private ControlProductInter productInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
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
       int productid = root.get("productid").getAsInt();
	   if(productInter.findid(productid)==null ) {
		   JsonUtility.messagesuccess(jsonWriter, false, "未找到");
		}
		else if(productInter.deleteId(productid)){
			 JsonUtility.messagesuccess(jsonWriter, true, "success");
		}else {
			   JsonUtility.messagesuccess(jsonWriter, false, "插入失败");
		}
		  jsonWriter.flush();
		  jsonWriter.close();
	}

	

}
