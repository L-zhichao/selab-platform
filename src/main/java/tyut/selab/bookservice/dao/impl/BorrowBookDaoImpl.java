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
    private BaseDao baseDao = new BaseDao();
    @Override
    public Integer insert(BorrowBook borrowBook) {
        String sql = "insert into book_borrow (borrow_id,book_id,borrow_user,borrow_duration,status,borrow_time,return_time) values (default,?,?,?,0,now(),?);";
        Object[] params = {borrowBook.getBookId(),borrowBook.getBorrowUser(),borrowBook.getBorrowDuration(),borrowBook.getReturnTime()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer update(BorrowBook borrowBook) {
        String sql ="update book_borrow set borrow_id = ?,book_id = ?,borrow_user = ?,borrow_duration = ?,status = ?,borrow_time = ?,return_time = ? where book_id = ?;";
        Object[] params = {borrowBook.getBorrowId(),borrowBook.getBookId(),borrowBook.getBorrowUser(),borrowBook.getBorrowDuration(),borrowBook.getStatus(),borrowBook.getBorrowTime(),borrowBook.getReturnTime(),borrowBook.getBookId()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public BorrowBook selectByBorrowIdBorrowBook(Integer borrowId) {
        String sql = "select borrow_Id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow where borrow_id = ?";
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, borrowId);
        return borrowBooks.get(0);
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
    public List<BorrowBook> selectAllForNoReturn(Integer cur,Integer size){
        String sql = "select borrow_id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow where status = 0 limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql,index,size);
        return borrowBooks;
    }

    @Override
    public List<BorrowBook> selectAllByBookId(Integer bookId,Integer cur,Integer size) {
        String sql = "select borrow_id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow where book_id = ? limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, bookId , index ,size);
        return borrowBooks;
    }
    @Override
    public List<BorrowBook> selectAllByBookId(Integer bookId){
        String sql = "select borrow_id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow where book_id = ?;";
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, bookId);
        return borrowBooks;
    }
    @Override
    public List<BorrowBook> selectAllByUserId(Integer userId,Integer cur,Integer size) {
        String sql = "select borrow_id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow where borrow_user = ? limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, userId, index, size);
        return borrowBooks;
    }
    @Override
    public List<BorrowBook> selectAll(Integer cur,Integer size) {
        String sql = "select borrow_id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow limit ?,?;";
        int index = (cur - 1) * size;
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, index, size);
        return borrowBooks;
    }

    @Override
    public Integer selectAllCountByUserId(Integer userId) {
        String sql = "select count(*) from book_borrow where borrow_user = ?;";
        return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql, userId)));
    }

    @Override
    public Integer selectAllCountByBookId(Integer bookId) {
        String sql = "select count(*) from book_borrow where book_id = ?;";
        return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql, bookId)));
    }

    @Override
    public Integer selectAllCount() {
        String sql = "select count(*) from book_borrow;";
        return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class, sql)));
    }

    @Override
    public Integer selectAllCountForNoReturn() {
        String sql = "select count(*) from book_borrow where status = 0;";
        return Integer.parseInt(String.valueOf((baseDao.baseQueryObject(Long.class,sql))));
    }

    @Override
    public List<BorrowBook> selectAllByBorrowId(Integer borrowId) {
        String sql = "select borrow_id borrowId,book_id bookId,borrow_user borrowUser,borrow_duration borrowDuration,status,borrow_time borrowTime,return_time returnTime from book_borrow where borrow_id = ?;";
        List<BorrowBook> borrowBooks = baseDao.baseQuery(BorrowBook.class, sql, borrowId);
        return borrowBooks;
    }
}
