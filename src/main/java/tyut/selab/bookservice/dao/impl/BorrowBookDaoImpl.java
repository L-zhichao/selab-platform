package tyut.selab.bookservice.dao.impl;

import tyut.selab.bookservice.dao.BaseDao;
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
    BaseDao baseDao = new BaseDao();
    @Override
    public Integer insert(BorrowBook borrowBook) {
        String sql = "insert into book_borrow (borrow_id,book_id,borrow_user,borrow_duration,status,borrow_time,return_time) values (default,?,?,?,0,now(),?);";
        Object[] params = {borrowBook.getBookId(),borrowBook.getBorrowUser(),borrowBook.getBorrowDuration(),borrowBook.getReturnTime()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer update(BorrowBook borrowBook) {
        String sql ="update book_borrow set borrow_duration = ?,borrow_time = ? where book_id = ?;";
        Object[] params = {borrowBook.getBorrowDuration(),borrowBook.getReturnTime(),borrowBook.getBookId()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public BorrowBook selectByBorrowIdBorrowBook(Integer borrowId) {
        String sql = "select book_id bookId,borrow_duration borrowDuration,return_time returnTime from book_borrow where borrow_id = ?";
        return (BorrowBook) baseDao.baseQuery(BorrowBook.class,sql,borrowId);
    }

    @Override
    public List<BorrowBook> selectAllByBorrowUserForNoReturn(Integer userId) {
        return null;
    }

//    public List<BorrowBook> selectAllByBookId(Integer bookId) {
//        String sql = "select book_id bookId,borrow_duration borrowDuration,return_time returnTime from book_borrow where book_id = ?;";
//        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, bookId);
//        return borrowBooks;
//    }

    @Override
    public List<BorrowBook> selectAllByBookId(Integer bookId,Integer cur,Integer size) {
        String sql = "select book_id bookId,borrow_duration borrowDuration,return_time returnTime from book_borrow where book_id = ? limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, bookId, index, size);
        return borrowBooks;
    }
    public List<BorrowBook> selectAllByUserId(Integer userId,Integer cur,Integer size) {
        String sql = "select book_id bookId,borrow_duration borrowDuration,return_time returnTime from book_borrow where borrow_user = ? limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, userId, index, size);
        return borrowBooks;
    }

    public List<BorrowBook> selectAll(Integer cur,Integer size) {
        String sql = "select book_id bookId,borrow_duration borrowDuration,return_time returnTime from book_borrow where limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, index, size);
        return borrowBooks;
    }
}
