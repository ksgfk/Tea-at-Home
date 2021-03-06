package com.github.ksgfk.teaathome.utility;

import com.github.ksgfk.teaathome.models.Message;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * json工具类
 */
public class JsonUtility {
    private static final Gson INSTANCE;

    static {
        INSTANCE = new GsonBuilder().create();
    }

    /**
     * 从servlet请求中读取json
     *
     * @param request servlet请求
     * @return json根节点
     * @throws IOException 读取时发生IO错误
     */
    public static JsonElement read(HttpServletRequest request) throws IOException {
        ServletInputStream stream = request.getInputStream();
        InputStreamReader reader = new InputStreamReader(stream);
        JsonElement element = JsonParser.parseReader(reader);
        reader.close();
        stream.close();
        return element;
    }

    public static String toJson(Object instance, Type type) {
        return INSTANCE.toJson(instance, type);
    }
    public static void toJson(Object instance, Type type, JsonWriter jsonWriter) {
         INSTANCE.toJson(instance, type, jsonWriter);
    }
	public static void messagesuccess(JsonWriter jsonwriter,boolean isok,String message) throws IOException{
		 INSTANCE.toJson(new Message(isok, message), Message.class, jsonwriter);
	}
}
