package tyut.selab.bookservice.service;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.vo.BookVo;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;

/**
 * @className: BookService
 * @author: lizhichao
 * @description: TODO  书籍信息 service 接口
 * @date: 2024/5/4 11:49
 * @version: 1.0
 */
public interface BookService {


    /**
     *  增加书籍
     * @param  bookDto
     * @return
     */

    public Integer insertBook(BookDto bookDto);

    /**
     *   修改书籍信息(管理员操作)
     * @param bookVo
     * @return
     */
    public Integer updateBook(BookVo bookVo);

    /**
     *  删除书籍
     * @param bookId
     * @return
     */
    public Integer deleteBook(Integer bookId);

    /**
     * 分页查询所有书籍
     * 传入userId和bookName
     * @return
     */
    public List<BookVo> selectList(Integer cur, Integer size, Integer userId, String bookName);

    /**
     *   通过 bookId 查询书籍信息
     * @param bookId 书籍id
     * @return
     */
    public BookVo selectBookById(Integer bookId);

    /**
     *   通过书籍名称查询书籍信息(支持模糊查询)
     * @param bookName
     * @return
     */
    public List<BookVo> selectBookByBookName(Integer cur, Integer size, String bookName);

    /**
     *  通过userid查询用户所拥有的所有书籍
     * @return
     */
    public List<BookVo> selectListByOwnerId(Integer cur,Integer size,Integer userId);

    /**
     * 分页查询所有书籍信息
     * @param
     * @return
     */
    public List<BookVo> selectAllList(Integer cur, Integer size);

    public BookVo bookIofoToBookVo(BookInfo bookInfo);
}
