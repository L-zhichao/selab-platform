package tyut.selab.bookservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.service.BorrowService;
import tyut.selab.bookservice.service.impl.BorrowServiceImpl;
import tyut.selab.bookservice.vo.BorrowBookVo;
import tyut.selab.utils.Result;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @className: BorrowController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:40
 * @version: 1.0
 */
@WebServlet(name = "BorrowController",urlPatterns = {"/book/borrow","/book/return/*","/borrow/record","/borrow/my","/borrow/noReturn","/borrow/queryById/*"})
public class BorrowController extends HttpServlet {
    private BorrowService borrowService = new BorrowServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        String methodName2 = split[split.length - 2];
        Result result = null;
//        if (methodName.equals("return")) {
//            result = returnBook(req, resp);
//        }else if
        if(methodName.equals("record")){
            result = query(req, resp);
        }else if (methodName.equals("my")){
            result = queryBorrowLog(req, resp);
        }else if (methodName.equals("noReturn")){
            result = queryAllNoReturnBook(req,resp);
        }else {
            if (methodName2.equals("return")){
                result = returnBook(req, resp);
            } else if (methodName2.equals("queryById")) {
                result = qurtyById(req, resp);
            } else {
                result.setCode(404);
                result.setData(null);
                result.setMsg("fail");
            }
        }
        resp.setContentType("application/json");
        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        Result result = null;
        if (methodName.equals("borrow")) {
            try {
                result = borrowingBook(req, resp);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
//        } else if (methodName.equals("return")) {
//            result = returnBook(req, resp);
//        } else if (methodName.equals("record")) {
//            result = query(req, resp);
        } else {
            result.setCode(404);
            result.setData(null);
            result.setMsg("路径有误");
        }
        resp.setContentType("application/json");
        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.flush();
        writer.close();
    }

    /**
     *  借阅书籍
     *  param: BorrowBookDto对象
     * @return
     * 闫焕根
     */
    private Result borrowingBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        // HttpServletRequest对象中获取输入流，并读取请求正文。
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }
        String requestBody = buffer.toString();
        // 使用JSON获取参数名对应的参数值
        JSONObject jsonObject = JSON.parseObject(requestBody);
        Integer bookId = (Integer) jsonObject.get("bookId");
        Integer borrowDuration = (Integer) jsonObject.get("borrowDuration");
        String returnTime = (String) jsonObject.get("returnTime");

        Result result = new Result(404, null);
        result.setMsg("借阅失败");

        if(borrowDuration <=0){
            result.setMsg("你可能在开玩笑");
            result.setCode(510);
            return result;
        }
        //将参数封装为Dto类
        BorrowBookDto borrowBookDto = new BorrowBookDto();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = ft.parse(returnTime);
        borrowBookDto.setBookId(bookId);
        borrowBookDto.setBorrowDuration(borrowDuration);
        borrowBookDto.setReturnTime(date);
        ////将参数传递给服务层，处理
        Integer i = borrowService.borrowBook(borrowBookDto);
        if (i > 0 ) {
            result = Result.success(null);
        } else if (i == -2) {
            result.setMsg("已被借阅");
            result.setCode(508);
        } else if (i == -3) {
            result.setMsg("不可借阅");
            result.setCode(509);
        }
        return result;
    }

    /**
     *  归还书籍
     *  path param: bookId (路径变量)
     * @return
     * 闫焕根
     */
    private Result returnBook(HttpServletRequest request,HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        Integer borrowId = Integer.valueOf(split[split.length - 1]);
        Result result = new Result(404,null);
        result.setMsg("fail");
        if (borrowId != null){
          Integer i = borrowService.returnBook(borrowId);
            if(i > 0){
                result = Result.success(null);
            }
        }
        return result;
    }

    /**
     *  分页查询借阅信息
     *  param: bookId | userId | borrowId [三选一或全为空，先进行非空判断确定查询参数]，选择相应的方法  全为空是表示查询全部
     *  param: [必须] cur size <当前页数> <每页数量>
     * @param request
     * @param response
     * @return BorrowBookVo对象
     * 闫焕根
     */
    private Result query(HttpServletRequest request,HttpServletResponse response){
        //接收请求参数
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));
        String bookId = request.getParameter("bookId");
        String userId = request.getParameter("userId");

        //将参数传递给服务层，进行查询
        Result result = new Result(404, null);
        if (bookId == null && userId == null){
            List<BorrowBookVo> borrowBookVos = borrowService.selectList(cur, size);
            //将分页查询的结果传入Result对象中
            if (!borrowBookVos.isEmpty()) {
                result.setCode(200);
                result.setData(borrowBookVos);
                result.setMsg("success");
            }
        }else if (bookId != null && userId == null){
            List<BorrowBookVo> borrowBookVos = borrowService.selectListByBookId(Integer.parseInt(bookId), cur, size);
            if (!borrowBookVos.isEmpty()) {
                result.setCode(200);
                result.setData(borrowBookVos);
                result.setMsg("success");
            }
        } else if (userId != null && bookId == null) {
            List<BorrowBookVo> borrowBookVos = borrowService.selectListByUserid(Integer.parseInt(userId), cur, size);
            if (!borrowBookVos.isEmpty()) {
                result.setCode(200);
                result.setData(borrowBookVos);
                result.setMsg("success");
            }
        }
        return result;
    }


    /**
     *  分页查询所有未归还书籍
     *  param: cur size
     * @param request
     * @param response
     * @return
     */
    private Result queryAllNoReturnBook(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  分页查询本人借阅书籍记录
     *  param: cur size (userId 通过登录模块获取)
     * @param request
     * @param response
     * @return
     */
    private Result queryBorrowLog(HttpServletRequest request,HttpServletResponse response){return null;}

    private Result qurtyById(HttpServletRequest request,HttpServletResponse response){
        return null;
    }
}
