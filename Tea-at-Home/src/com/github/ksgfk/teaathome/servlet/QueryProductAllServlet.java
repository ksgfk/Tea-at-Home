package com.github.ksgfk.teaathome.servlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.ksgfk.teaathome.control.impl.ControlProduct;
import com.github.ksgfk.teaathome.control.inter.ControlProductInter;
import com.github.ksgfk.teaathome.models.Message;
import com.github.ksgfk.teaathome.models.Product;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

/**
 * Servlet implementation class QueryProductAllServlet
 */
@WebServlet("/product/fuzzy_queryall")
public class QueryProductAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ControlProductInter productInter=null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryProductAllServlet() {
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
		response.setContentType("application/json");
		List<Product> list= productInter.findall();
		JsonWriter jsonWriter= new JsonWriter(new OutputStreamWriter(response.getOutputStream()));
		Map<String, Object> M= new TreeMap<String,Object>();
		if(list!=null&&list.size()!=0) {
			M.put("bok",new Message(true, "success"));
			M.put("data",list);
		}
		else {
			M.put("bok",new Message(false, "没有产品"));
			M.put("data", null);
		}
		JsonUtility.toJson(M, M.getClass(), jsonWriter);
		jsonWriter.flush();
		jsonWriter.close();
	}

}
