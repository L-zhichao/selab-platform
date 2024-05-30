package tyut.selab.bookservice.controller;

import com.alibaba.fastjson.JSON;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.utils.PageUtil;
import tyut.selab.utils.Result;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * @className: BookController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:27
 * @version: 1.0
 */

@WebServlet(name = "BookController",urlPatterns = {"/book/save","/book/update","/book/queryOne","/book/list","/book/delete"})
public class BookController extends HttpServlet {

    private BookService bookService = new BookServiceImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取url并拆成段
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        // 获取此次请求是save? update? query? list? 还是 delete? [要操作的方法名]
        String methodName = split[split.length - 1];
        Result result = null;
        if (methodName.equals("queryOne")) {
            query(req, resp);
        } else if (methodName.equals("list")) {
            result = list(req, resp);
        } else if (methodName.equals("delete")) {
            result = delete(req, resp);
        } else {
            result = Result.error(500004,"路径有误");
        }

        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置请求和响应的编码
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");
        // 获取url并拆成段
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        // 获取此次请求是save? update? query? list? 还是 delete? [要操作的方法名]
        String methodName = split[split.length - 1];
        Result result = null;
        if (methodName.equals("save")) {
            query(req, resp);
        } else if (methodName.equals("update")) {
            result = list(req, resp);
        } else {
            result = Result.error(500004,"路径有误");
        }

        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.flush();
        writer.close();
    }

    /**
     *  param: bookDto
     * @param request
     * @param response
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response){
//        doPost(request,response);
//        Integer i = bookService.insertBook(bookDto);
//        Class<Result> resultClass = Result.class;
//        x'xMethod method = resultClass.getDeclaredMethod("success", BookDto.class);
//        return (Result) method.invoke(bookDto);
        return null;
    }

    /**
     *  param: BookVo
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) {
        //doPost(request,response);
        // 把Java对象转换成字符串
        //String json = JSON.toJSONString(bookDto);
        // 再把字符串转换为Java对象
        //BookVo bookVo = JSON.parseObject(json, BookVo.class);
        //Integer i = bookService.updateBook(bookVo);
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
    private Result list(HttpServletRequest request, HttpServletResponse response) {
        //接受请求参数
        if ((request.getParameter("cur") == null) || (request.getParameter("size") == null) || Integer.parseInt(request.getParameter("cur")) <= 0 || Integer.parseInt(request.getParameter("size")) <= 0){
//            result.setMsg("页码或每页数量为空");
//            result.setCode(500001);
//            return result;
            return Result.error(500001,"信息错误");
        }

        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));

        //将参数传递给服务层，进行分页查询
        PageUtil<BookVo> bookVoPageUtil = bookService.selectList(cur, size);

        //将分页查询的结果响应给客户端

//        if(bookVoList.isEmpty()) {
//            result.setMsg("查询信息为空");
//            result.setData(null);
//            result.setCode(500003);
//        }
        return Result.success(bookVoPageUtil);
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
//        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
//        Integer i = bookService.deleteBook(bookId);
//        Class<Result> resultClass = Result.class;
//        if (i >= 0) {
//            Method method = resultClass.getDeclaredMethod("success", Integer.class);
//            return (Result) method.invoke(i);
//        }
        return null;
    }


}
