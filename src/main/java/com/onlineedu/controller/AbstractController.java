package com.onlineedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class AbstractController {

    protected void generateJson(HttpServletResponse response, Object responseData) {
        try {
            Map<String, Object> responseObject = new HashMap<String, Object>();
            responseObject.put("isSuccess", true);
            responseObject.put("data", responseData);
            Gson gson = new Gson();
            String jsonData = gson.toJson(responseObject);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonData);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void generateJsonArray(HttpServletResponse response, JsonObject object) {
        JsonArray jsonArr = new JsonArray();
        jsonArr.add(object);
        try {
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print(jsonArr);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateJsonError(HttpServletResponse response, Object responseData) {
        try {
            Map<String, Object> responseObject = new HashMap<String, Object>();
            responseObject.put("isSuccess", false);
            if (responseData instanceof Exception) {
                responseObject.put("error", ((Exception) responseData).getMessage());
            } else {
                responseObject.put("error", responseData);
            }
            Gson gson = new Gson();
            String json = gson.toJson(responseObject);
            response.setContentType("application/json; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
