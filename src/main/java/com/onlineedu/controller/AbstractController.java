package com.onlineedu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public abstract class AbstractController {

  protected void generateJson(HttpServletResponse response, Object responseData) {
    try {
      Gson gson = new Gson();
      String jsonData = gson.toJson(responseData);
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
}
