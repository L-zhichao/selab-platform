package tyut.selab.bookservice.dao.impl;

import tyut.selab.bookservice.dao.BaseDao;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.vo.BookVo;

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
    public BookInfo selectByBookIdBookInfo(Integer bookId) {
        String sql = "select bookName userId cur size from book_info where bookId = ?";
        return null;
    }

    @Override
    public List<BookInfo> selectByOwnerBookInfo(Integer userId) {
        return null;
    }

    @Override
    public List<BookVo> selectAll(Integer cur, Integer size) {
        String sql = "select t1.book_id bookId,t1.book_name bookName,t1.book_author bookAuthor,t1.book_details bookDetails,t1.price,t1.owner owner,t2.user_name ownerName,t1.status status,t1.create_time createTime,t1.update_time updateTime,t1.book_ref book\n" +
                "Ref from Book_info t1 join sys_user t2 on t1.owner = t2.user_id limit ";
        int index = (cur - 1) * size;
        sql.concat(index + "," + size);
        try {
            List<BookVo> bookVos = baseQuery(BookVo.class, sql);
            return bookVos;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookInfo> selectAllByBookName() {
        return null;
    }
}
