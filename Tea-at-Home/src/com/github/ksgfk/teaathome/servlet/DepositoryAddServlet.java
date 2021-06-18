package com.github.ksgfk.teaathome.servlet;

import com.github.ksgfk.teaathome.control.impl.ControlDepository;
import com.github.ksgfk.teaathome.control.inter.ControlDepositoryInter;
import com.github.ksgfk.teaathome.models.Depository;
import com.github.ksgfk.teaathome.utility.JsonUtility;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.OutputStreamWriter;

@WebServlet(name = "/depository/add", value = "/depository/add")
public class DepositoryAddServlet extends HttpServlet {
    private final ControlDepositoryInter depo;

    public DepositoryAddServlet() {
        depo = new ControlDepository();
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
            JsonElement element = JsonUtility.read(request);
            JsonObject root = element.getAsJsonObject();
            String name = root.get("name").getAsString();
            String addr = root.get("address").getAsString();
            //TODO:图片
            stream = response.getOutputStream();
            writer = new OutputStreamWriter(stream);
            json = new JsonWriter(writer);
            if (name == null || name.isEmpty()) {
                writeAddFailed(json, "错误的仓库名");
            } else if (addr == null || addr.isEmpty()) {
                writeAddFailed(json, "错误的仓库名");
            } else {
                Depository t = depo.findName(name);
                if (t != null) {
                    writeAddFailed(json, "重复的仓库名");
                } else {
                    Depository newDepo = new Depository(-1, name, addr);
                    boolean result = depo.add(newDepo);
                    if (result) {
                        writeAddSuccess(json);
                    } else {
                        writeAddFailed(json, "添加仓库失败");
                    }
                }
            }
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

    public static void writeAddFailed(JsonWriter writer, String message) throws IOException {
        writer.beginObject();
        writer.name("success");
        writer.value(false);
        writer.name("message");
        writer.value(message);
        writer.endObject();
    }

    public static void writeAddSuccess(JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("success");
        writer.value(true);
        writer.name("message");
        writer.nullValue();
        writer.endObject();
    }
}
