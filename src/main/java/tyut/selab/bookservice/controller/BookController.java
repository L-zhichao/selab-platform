package tyut.selab.bookservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.exception.ServiceException;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.utils.PageUtil;
import tyut.selab.utils.Result;

import java.awt.print.Book;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            result = queryOne(req, resp);
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
            result = save(req, resp);
        } else if (methodName.equals("update")) {
            result = update(req, resp);
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
     *  正则表达式来判断数据类型
     * @param obj
     * @return
     */
    public static boolean isString(Object obj) {
        if (obj == null) {
            return false;
        }
        return true;
//        String str = obj.toString();
//        return str.matches("^[a-zA-Z]+$");
    }
    public static boolean isInteger(Object obj) {
        if (obj == null) {
            return false;
        }
        // 使用双反斜杠转义正则表达式中的反斜杠
//        String regex = "^-?\\\\d+$";
        String regex = "^[1-9]\\d*$";
        return obj.toString().matches(regex);
    }
    public static boolean isDouble(Object obj) {
        if (obj == null) {
            return false;
        }
        String str = obj.toString();
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     *  param: bookDto
     * @param request
     * @param response
     * @return
     */
    private Result<Void> save(HttpServletRequest request, HttpServletResponse response) {
        //权限判断
//        UserLocal user = SecurityUtil.getUser();
//        if(user.getRoleId() == 2){
//            return Result.error(500013,"权限不足");
//        }

        JSONObject jsonObject = tool(request, response);
        if (!isString(jsonObject.get("bookName"))) {
            return Result.error(500001, "bookName信息输入有误");
        } else if (!isString(jsonObject.get("bookAuthor"))) {
            return Result.error(500001, "bookAuthor信息输入有误");
        } else if (!isString(jsonObject.get("bookDetails"))) {
            return Result.error(500001, "bookDetails信息输入有误");
        } else if (!isDouble(jsonObject.get("price"))) {
            return Result.error(500001, "price信息输入有误");
        } else if (!isInteger(jsonObject.get("owner"))) {
            return Result.error(500001, "owner信息输入有误");
        } else if (!isString(jsonObject.get("remark"))) {
            return Result.error(500001, "remark信息输入有误");
        } else if (!isString(jsonObject.get("bookRef"))) {
            return Result.error(500001, "bookRef信息输入有误");
        }
        String bookName = String.valueOf(jsonObject.get("bookName"));
        String bookAuthor = String.valueOf(jsonObject.get("bookAuthor"));
        String bookDetails = String.valueOf(jsonObject.get("bookDetails"));
        Double price = Double.valueOf(jsonObject.get("price").toString());
        if (price<=0){
            return Result.error(500001,"信息输入有误");
        }
        Integer owner = Integer.valueOf(jsonObject.get("owner").toString());
        String remark = String.valueOf(jsonObject.get("remark"));
        String bookRef = String.valueOf(jsonObject.get("bookRef"));

        //将参数封装为Dto类
        BookDto bookDto = new BookDto();
        bookDto.setBookName(bookName);
        bookDto.setBookAuthor(bookAuthor);
        bookDto.setBookDetails(bookDetails);
        bookDto.setPrice(price);
        bookDto.setOwner(owner);
        bookDto.setRemark(remark);
        bookDto.setBookRef(bookRef);

        Integer i = bookService.insertBook(bookDto);
        Result result = new Result(500002,null);
        if (i > 0) {
            return result.success(null);
        }
        else{
            return result.error(500005,"图书信息添加失败");
        }
    }

    /**
     * param: BookVo
     * @param request
     * @param response
     * @return
     */
    private Result update(HttpServletRequest request, HttpServletResponse response) {
        //权限判断
        UserLocal user = SecurityUtil.getUser();
        if(user.getRoleId() == 2){
            return Result.error(500013,"权限不足");
        }

        JSONObject jsonObject = tool(request, response);
        if (isInteger(jsonObject.get("bookId")) || isString(jsonObject.get("bookName")) || isString(jsonObject.get("bookAuthor")) || isString(jsonObject.get("bookDetails")) || isDouble(jsonObject.get("price")) || isInteger(jsonObject.get("owner")) || isString(jsonObject.get("bookRef"))
                    || isInteger(jsonObject.get("status")) || isString(jsonObject.get("createTime")) || isString(jsonObject.get("updateTime"))) {
            return Result.error(500001,"信息输入有误");
        }

        // 处理JSON对象，从请求体中读取参数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer bookId = Integer.valueOf(jsonObject.get("bookId").toString());
        String bookName = String.valueOf(jsonObject.get("bookName"));
        String bookAuthor = String.valueOf(jsonObject.get("bookAuthor"));
        String bookDetails = String.valueOf(jsonObject.get("bookDetails"));
        Double price = Double.valueOf(jsonObject.get("price").toString());
        if (price<=0){
            return Result.error(500001,"信息输入有误");
        }
        Integer owner = Integer.valueOf(jsonObject.get("owner").toString());
        //String ownerName = (String) jsonObject.get("ownerName");
        Integer status = Integer.valueOf(jsonObject.get("status").toString());
        Date createTime = null;
        try {
            createTime = sdf.parse(String.valueOf(jsonObject.get("createTime")));
        } catch (ParseException e) {
            throw new ServiceException("获取添加时间失败");
        }
        Date updateTime = null;
        try {
            updateTime = sdf.parse(String.valueOf(jsonObject.get("updateTime")));
        } catch (ParseException e) {
            throw new ServiceException("获取修改时间失败");
        }
        String bookRef = String.valueOf(jsonObject.get("bookRef"));

        // 封装到BookVo类中
        BookVo bookVo = new BookVo();
        bookVo.setBookId(bookId);
        bookVo.setBookName(bookName);
        bookVo.setBookAuthor(bookAuthor);
        bookVo.setBookDetails(bookDetails);
        bookVo.setPrice(price);
        bookVo.setStatus(status);
        bookVo.setCreateTime(createTime);
        bookVo.setUpdateTime(updateTime);
        bookVo.setBookRef(bookRef);
        bookVo.setOwner(owner);
        //bookVo.setOwnerName(ownerName);
        // 执行更新方法，得到改变行数的返回值
        Integer i = bookService.updateBook(bookVo);
        Result result = new Result(500002,null);
        if (i > 0) {
            return result.success(null);
        }
        else{
            return result.error(500005,"图书信息修改失败");
        }
    }

    /**
     * 通过BookId查询书籍信息
     * @param request
     * @param response
     * @return
     */
    private Result queryOne(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("bookId") != null) {
            Integer bookId = Integer.valueOf(request.getParameter("bookId").toString());
            BookVo bookVo = bookService.selectBookById(bookId);
            if (bookVo != null) {
                return Result.success(bookVo);
            } else {
                return Result.error(500009, "没有找到该书");
            }
        }
        return Result.error(500001, "信息输入有误");
    }

    /**
     * 查询所有书籍(分页查询)
     * 传入 bookName & userId(书籍拥有者)[可以都传，也可以传入单个参数 | 调用前先判断参数是都为空(是否有效) | 为空跳过此参数 ]
     * param: bookName userId cur[不为空] size[不为空]   cur[当前页数] size[每页数量]
     *  @return list<BookVo>
     */
    private Result list(HttpServletRequest request, HttpServletResponse response) {
        if(request.getParameter("cur") == null || request.getParameter("size") == null || Integer.parseInt(request.getParameter("cur")) <= 0 || Integer.parseInt(request.getParameter("size")) <= 0){
            return Result.error(500001,"信息错误");
        }
        //接受请求参数
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));
        //将参数传递给服务层，进行分页查询
        PageUtil<BookVo> bookVoPageUtil = new PageUtil<BookVo>();
        if(request.getParameter("userId")==null &&request.getParameter("bookName")==null){
            bookVoPageUtil = bookService.selectAllList(cur,size);
            return Result.success(bookVoPageUtil);
        } else if (request.getParameter("userId")==null &&request.getParameter("bookName")!=null) {
            String bookName = request.getParameter("bookName").toString();
            bookVoPageUtil = bookService.selectBookByBookName(cur,size,bookName);
            return Result.success(bookVoPageUtil);
        } else if (request.getParameter("userId")!=null &&request.getParameter("bookName")==null) {
            Integer userId = Integer.valueOf(request.getParameter("userId").toString());
            bookVoPageUtil = bookService.selectListByOwnerId(cur,size,userId);
            return Result.success(bookVoPageUtil);
        } else{
            Integer userId = Integer.valueOf(request.getParameter("userId").toString());
            String bookName = request.getParameter("bookName").toString();
            bookVoPageUtil = bookService.selectList(cur,size,userId,bookName);
            return Result.success(bookVoPageUtil);
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
        System.out.println(request.getParameter("bookId"));
        if(request.getParameter("bookId")!=null){
            Integer bookId = Integer.valueOf(request.getParameter("bookId").toString());
            Integer i = bookService.deleteBook(bookId);
            response.setCharacterEncoding("UTF-8");
            if (i > 0) {
                return Result.success(null);
            }
            else{
                return Result.error(500009,"图书信息删除失败");
            }
        }
        return Result.error(500001,"信息输入有误");
    }

    /**
     * 这是一个直接从请求体拿数据转换为JSON对象的工具类
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    public static JSONObject tool (HttpServletRequest request, HttpServletResponse response) {
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
                if (!((line = br.readLine()) != null)) {
                    break;
                }
            } catch (IOException e) {
                throw new ServiceException("读取文本信息失败");
            }
            sb.append(line);
        }
        String requestBody = sb.toString();
        // 使用JSON获取参数名对应的参数值
        JSONObject jsonObject = JSON.parseObject(requestBody);
        return jsonObject;
    }

}
