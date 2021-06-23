package com.github.ksgfk.teaathome.servlet;

import com.github.ksgfk.teaathome.models.User;
import com.google.gson.stream.JsonWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet(name = "/user/logout", value = "/user/logout")
public class UserLogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream stream = null;
        OutputStreamWriter writer = null;
        JsonWriter json = null;
        try {
            HttpSession session = request.getSession();
            Object usrObj = session.getAttribute("user");
            response.setContentType("application/json");
            stream = response.getOutputStream();
            writer = new OutputStreamWriter(stream);
            json = new JsonWriter(writer);
            session.invalidate();//无论用户存不存在都销毁session
            json.beginObject();
            json.name("success");
            if (usrObj instanceof User) {
                json.value(true);
                json.name("message");
                json.nullValue();
            } else {
                json.value(false);
                json.name("message");
                json.value("该用户已登出");
            }
            json.endObject();
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        } finally {
            if (json != null) {
                json.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (stream != null) {
                stream.close();
            }
        }
        request.getRequestDispatcher("index.html");//.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(501);
    }
}
