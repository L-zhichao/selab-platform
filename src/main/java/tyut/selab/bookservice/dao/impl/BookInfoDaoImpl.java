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

    @Override
    public Integer insert(BookInfo bookInfo) throws SQLException {
        String sql = "insert into book_info values(DEFAULT,?,?,?,?,?,?,?,?,?,?)";
        int rows = baseUpdate(sql,bookInfo.getBookName(),bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getStatus(),bookInfo.getCreateTime(),bookInfo.getUpdateTime(),bookInfo.getRemark(),bookInfo.getBookRef());
        return rows;
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
    public List<BookInfo> selectAll(Integer cur, Integer size) {
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
