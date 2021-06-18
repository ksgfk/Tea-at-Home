package com.github.ksgfk.teaathome.servlet;

import com.github.ksgfk.teaathome.control.impl.ControlUser;
import com.github.ksgfk.teaathome.control.inter.ControlUserInter;
import com.github.ksgfk.teaathome.models.User;
import com.github.ksgfk.teaathome.models.UserPermission;
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

/**
 * 用户注册实现
 */
@WebServlet(name = "/user/register", value = "/user/register")
public class UserRegisterServlet extends HttpServlet {
    private final ControlUserInter userCtrl;

    public UserRegisterServlet() {
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
        JsonWriter write = null;
        try {
            response.setContentType("application/json");

            JsonElement userData = JsonUtility.read(request);
            JsonObject root = userData.getAsJsonObject();
            String usr = root.get("username").getAsString();
            String pwd = root.get("password").getAsString();
            String pho = root.get("phone").getAsString();

            stream = response.getOutputStream();
            writer = new OutputStreamWriter(stream);
            write = new JsonWriter(writer);

            if (!isValidUsername(usr)) {
                writeRegisterFailed(write, "非法用户名");
                return;
            }
            if (isRepeatUsername(userCtrl, usr)) {
                writeRegisterFailed(write, "重复用户名");
                return;
            }
            if (!isValidPassword(pwd)) {
                writeRegisterFailed(write, "无效密码");
                return;
            }
            if (!isValidPhone(pho)) {
                writeRegisterFailed(write, "无效手机号");
                return;
            }
            User newUser = new User(usr, pwd, pho);
            newUser.setPermission(UserPermission.USER.getLevel());
            boolean success = userCtrl.add(newUser);
            if (success) {
                writeRegisterSuccess(write);
            } else {
                writeRegisterFailed(write, "内部错误,请联系管理员");
            }
            User registered = userCtrl.findname(newUser.getName());
            HttpSession session = request.getSession();
            session.setAttribute("user", registered);
            response.setStatus(200);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        } finally {
            if (write != null) {
                write.close();
            }
            if (writer != null) {
                writer.close();
            }
            if (stream != null) {
                stream.close();
            }
        }
    }

    public static boolean isValidUsername(String name) {
        return name != null && !name.isEmpty() && User.checkname(name);
    }

    public static boolean isRepeatUsername(ControlUserInter ctrl, String name) {
        User any = ctrl.findname(name);
        return any != null;
    }

    public static boolean isValidPassword(String pwd) {
        return pwd != null && !pwd.isEmpty() && User.checkpassword(pwd);
    }

    public static boolean isValidPhone(String phone) {
        return phone != null && !phone.isEmpty() && User.checkphone(phone);
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
