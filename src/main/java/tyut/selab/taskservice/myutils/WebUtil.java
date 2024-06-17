package tyut.selab.taskservice.myutils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.utils.Result;


import java.io.BufferedReader;
import java.io.IOException;

/**
 * ClassName: WebUtil
 * Package: tyut.selab.taskservice.myutils
 * Description:
 *
 * @Author :triumph
 * @Create 2024/5/16 上午9:13
 * @Version 1.0
 */
public class WebUtil {

    // 从请求中获取JSON串并转换为Object
    public static <T> T readJson(HttpServletRequest request, Class<T> clazz){
        T t =null;
        BufferedReader reader = null;
        try {
            reader = request.getReader();
            StringBuffer buffer =new StringBuffer();
            String line =null;
            while((line = reader.readLine())!= null) {
                buffer.append(line);
            }
            t = JSON.parseObject(buffer.toString(),clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return t;
    }
    // 将Result对象转换成JSON串并放入响应对象
    public static void writeJson(HttpServletResponse response, Result result){
        response.setContentType("application/json;charset=UTF-8");
        try {
            String json = JSONObject.toJSONString(result);
            response.getWriter().write(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
