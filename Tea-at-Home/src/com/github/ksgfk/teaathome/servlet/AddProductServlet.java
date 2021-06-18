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
@WebServlet("/product/fuzzy_add")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlProductInter productInter=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
			int depository_id=root.get("count").getAsInt();
			BigDecimal price =root.get("price").getAsBigDecimal();
			if(!productInter.add(new Product(depository_id, name, count, depository_id, price))) {
				writeRegisterFailed(jsonWriter, "插入错误");
			}
			else{
				writeRegisterSuccess(jsonWriter);
			};
			   jsonWriter.flush();
		        jsonWriter.close();
		
	}
    public static void writeRegisterFailed(JsonWriter writer, String message) throws IOException {
        writer.beginObject();
        writer.name("success");
        writer.value(false);
        writer.name("message");
        writer.value(message);
        writer.endObject();
    }

    public static void writeRegisterSuccess(JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("success");
        writer.value(true);
        writer.name("message");
        writer.nullValue();
        writer.endObject();
    }

}
