package com.github.ksgfk.teaathome.servlet;

import com.github.ksgfk.teaathome.control.impl.ControlUser;
import com.github.ksgfk.teaathome.control.inter.ControlUserInter;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
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

@WebServlet(name = "/user/login", value = "/user/login")
public class LoginServlet extends HttpServlet {
    private final ControlUserInter userCtrl;

    public LoginServlet() {
        userCtrl = new ControlUser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(501);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletOutputStream stream = null;
        OutputStreamWriter writer = null;
        JsonWriter json = null;
        try {
            JsonElement userData = JsonUtility.read(request);
            JsonObject root = userData.getAsJsonObject();
            String usr = root.get("username").getAsString();
            String pwd = root.get("password").getAsString();
            User user = userCtrl.findname(usr);
            response.setContentType("application/json");
            stream = response.getOutputStream();
            writer = new OutputStreamWriter(stream);
            json = new JsonWriter(writer);
            json.beginObject();
            json.name("success");
            if (user == null) {
                json.value(false);
                json.name("message");
                json.value("账号或密码错误");
            } else {
                if (!pwd.equals(user.getPassword())) {//TODO:加密比较
                    json.value(false);//登录失败
                    json.name("message");
                    json.value("账号或密码错误");
                } else {
                    json.value(true);//登录成功
                    json.name("message");
                    json.nullValue();
                    HttpSession session = request.getSession();//把user保存到session中
                    session.setAttribute("user", user);
                }
            }
            json.endObject();
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        } finally {//一定要按这个顺序关流!
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
}
