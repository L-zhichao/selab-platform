package tyut.selab.loginservice.utils;

import com.alibaba.fastjson.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.utils.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtils {
    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request, Class<T> clazz) {
        T t = null;
        BufferedReader reader = null;
        StringBuilder buffer = new StringBuilder();
        String line = null;

        try {
            reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (IOException e) {
            // Log the error (you can use a logging framework or print the stack trace)
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            t = JSON.parseObject(buffer.toString(), clazz);
        } catch (Exception e) {
            // Log the error or handle the parse exception
            e.printStackTrace();
        }

        return t;
    }
    // 将Result对象转换成JSON串并放入响应对象
    public static void writeJson(HttpServletResponse response, Result result){
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = null;
        try {
            String json = JSONObject.toJSONString(result);
            writer = response.getWriter();
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
