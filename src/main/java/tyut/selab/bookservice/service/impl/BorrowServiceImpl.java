package tyut.selab.bookservice.service.impl;

import tyut.selab.bookservice.domain.BorrowBook;
import tyut.selab.bookservice.service.BorrowService;

import java.util.List;

/**
 * @className: BorrowServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 11:05
 * @version: 1.0
 */
public class BorrowServiceImpl implements BorrowService {
    @Override
    public Integer borrowBook(Integer bookId) {
        return null;
    }

    @Override
    public Integer returnBook(Integer bookId) {
        return null;
    }

    @Override
    public List<BorrowBook> selectListByUserid(Integer userId) {
        return null;
    }

    @Override
    public List<BorrowBook> selectListByBookId(Integer bookId) {
        return null;
    }

    @Override
    public List<BorrowBook> selectList() {
        return null;
    }

    @Override
    public List<BorrowBook> selectAllForNoReturn() {
        return null;
    }
}
