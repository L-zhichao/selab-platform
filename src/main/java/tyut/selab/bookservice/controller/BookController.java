package tyut.selab.bookservice.controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.utils.Result;

import java.io.IOException;

/**
 * @className: BookController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:27
 * @version: 1.0
 */

@WebServlet(name = "BookController",urlPatterns = {"/book/save","/book/update","/book/query","/book/list"})
public class BookController extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
    private Result list(HttpServletRequest request, HttpServletResponse response) {
        return null;
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
