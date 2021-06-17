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
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet(name = "/user/register", value = "/user/register")
public class RegisterUserServlet extends HttpServlet {
    private final ControlUserInter userCtrl;

    public RegisterUserServlet() {
        userCtrl = new ControlUser();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setStatus(501);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JsonElement userData = JsonUtility.read(request);
            JsonObject root = userData.getAsJsonObject();
            String usr = root.get("username").getAsString();
            String pwd = root.get("password").getAsString();
            String pho = root.get("phone").getAsString();
            if (!isValidUsername(usr)) {
                JsonWriter writer = startResponse(response);
                writeRegisterFailed(writer, "非法用户名");
                endResponse(writer);
                return;
            }
            if (isRepeatUsername(userCtrl, usr)) {
                JsonWriter writer = startResponse(response);
                writeRegisterFailed(writer, "重复用户名");
                endResponse(writer);
                return;
            }
            if (!isValidPassword(pwd)) {
                JsonWriter writer = startResponse(response);
                writeRegisterFailed(writer, "无效密码");
                endResponse(writer);
                return;
            }
            if (!isValidPhone(pho)) {
                JsonWriter writer = startResponse(response);
                writeRegisterFailed(writer, "无效手机号");
                endResponse(writer);
                return;
            }
            User newUser = new User(usr, pwd, pho);
            newUser.setPermission(UserPermission.USER.getLevel());
            boolean success = userCtrl.add(newUser);
            JsonWriter writer = startResponse(response);
            if (success) {
                writeRegisterSuccess(writer);
            } else {
                writeRegisterFailed(writer, "内部错误,请联系管理员");
            }
            endResponse(writer);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
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

    public static JsonWriter startResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setStatus(200);
        ServletOutputStream stream = response.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(stream);
        return new JsonWriter(writer);
    }

    public static void endResponse(JsonWriter writer) throws IOException {
        writer.close();
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
