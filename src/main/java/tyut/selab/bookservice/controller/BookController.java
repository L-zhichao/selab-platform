package tyut.selab.bookservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.exception.ServiceException;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.utils.PageUtil;
import tyut.selab.utils.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        // 设置请求和响应的编码
        resp.setContentType("text/html;charset=utf-8");
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException(e);
        }
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        // 获取url并拆成段
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        // 获取此次请求是save? update? query? list? 还是 delete? [要操作的方法名]
        String methodName = split[split.length - 1];
        Result result = new Result(404,null);
        if(methodName.equals("save")){
            result = save(req,resp);
        } else if(methodName.equals("update")){
            result = update(req,resp);
        } else if(methodName.equals("queryOne")){
            result = queryOne(req,resp);
        } else if(methodName.equals("list")){
            result = list(req,resp);
        } else if(methodName.equals("delete")){
            result = delete(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        // 获取请求消息体(其实对应的就是请求参数)
        BufferedReader br = null;
        try {
            br = request.getReader();
        } catch (IOException e) {
            throw new ServiceException("获取请求体字符流失败");
        }
        StringBuilder sb = new StringBuilder();
        // 读取数据
        String line = null;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new ServiceException("读取文本信息失败");
            }
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
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response) {
        BookDto bookDto = tool(request, response);
        Integer i = bookService.insertBook(bookDto);
        Result result = new Result(500002,null);
        if (i >= 0) {
            try {
                request.getRequestDispatcher("book/list").forward(request,response);
            } catch (ServletException e) {
                throw new ServiceException("页面跳转失败");
            } catch (IOException e) {
                throw new ServiceException("获取信息失败");
            }
            result.setCode(200);
            result.setMsg("图书信息添加成功");
        }
        else{
            result.setCode(500005);
            result.setMsg("图书信息添加失败");
        }
        return result;
    }

    /**
     * param: BookVo
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) {
        BookDto bookDto = tool(request,response);
        // 把Java对象转换成字符串
        String json = JSON.toJSONString(bookDto);
        // 再把字符串转换为Java对象
        BookVo bookVo = JSON.parseObject(json, BookVo.class);
        // 执行更新方法，得到改变行数的返回值
        Integer i = bookService.updateBook(bookVo);
        Result result = new Result(500002,null);
        if (i > 0) {
            try {
                request.getRequestDispatcher("book/list").forward(request,response);
            } catch (ServletException e) {
                throw new ServiceException("页面跳转失败");
            } catch (IOException e) {
                throw new ServiceException("获取信息失败");
            }
            result.setCode(200);
            result.setMsg("图书信息修改成功");
        }
        else{
            result.setCode(500005);
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
    private Result queryOne(HttpServletRequest request, HttpServletResponse response) {
        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
        BookVo bookVo = bookService.selectBookById(bookId);
        Result result = new Result(500002,null);
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
    private Result list(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("编码为UTF—8失败");
        }
        //接受请求参数
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));
        //将参数传递给服务层，进行分页查询
        PageUtil<BookVo> bookVoPageUtil = new PageUtil<BookVo>();
        if (cur == 0 || size==0) {
            Result result = new Result(500001,null);
            result.setMsg("页码或每页数量为空");
            return result;
        }else{
            if(request.getAttribute("userId")==null &&request.getAttribute("bookName")==null){
                bookVoPageUtil = bookService.selectAllList(cur,size);
                return Result.success(bookVoPageUtil);
            } else if (request.getAttribute("userId")==null &&request.getAttribute("bookName")!=null) {
                String bookName = request.getAttribute("bookName").toString();
                bookVoPageUtil = bookService.selectBookByBookName(cur,size,bookName);
                return Result.success(bookVoPageUtil);
            } else if (request.getAttribute("userId")!=null &&request.getAttribute("bookName")==null) {
                Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
                bookVoPageUtil = bookService.selectListByOwnerId(cur,size,userId);
                return Result.success(bookVoPageUtil);
            } else{
                Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
                String bookName = request.getAttribute("bookName").toString();
                bookVoPageUtil = bookService.selectList(cur,size,userId,bookName);
                return Result.success(bookVoPageUtil);
            }
        }
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
        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
        Integer i = bookService.deleteBook(bookId);
        response.setCharacterEncoding("UTF-8");
        Result result = new Result(500004,bookId);
        if (i > 0) {
            result.setCode(200);
            result.setMsg("图书信息删除成功");
        }
        else{
            result.setCode(500009);
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
    public static BookDto tool (HttpServletRequest request, HttpServletResponse response) {
        // 获取请求消息体(其实对应的就是请求参数)
        BufferedReader br = null;
        try {
            br = request.getReader();
        } catch (IOException e) {
            throw new ServiceException("获取请求体的字符流失败");
        }
        StringBuilder sb = new StringBuilder();
        // 读取数据
        String line = null;
        while (true) {
            try {
                if (!((line = br.readLine()) != null)) break;
            } catch (IOException e) {
                throw new ServiceException("读取文本信息失败");
            }
            sb.append(line);
        }
        // 先转换为JSON字符串，再封装为BookDao类
        String DTO = sb.toString();
        BookDto bookDto = JSONObject.parseObject(DTO, BookDto.class);
        return bookDto;
    }

}
