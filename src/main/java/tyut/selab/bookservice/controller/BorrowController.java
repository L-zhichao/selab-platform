package tyut.selab.bookservice.controller;

import tyut.selab.bookservice.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: BorrowController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:40
 * @version: 1.0
 */
@WebServlet(name = "BorrowController",urlPatterns = {})
public class BorrowController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    /**
     *  借阅书籍
     *  param: BorrowBookDto对象
     * @return
     */
    private Result borrowingBook(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  归还书籍
     *  param: bookId
     * @return
     */
    private Result returnBook(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  查询借阅信息
     *  param: bookId | userId | borrowId [三选一，先进行非空判断确定查询参数]，选择相应的方法
     * @param request
     * @param response
     * @return
     */
    private Result queryOne(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  分页查询所有借阅信息
     *  param: cur[当前页] size[每页数量]
     * @param request
     * @param response
     * @return
     */
    private Result queryAll(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  查询所有未归还书籍
     *  param: null
     * @param request
     * @param response
     * @return
     */
    private Result queryAllNoReturnBook(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  查询本人借阅书籍记录
     *  param: null (userId 通过登录模块获取)
     * @param request
     * @param response
     * @return
     */
    private Result queryBorrowLog(HttpServletRequest request,HttpServletResponse response){return null;}
}
