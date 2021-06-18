package com.github.ksgfk.teaathome.servlet;

import com.github.ksgfk.teaathome.control.impl.ControlUser;
import com.github.ksgfk.teaathome.control.inter.ControlUserInter;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
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

@WebServlet(name = "/user/query_info", value = "/user/query_info")
public class GetUserInfoServlet extends HttpServlet {
    private final ControlUserInter userCtrl;

    public GetUserInfoServlet() {
        userCtrl = new ControlUser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream stream = null;
        OutputStreamWriter writer = null;
        JsonWriter json = null;
        try {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            response.setContentType("application/json");
            stream = response.getOutputStream();
            writer = new OutputStreamWriter(stream);
            json = new JsonWriter(writer);
            json.beginObject();
            json.name("success");
            if (user == null) {
                json.value(false);
                json.name("message");
                json.value("请先登录");
            } else {
                User refresh = userCtrl.findid(user.getId());
                session.setAttribute("user", refresh);
                json.value(true);
                json.name("user");
                json.jsonValue(JsonUtility.toJson(user, User.class));//TODO:更好的办法?
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
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(501);
    }
}
