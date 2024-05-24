package tyut.selab.bookservice.controller;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.druid.support.json.JSONWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.utils.Result;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: BookController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:27
 * @version: 1.0
 */

@WebServlet(name = "BookController",urlPatterns = {"/book/save","/book/update","/book/query","/book/list","/book/delete"})
public class BookController extends HttpServlet {

    private BookService bookService = new BookServiceImpl();
    static private BookDto bookDto = new BookDto();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置请求和响应的编码
        resp.setContentType("text/html;charset=utf-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取url并拆成段
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        // 获取此次请求是save? update? query? list? 还是 delete? [要操作的方法名]
        String methodName = split[split.length - 1];
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            // 设置 暴力破解 方法的访问修饰符的限制
            declaredMethod.setAccessible(true);
            // 执行方法
            declaredMethod.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("异常处理");

        }


//            result.setCode(404);
//            result.setData(null);
//            result.setMsg("路径有误");
//        }
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//        //String jsonString1 = JSONObject.toJSONString(result);
//        String jsonString = JSON.toJSONString(result);
//        PrintWriter writer = resp.getWriter();
//        writer.print(jsonString);
//        writer.flush();
//        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求消息体(其实对应的就是请求参数)
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder();
        // 读取数据
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        System.out.println(sb.toString());
    }

    /**
     *  param: bookDto
     * @param request
     * @param response
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BookDto bookDto = tool(request, response);
        Integer i = bookService.insertBook(bookDto);
        Result result = new Result(404,null);
        if (i >= 0) {
            request.getRequestDispatcher("book/list").forward(request,response);
            result.setCode(200);
            result.setMsg("图书信息添加成功");
        }
        else{
            result.setCode(500);
            result.setMsg("服务器执行请求时发生了错误");
        }
        return result;
    }

    /**
     * param: BookVo
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) throws Exception{
        BookDto bookDto = tool(request,response);
        // 把Java对象转换成字符串
        String json = JSON.toJSONString(bookDto);
        // 再把字符串转换为Java对象
        BookVo bookVo = JSON.parseObject(json, BookVo.class);
        // 执行更新方法，得到改变行数的返回值
        Integer i = bookService.updateBook(bookVo);
        Result result = new Result(404,null);
        if (i > 0) {
            request.getRequestDispatcher("book/list").forward(request,response);
            result.setCode(200);
            result.setMsg("图书信息修改成功");
        }
        else{
            result.setCode(500);
            result.setMsg("图书信息修改失败");
        }
        return result;
    }

    /**
     * 通过BookId查询书籍信息
     * @param request
     * @param response
     * @return
     */
    private Result queryOne(HttpServletRequest request, HttpServletResponse response) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException, NoSuchMethodException, ServletException, IOException, InvocationTargetException {
        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
        BookVo bookVo = bookService.selectBookById(bookId);
        Result result = new Result(404,null);
        if(bookVo != null){
            result.setCode(200);
            result.setData(bookVo);
            result.setMsg("成功查询");
        }else{
            result.setCode(500009);
            result.setMsg("没有找到该书");
        }
        return result;
    }

    /**
     * 查询所有书籍(分页查询)
     * 传入 bookName & userId(书籍拥有者)[可以都传，也可以传入单个参数 | 调用前先判断参数是都为空(是否有效) | 为空跳过此参数 ]
     * param: bookName userId cur[不为空] size[不为空]   cur[当前页数] size[每页数量]
     *  @return list<BookVo>
     */
    private Result list(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.setCharacterEncoding("UTF-8");
        //接受请求参数
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));
        Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
        String bookName = request.getAttribute("bookName").toString();
        //将参数传递给服务层，进行分页查询
        List<BookVo> bookVoList = new ArrayList<>();
        if(cur == 0 || size==0){
            Result result = new Result(500001,null);
            result.setMsg("页码或每页数量为空");
            return result;
        }else{
            if(userId!=null && bookName!=null){
                bookVoList = bookService.selectList(cur,size,userId,bookName);
            } else if (userId==null && bookName!=null) {
                bookVoList = bookService.selectBookByBookName(cur,size,bookName);
            } else if (userId!=null && bookName==null) {
                bookVoList = bookService.selectListByOwnerId(cur,size,userId);
            }else{
                bookVoList = bookService.selectAllList(cur,size);
            }
        }
        //将分页查询的结果响应给客户端
        Result result = Result.success(bookVoList);
        if(bookVoList.isEmpty()) {
            result.setMsg("查询信息为空");
            result.setData(null);
            result.setCode(500003);
        }
        return result;
    }

    /**
     * 删除书籍
     * param: bookId
     *
     * @param request
     * @param response
     * @return
     */
    private Result delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
        Integer i = bookService.deleteBook(bookId);
        response.setCharacterEncoding("UTF-8");
        Result result = new Result(404,bookId);
        if (i > 0) {
            result.setCode(200);
            result.setMsg("图书信息删除成功");
        }
        else{
            result.setCode(500);
            result.setMsg("图书信息删除失败");
        }
        return result;
    }

    /**
     * 这是一个直接从请求体拿数据封装为bookDto的工具类
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static BookDto tool (HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取请求消息体(其实对应的就是请求参数)
        BufferedReader br = request.getReader();
        StringBuilder sb = new StringBuilder();
        // 读取数据
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        // 先转换为JSON字符串，再封装为BookDao类
        String DTO = sb.toString();
        BookDto bookDto = JSONObject.parseObject(DTO, BookDto.class);
        return bookDto;
    }

}
