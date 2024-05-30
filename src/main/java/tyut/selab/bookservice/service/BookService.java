package tyut.selab.bookservice.service;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.utils.PageUtil;

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
     * 分页查询所有书籍
     *
     * @return
     */
    public PageUtil<BookVo> selectList(Integer cur, Integer size, Integer userId, String bookName);

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
    public PageUtil<BookVo> selectBookByBookName(Integer cur, Integer size, String bookName);

    /**
     *  通过userid查询用户所拥有的所有书籍
     * @return
     */
    public PageUtil<BookVo> selectListByOwnerId(Integer userid,Integer cur,Integer size);

    public BookVo bookIofoToBookVo(BookInfo bookInfo);

    public Integer deleteBook(Integer bookId);

    public PageUtil<BookVo> selectAllList(Integer cur,Integer size);
}
