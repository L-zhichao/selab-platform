package tyut.selab.bookservice.dao.impl;

import org.junit.experimental.theories.ParameterSignature;
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
    public Integer insert(BookInfo bookInfo) throws SQLException {
        String sql = "insert into book_info (book_name BookName,book_author BookAuthor,book_details BookDetails,price Price,owner Owner,remark Remark,book_ref BookRef) values (DEFAULT,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {bookInfo.getBookName(),bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getRemark(),bookInfo.getBookRef()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer update(BookInfo bookInfo) throws SQLException {
        String sql = "update book_info set book_author=?,book_details=?,price=?,owner=?,status=? where book_name=?";
        Object[] params = {bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getStatus(),bookInfo.getBookName()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer delete(Integer bookId) throws SQLException {
        String sql = "delete from book_info where bookId=?";
        int param = bookId;
        return baseDao.baseUpdate(sql,param);
    }

    @Override
    public BookInfo selectByBookIdBookInfo(Integer bookId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        String sql = "select book_name BookName,book_author BookAuthor,book_details BookDetails,price Price,owner Owner,remark Remark,book_ref BookRef from book_info where bookId = ?";
        int param = bookId;
        return baseDao.baseQueryObject(BookInfo.class,sql,param);
    }

    @Override
    public List<BookInfo> selectByOwnerBookInfo(Integer userId) throws SQLException {
        String sql = "select book_name BookName,book_author BookAuthor,book_details BookDetails,price Price,owner Owner,remark Remark,book_ref BookRef from book_info where userId = ?";
        int param = userId;
        return baseDao.baseQuery(BookInfo.class,sql,param);
    }

    @Override
    public List<BookInfo> selectAll(Integer cur, Integer size) throws SQLException {
        String sql = "select t1.book_id bookId,t1.book_name bookName,t1.book_author bookAuthor,t1.book_details bookDetails,t1.price,t1.owner owner,t1.status status,t1.create_time createTime,t1.update_time updateTime,t1.book_ref bookRef from Book_info t1 limit ";
        int index = (cur - 1) * size;
        sql += index + "," + size + ";";
        List<BookInfo> bookInfos = baseQuery(BookInfo.class, sql);
        return bookInfos;
    }

    @Override
    public List<BookInfo> selectAllByBookName() {
        return null;
    }
}
