package tyut.selab.bookservice.dao.impl;

import tyut.selab.bookservice.dao.BaseDao;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.vo.BookVo;

import java.lang.reflect.Field;
import java.sql.Ref;
import java.sql.SQLException;
import java.util.List;

/**
 * @className: BookInfoDaoImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 12:03
 * @version: 1.0
 */
public class BookInfoDaoImpl extends BaseDao implements BookInfoDao {

    private BaseDao baseDao = new BaseDao();
    @Override
    public Integer insert(BookInfo bookInfo) {
        String sql = "insert into book_info (book_name bookName,book_author bookAuthor,book_details bookDetails,price price,owner owner,remark remark,book_ref bookRef) values (DEFAULT,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {bookInfo.getBookName(),bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getRemark(),bookInfo.getBookRef()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer update(BookInfo bookInfo){
        String sql = "update book_info set book_author=?,book_details=?,price=?,owner=?,status=? where book_name=?";
        Object[] params = {bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getStatus(),bookInfo.getBookName()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public BookInfo selectByBookIdBookInfo(Integer bookId) {
        String sql = "select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,remark,book_ref bookRef from book_info where book_id = ?";
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class, sql, bookId);
        return bookInfos.get(0);
    }

    @Override
    public List<BookInfo> selectByOwnerBookInfo(Integer userId) {
        return null;
    }

    @Override
    public List<BookInfo> selectAll(Integer cur, Integer size) {
        String sql = "select t1.book_id bookId,t1.book_name bookName,t1.book_author bookAuthor,t1.book_details bookDetails,t1.price,t1.owner owner,t1.status status,t1.create_time createTime,t1.update_time updateTime,t1.remark remark,t1.book_ref bookRef from Book_info t1 limit ";
        int index = (cur - 1) * size;
        sql += index + "," + size + ";";
        System.out.println("index:"+index+",size:"+size);
        List<BookInfo> bookInfos = baseQuery(BookInfo.class, sql);
        return bookInfos;
    }

    @Override
    public List<BookInfo> selectAllByBookName() {
        return null;
    }

    @Override
    public List<BookInfo> selectByOwnerBookName(Integer userId, String bookName) {
        return null;
    }
}
