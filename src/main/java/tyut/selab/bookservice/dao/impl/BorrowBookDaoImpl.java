package tyut.selab.bookservice.dao.impl;

import tyut.selab.bookservice.dao.BorrowBookDao;
import tyut.selab.bookservice.domain.BorrowBook;

import java.util.List;

/**
 * @className: BorrowBookDaoImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 12:04
 * @version: 1.0
 */
public class BorrowBookDaoImpl implements BorrowBookDao {
    @Override
    public Integer insert(BorrowBook borrowBook) {
        return null;
    }

    @Override
    public Integer update(BorrowBook borrowBook) {
        return null;
    }

    @Override
    public BorrowBook selectByBorrowIdBorrowBook(Integer borrowId) {
        return null;
    }

    @Override
    public List<BorrowBook> selectAllByBorrowUserForNoReturn(Integer userId) {
        return null;
    }

    @Override
    public List<BorrowBook> selectAllByBookId(Integer bookId) {
        return null;
    }
}
