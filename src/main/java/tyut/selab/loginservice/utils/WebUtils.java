package tyut.selab.loginservice.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.utils.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class WebUtils {
    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request, Class<T> clazz){
        T t =null;
        BufferedReader reader = null;
        StringBuffer buffer =new StringBuffer();
        try {
            reader = request.getReader();
            String line =null;
            while(null != (line = reader.readLine())) {
                buffer.append(line);
            }
        } catch (IOException e) {
        }finally{
            try {
                reader.close();
            } catch (IOException e) {
            }
            t = JSON.parseObject(buffer.toString(), clazz);
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
            throw new RuntimeException(e);
        }finally {
            writer.flush();
            writer.close();
        }
    }
}
