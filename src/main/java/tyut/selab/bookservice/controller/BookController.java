package tyut.selab.bookservice.controller;

import com.alibaba.druid.support.json.JSONWriter;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @className: BookController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:27
 * @version: 1.0
 */

@WebServlet(name = "BookController",urlPatterns = {"/book/save","book/update","/book/query","book/list","book/delete"})
public class BookController extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        Class aClass = this.getClass();
        try {
            Method declaredMethod = aClass.getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(this,req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        if (methodName.equals("save")) {
//            save(req, resp);
//        }else if (methodName.equals("update")) {
//            update(req, resp);
//        }else if (methodName.equals("query")){
//            query(req, resp);
//        }else if (methodName.equals("list")){
//            list(req, resp);
//        }else{
//
//        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求消息体(其实对应的就是请求参数)
        BufferedReader br = request.getReader();
        StringBuilder sb= new StringBuilder();
        // 读取数据
        String line = null;
        while((line = br.readLine())!=null){
            sb.append(line);
        }

    }

    /**
     *  param: bookDto
     * @param request
     * @param response
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     *  param: BookVo
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    private Result queryOne(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     *  通过BookId查询书籍信息
     *  传入 bookId & bookName & userId(书籍拥有者)[可以都传，也可以传入单个参数 | 调用前先判断参数是都为空(是否有效) | 为空跳过此参数 ]
     *  param: bookId bookName userId cur[不为空] size[不为空]
     * @return list<BookVo>
     */
    private Result query(HttpServletRequest request,HttpServletResponse response){
        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
        Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
        String bookName = request.getAttribute("bookName").toString();

        return null;
    }

    /**
     * 查询所有书籍(分页查询)
     * param: cur[当前页数] size[每页数量]
     *
     * @param request
     * @param response
     * @return
     */
    private Result list(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        //接受请求参数
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));
        //将参数传递给服务层，进行分页查询
        List<BookVo> bookVoList = bookService.selectList(cur, size);
        //将分页查询的结果响应给客户端
        response.setCharacterEncoding("UTF-8");
        Result result = new Result(200,bookVoList);
        result.setMsg("success");
        PrintWriter writer = response.getWriter();
        writer.print(result);
        writer.flush();
        writer.close();
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
    private Result delete(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }


}
