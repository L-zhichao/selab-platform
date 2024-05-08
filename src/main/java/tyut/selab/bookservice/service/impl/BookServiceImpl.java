package tyut.selab.bookservice.service.impl;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.service.BookService;

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
    public Integer insertBook(BookInfo bookInfo) {
        return null;
    }

    @Override
    public Integer updateBook(BookInfo bookInfo) {
        return null;
    }

    @Override
    public List<BookInfo> selectList(Integer cur,Integer size) {
        return null;
    }

    @Override
    public BookInfo selectBookById(Integer bookId) {
        return null;
    }

    @Override
    public BookInfo selectBookByBookName(String bookName) {
        return null;
    }

    @Override
    public List<BookInfo> selectListByOwnerId(Integer userid) {
        return null;
    }
}
