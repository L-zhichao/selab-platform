package tyut.selab.bookservice.dao.impl;

import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.domain.BookInfo;

import java.util.List;

/**
 * @className: BookInfoDaoImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 12:03
 * @version: 1.0
 */
public class BookInfoDaoImpl implements BookInfoDao {
    @Override
    public Integer insert(BookInfo bookInfo) {
        return null;
    }

    @Override
    public Integer update(BookInfo bookInfo) {
        return null;
    }

    @Override
    public BookInfo selectByBookIdBookInfo(Integer bookId) {
        return null;
    }

    @Override
    public List<BookInfo> selectByOwnerBookInfo(Integer userId) {
        return null;
    }

    @Override
    public List<BookInfo> selectAll() {
        return null;
    }

    @Override
    public List<BookInfo> selectAllByBookName() {
        return null;
    }
}
