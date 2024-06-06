package tyut.selab.bookservice.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.exception.ServiceException;
import tyut.selab.bookservice.service.BorrowService;
import tyut.selab.bookservice.service.impl.BorrowServiceImpl;
import tyut.selab.bookservice.vo.BorrowBookVo;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.utils.PageUtil;
import tyut.selab.utils.Result;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];

        Result result = null;

        if(methodName.equals("record")){
            result = query(req, resp);
        }else if (methodName.equals("my")){
            result = queryBorrowLog(req, resp);
        }else if (methodName.equals("noReturn")){
            result = queryAllNoReturnBook(req,resp);
        }else {

            String methodName2 = split[split.length - 2];

            if (methodName2.equals("return")){
                result = returnBook(req, resp);
            } else if (methodName2.equals("queryById")) {
                result = queryById(req, resp);
            } else {
                result = Result.error(500004, "路径有误");
            }
        }

        String jsonString = JSON.toJSONString(result);
        PrintWriter writer = resp.getWriter();
        writer.print(jsonString);
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=utf-8");

        String requestURI = req.getRequestURI();
        String[] split = requestURI.split("/");
        String methodName = split[split.length - 1];
        Result result = null;
        if (methodName.equals("borrow")) {
            try {
                result = borrowingBook(req, resp);
            } catch (ParseException e) {
                throw new ServiceException("获取时间失败");
            }
        } else {
            result = Result.error(500004, "路径有误");
        }

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
     */
    private Result borrowingBook(HttpServletRequest request, HttpServletResponse response) throws ParseException {
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
                if ((line = reader.readLine()) == null) {
                    break;
                }
            } catch (IOException e) {
                throw new ServiceException("读取失败");
            }
            buffer.append(line);
        }
        String requestBody = buffer.toString();
        // 使用JSON获取参数名对应的参数值
        JSONObject jsonObject = JSON.parseObject(requestBody);
        Date nowDate = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");

        if (jsonObject.get("borrowDuration") == null) {
            return Result.error(500001, "borrowDuration为空");
        } else if (jsonObject.get("bookId") == null) {
            return Result.error(500001, "bookId为空");
        } else if (jsonObject.get("returnTime") == null) {
            return Result.error(500001, "returnTime为空");
        } else if (!(jsonObject.get("bookId").toString().matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "bookId不是大于0的整数");
        } else if (!((jsonObject.get("borrowDuration").toString()).matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "borrowDuration不是大于0的整数");
        } else if (nowDate.after(ft.parse((String) jsonObject.get("returnTime")))) {
            return Result.error(500001, "returnTime不是大于0的整数");
        }

        Integer bookId = Integer.valueOf(jsonObject.get("bookId").toString());
        Integer borrowDuration = Integer.valueOf(jsonObject.get("borrowDuration").toString());
        Date returnTime = ft.parse( String.valueOf(jsonObject.get("returnTime")));


        //将参数封装为Dto类
        BorrowBookDto borrowBookDto = new BorrowBookDto();

        borrowBookDto.setBookId(bookId);
        borrowBookDto.setBorrowDuration(borrowDuration);
        borrowBookDto.setReturnTime(returnTime);

        ////将参数传递给服务层，处理
        Integer i = borrowService.borrowBook(borrowBookDto);
        if (i > 0 ) {
            return Result.success(null);
        } else if (i == -2) {
            return Result.error(500006,"已被他人借阅");
        } else if (i == -3) {
            return Result.error(500007,"不可借阅");
        } else if (i == -4) {
            return Result.error(500008,"您已借阅");
        }else if (i == -5) {
            return Result.error(500009, "没有找到该书");
        }else {
            return Result.error(500001,"信息错误");
        }
    }

    /**
     *  归还书籍
     *  path param: bookId (路径变量)
     * @return
     */
    private Result returnBook(HttpServletRequest request,HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        if (split[split.length - 1] == null || !(split[split.length - 1].matches("^[1-9]\\d*$"))){
            return Result.error(500001,"bookId不是大于0的整数");
        }

        Integer borrowId = Integer.valueOf(split[split.length - 1]);

        Integer i = borrowService.returnBook(borrowId);

        if(i > 0){
            return Result.success(null);
        } else if (i == -1) {
            return Result.error(500010,"没有借阅记录");
        } else if (i == -2) {
            return Result.error(500011,"此书不是您借的");
        } else if (i == -3) {
            return Result.error(500012,"您已归还");
        }else {
            return Result.error(500001,"信息错误");
        }
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
        if (request.getParameter("cur") == null) {
            return Result.error(500001, "cur为空");
        } else if (request.getParameter("size") == null) {
            return Result.error(500001, "size为空");
        } else if (!(request.getParameter("cur").matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "cur不是大于0的整数");
        } else if (!(request.getParameter("size").matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "size不是大于0的整数");
        }
        //权限判断
        UserLocal user = SecurityUtil.getUser();
        if(user.getRoleId() == 2){
            return Result.error(500013,"权限不足");
        }


        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));

        //将参数传递给服务层，进行查询

        if (request.getParameter("bookId") == null && request.getParameter("userId") == null && request.getParameter("borrowId") == null){
            PageUtil<BorrowBookVo> borrowBookVoPageUtil = borrowService.selectList(cur, size);

            return Result.success(borrowBookVoPageUtil);

        }else if (request.getParameter("bookId") != null && request.getParameter("userId") == null && request.getParameter("borrowId") == null){
            if(!request.getParameter("bookId").matches("^[1-9]\\d*$")){
                return Result.error(500001,"bookId不是大于0的整数");
            }
            Integer bookId = Integer.valueOf(request.getParameter("bookId"));
            PageUtil<BorrowBookVo> borrowBookVoPageUtil = borrowService.selectListByBookId(bookId, cur, size);

            return Result.success(borrowBookVoPageUtil);

        } else if (request.getParameter("userId") != null && request.getParameter("bookId") == null && request.getParameter("borrowId") == null) {
            if ( !request.getParameter("userId").matches("^[1-9]\\d*$")){
                return Result.error(500001,"userId不是大于0的整数");
            }
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            PageUtil<BorrowBookVo> borrowBookVoPageUtil = borrowService.selectListByUserId(userId, cur, size);

            return Result.success(borrowBookVoPageUtil);
        } else if (request.getParameter("userId") == null && request.getParameter("bookId") == null && request.getParameter("borrowId") != null) {
            if (!request.getParameter("borrowId").matches("^[1-9]\\d*$")){
                return Result.error(500001,"borrowId不是大于0的整数");
            }
            Integer borrowId = Integer.valueOf(request.getParameter("borrowId"));
            BorrowBookVo borrowBookVo = borrowService.selectByBorrowId(borrowId);

            return Result.success(borrowBookVo);
        }else {
            return Result.error(500001,"信息错误");
        }
    }


    /**
     *  分页查询本人借阅书籍记录
     *  param: cur size (userId 通过登录模块获取)
     * @param request
     * @param response
     * @return
     * 宋晨语
     */

    private Result queryBorrowLog(HttpServletRequest request,HttpServletResponse response){
        if (request.getParameter("cur") == null) {
            return Result.error(500001, "cur为空");
        } else if (request.getParameter("size") == null) {
            return Result.error(500001, "size为空");
        } else if (!(request.getParameter("size").matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "size不是大于0的整数");
        } else if (!(request.getParameter("cur").matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "cur不是大于0的整数");
        }
        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));
        UserLocal user = SecurityUtil.getUser();
        Integer userId = user.getUserId();
        PageUtil<BorrowBookVo> borrowBookVoPageUtil = borrowService.selectListByUserId(userId, cur, size);

        return Result.success(borrowBookVoPageUtil);




    }
    private Result queryAllNoReturnBook(HttpServletRequest request,HttpServletResponse response){
        //接受请求参数
        if (request.getParameter("cur") == null) {
            return Result.error(500001, "cur为空");
        } else if (request.getParameter("size") == null) {
            return Result.error(500001, "size为空");
        } else if (!(request.getParameter("cur").matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "cur不是大于0的整数");
        } else if (!(request.getParameter("size").matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "size不是大于0的整数");
        }

        UserLocal user = SecurityUtil.getUser();

        if(user.getRoleId() ==2){
            return Result.error(500013,"权限不足");
        }

        Integer cur = Integer.valueOf(request.getParameter("cur"));
        Integer size = Integer.valueOf(request.getParameter("size"));

        PageUtil<BorrowBookVo> borrowBookVoPageUtil = borrowService.selectAllForNoReturn(cur, size);

        return Result.success(borrowBookVoPageUtil);
    }

    private Result queryById(HttpServletRequest request,HttpServletResponse response){
        String requestURI = request.getRequestURI();
        String[] split = requestURI.split("/");
        if (split[split.length - 1] == null) {
            return Result.error(500001, "borrowId为空");
        } else if (!(split[split.length - 1].matches("^[1-9]\\d*$"))) {
            return Result.error(500001, "borrowId不是大于0的整数");
        }

        Integer borrowId = Integer.valueOf(split[split.length - 1]);

        BorrowBookVo borrowBookVo = borrowService.selectByBorrowId(borrowId);

        return Result.success(borrowBookVo);
    }
}
