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

@WebServlet(name = "/depository/modify", value = "/depository/modify")
public class DepositoryModifyServlet extends HttpServlet {
    private final ControlDepositoryInter depo;

    public DepositoryModifyServlet() {
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
            int depoId = root.get("id").getAsInt();
            Depository old = depo.findid(depoId);
            stream = response.getOutputStream();
            writer = new OutputStreamWriter(stream);
            json = new JsonWriter(writer);
            if (old == null) {
                writeModifyFailed(json, "找不到仓库");
            } else {
                JsonElement newName = root.get("name");
                JsonElement newAddr = root.get("address");
                Depository newDepo = new Depository(old.getId(), old.getName(), old.getName());
                if (newName != null) {
                    String newNameValue = newName.getAsString();
                    if (!newNameValue.isEmpty()) {
                        newDepo.setName(newNameValue);
                    }
                }
                if (newAddr != null) {
                    String newAddrValue = newAddr.getAsString();
                    if (!newAddrValue.isEmpty()) {
                        newDepo.setAddress(newAddrValue);
                    }
                }
                boolean result = depo.updata(newDepo);
                if (result) {
                    writeModifySuccess(json);
                } else {
                    writeModifyFailed(json, "修改仓库失败");
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

    public static void writeModifyFailed(JsonWriter writer, String message) throws IOException {
        writer.beginObject();
        writer.name("success");
        writer.value(false);
        writer.name("message");
        writer.value(message);
        writer.endObject();
    }

    public static void writeModifySuccess(JsonWriter writer) throws IOException {
        writer.beginObject();
        writer.name("success");
        writer.value(true);
        writer.name("message");
        writer.nullValue();
        writer.endObject();
    }
}
