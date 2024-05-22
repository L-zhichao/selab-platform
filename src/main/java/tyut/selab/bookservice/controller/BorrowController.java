package tyut.selab.bookservice.controller;

import tyut.selab.utils.Result;

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
@WebServlet(name = "BorrowController",urlPatterns = {"/borrow/book","/borrow/return/*","/borrow/record","/borrow/my","borrow/noReturn"})
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
    private Result borrowingBook(HttpServletRequest request, HttpServletResponse response){
        return null;
    }

    /**
     *  归还书籍
     *  path param: bookId (路径变量)
     * @return
     */
    private Result returnBook(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  分页查询借阅信息
     *  param: bookId | userId | borrowId [三选一或全为空，先进行非空判断确定查询参数]，选择相应的方法  全为空是表示查询全部
     *  param: [必须] cur size <当前页数> <每页数量>
     * @param request
     * @param response
     * @return BorrowBookVo对象
     */
    private Result query(HttpServletRequest request,HttpServletResponse response){
        return null;
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
}
