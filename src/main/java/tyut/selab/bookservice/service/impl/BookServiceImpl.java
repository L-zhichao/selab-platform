package tyut.selab.bookservice.service.impl;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.vo.BookVo;

import java.util.List;

/**
 * @className: BookServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 17:26
 * @version: 1.0
 */
public class BookServiceImpl implements BookService {
    @Override
    public Integer insertBook(BookDto bookDto) {
        return null;
    }

    @Override
    public Integer updateBook(BookVo bookVo) {
        return null;
    }



    @Override
    public List<BookVo> selectList(Integer cur, Integer size) {
        return null;
    }

    @Override
    public BookVo selectBookById(Integer bookId) {
        return null;
    }

    @Override
    public BookVo selectBookByBookName(String bookName) {
        return null;
    }

    @Override
    public List<BookVo> selectListByOwnerId(Integer userid, Integer cur, Integer size) {
        return null;
    }

}
