package tyut.selab.bookservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.exception.ServiceException;
import tyut.selab.bookservice.service.BorrowService;
import tyut.selab.bookservice.service.impl.BorrowServiceImpl;
import tyut.selab.bookservice.vo.BorrowBookVo;
import tyut.selab.utils.Result;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("编码为UTF—8失败");
        }
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
                result = queryById(req, resp);
            } else {
                result.setCode(404);
                result.setData(null);
                result.setMsg("fail");
            }
        }
        resp.setContentType("application/json");
        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            throw new ServiceException("获取Writer失败");
        }
        writer.print(jsonString);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ServiceException("编码为UTF—8失败");
        }
        resp.setCharacterEncoding("UTF-8");
        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        Result result = new Result(404,null);
        if (methodName.equals("borrow")) {

            result = borrowingBook(req, resp);
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
        PrintWriter writer = null;
        try {
            writer = resp.getWriter();
        } catch (IOException e) {
            throw new ServiceException("获取Writer失败");
        }
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
    private Result borrowingBook(HttpServletRequest request, HttpServletResponse response) {
        // HttpServletRequest对象中获取输入流，并读取请求正文。
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = request.getReader();
        } catch (IOException e) {
            throw new ServiceException("读取失败");
        }
        String line;
        while (true) {
            try {
                if ((line = reader.readLine()) == null) break;
            } catch (IOException e) {
                throw new ServiceException("读取失败");
            }
            buffer.append(line);
        }
        String requestBody = buffer.toString();
        // 使用JSON获取参数名对应的参数值
        JSONObject jsonObject = JSON.parseObject(requestBody);
        Result result = new Result(404, null);
        if (jsonObject.get("bookId") == null || jsonObject.get("borrowDuration") == null || jsonObject.get("returnTime") == null) {
            result.setMsg("信息缺少");
            return result;
        }
        Integer bookId = (Integer) jsonObject.get("bookId");
        Integer borrowDuration = (Integer) jsonObject.get("borrowDuration");
        String returnTime = (String) jsonObject.get("returnTime");

        result.setMsg("借阅失败");

        if(borrowDuration <=0 || bookId == 0 ){
            result.setMsg("无效借阅");
            result.setCode(512);
            return result;
        }
        //将参数封装为Dto类
        BorrowBookDto borrowBookDto = new BorrowBookDto();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = ft.parse(returnTime);
        } catch (ParseException e) {
            throw new ServiceException("获取异常失败");
        }
        borrowBookDto.setBookId(bookId);
        borrowBookDto.setBorrowDuration(borrowDuration);
        borrowBookDto.setReturnTime(date);
        ////将参数传递给服务层，处理
        Integer i = borrowService.borrowBook(borrowBookDto);
        if (i > 0 ) {
            result = Result.success(null);
        } else if (i == -2) {
            result.setMsg("已被他人借阅");
            result.setCode(508);
        } else if (i == -3) {
            result.setMsg("不可借阅");
            result.setCode(509);
        } else if (i == -4) {
            result.setMsg("您已借阅");
            result.setCode(510);
        }else if (i == -5) {
            result.setMsg("没有找到该书");
            result.setCode(511);
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
        Result result = new Result(404,null);
        if (split[split.length - 1] == null){
            result.setMsg("BookId为空");
            return result;
        }
        Integer borrowId = Integer.valueOf(split[split.length - 1]);
        result.setMsg("借阅失败");
        if (borrowId != 0){
          Integer i = borrowService.returnBook(borrowId);
            if(i > 0){
                result = Result.success(null);
            } else if (i == -1) {
                result.setCode(512);
                result.setMsg("没有借阅记录");
            } else if (i == -2) {
                result.setCode(513);
                result.setMsg("此书不是您借的");
            } else if (i == -3) {
                result.setCode(514);
                result.setMsg("您已归还");
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
        Result result = new Result(404, null);
        //接收请求参数
        if (request.getParameter("cur") == null || request.getParameter("size") == null){
            result.setMsg("存在信息为空");
            return result;
        }
        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));

        if (cur <= 0 || size <= 0){
            result.setCode(514);
            result.setMsg("信息错误");
            return result;
        }
//        Integer bookId = Integer.valueOf();
//        Integer userId = Integer.valueOf(request.getParameter("userId"));
        //将参数传递给服务层，进行查询

        if (request.getParameter("bookId") == null && request.getParameter("userId") == null){
            List<BorrowBookVo> borrowBookVos = borrowService.selectList(cur, size);
            //将分页查询的结果传入Result对象中request.getParameter("bookId")
            if (!borrowBookVos.isEmpty()) {
                result.setCode(200);
                result.setData(borrowBookVos);
                result.setMsg("success");
            }else {
                result.setCode(513);
                result.setMsg("查询信息为空");
            }
        }else if (request.getParameter("bookId") != null && request.getParameter("userId") == null){
            Integer bookId = Integer.valueOf(request.getParameter("bookId"));
            List<BorrowBookVo> borrowBookVos = borrowService.selectListByBookId(bookId,cur,size);
            if (!borrowBookVos.isEmpty()) {
                result.setCode(200);
                result.setData(borrowBookVos);
                result.setMsg("success");
            }else {
                result.setCode(513);
                result.setMsg("查询信息为空");
            }
        } else if (request.getParameter("userId") != null && request.getParameter("bookId") == null) {
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            List<BorrowBookVo> borrowBookVos = borrowService.selectListByUserid(userId, cur, size);
            if (!borrowBookVos.isEmpty()) {
                result.setCode(200);
                result.setData(borrowBookVos);
                result.setMsg("success");
            }else {
                result.setCode(513);
                result.setMsg("查询信息为空");
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
        Result result = new Result(404, null);
        //接受请求参数
        if (request.getParameter("cur") == null || request.getParameter("size") == null){
            result.setMsg("页码或每页数量为空");
            return result;
        }

        int cur = Integer.parseInt(request.getParameter("cur"));
        int size = Integer.parseInt(request.getParameter("size"));

        if (cur <= 0 || size <= 0){
            result.setCode(514);
            result.setMsg("信息错误");
            return result;
        }

        List<BorrowBookVo> borrowBookVos = borrowService.selectAllForNoReturn(cur, size);

        if(borrowBookVos.isEmpty()) {
            result.setMsg("查询信息为空");
            result.setData(null);
            result.setCode(513);
        }

        result = Result.success(borrowBookVos);

        return result;
    }

    /**
     *  分页查询本人借阅书籍记录
     *  param: cur size (userId 通过登录模块获取)
     * @param request
     * @param response
     * @return
     */
    private Result queryBorrowLog(HttpServletRequest request,HttpServletResponse response){return null;}

    private Result queryById(HttpServletRequest request,HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        Integer borrowId = Integer.valueOf(split[split.length - 1]);
        BorrowBookVo borrowBookVo = borrowService.selectByBorrowId(borrowId);
        Result result = new Result(404,null);
        if(borrowBookVo != null){
            result.setCode(200);
            result.setData(borrowBookVo);
            result.setMsg("信息查询成功");
        }else{
            result.setMsg("查询信息为空");
            result.setData(null);
            result.setCode(513);
        }
        return result;
    }
}
