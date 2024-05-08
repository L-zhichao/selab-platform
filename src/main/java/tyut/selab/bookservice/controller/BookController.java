package tyut.selab.bookservice.controller;

import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.service.impl.BookServiceImpl;
import tyut.selab.bookservice.utils.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @className: BookController
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:27
 * @version: 1.0
 */
@WebServlet(name = "BookController",urlPatterns = {})
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

    private Result<Void> save(HttpServletRequest request, HttpServletResponse response){
      return null;
    }

    private Result update(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**
     *  通过BookId查询书籍信息
     *  传入 bookId & bookName & userId(书籍拥有者)[可以都传，也可以传入单个参数 | 调用前先判断参数是都为空(是否有效) | 为空跳过此参数 ]
     * @return
     */
    private Result queryOne(HttpServletRequest request,HttpServletResponse response){
        Integer bookId = Integer.valueOf(request.getAttribute("bookId").toString());
        Integer userId = Integer.valueOf(request.getAttribute("userId").toString());
        String bookName = request.getAttribute("bookName").toString();

        return null;
    }

    /**
     *  查询所有书籍(分页查询)
     *  param: cur[当前页数] size[每页数量]
     * @param request
     * @param response
     * @return
     */
    private Result list(HttpServletRequest request,HttpServletResponse response){
        return null;
    }

    /**  删除书籍
     *   param: bookId
     * @param request
     * @param response
     * @return
     */
    private Result delect(HttpServletRequest request,HttpServletResponse response){return null;}


}
