package tyut.selab.bookservice.dao.impl;

import tyut.selab.bookservice.dao.BaseDao;
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
public class BookInfoDaoImpl extends BaseDao implements BookInfoDao {

    private BaseDao baseDao = new BaseDao();
    @Override
    public Integer insert(BookInfo bookInfo) {
        String sql = "insert into book_info (book_name BookName,book_author BookAuthor,book_details BookDetails,price Price,owner Owner,remark Remark,book_ref BookRef) values (DEFAULT,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {bookInfo.getBookName(),bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getRemark(),bookInfo.getBookRef()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer update(BookInfo bookInfo) {
        String sql = "update book_info set book_author=?,book_details=?,price=?,owner=?,status=? where book_name=?";
        Object[] params = {bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getStatus(),bookInfo.getBookName()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer delete(Integer bookId) {
        String sql = "delete from book_info where bookId=?";
        int param = bookId;
        return baseDao.baseUpdate(sql,param);
    }

    @Override
    public List<BookInfo> selectByOwnerBookName(Integer cur, Integer size, Integer userId, String bookName) {
        String sql ="select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner owner,status status,create_time createTime,update_time updateTime,book_ref bookRef from Book_info where user_id like ? and book_name=? limit ?,?";
        int index = (cur - 1) * size;
        List<BookInfo> bookInfos = baseQuery(BookInfo.class, sql,"%" + bookName + "%", userId,index,size);
        return bookInfos;
    }

    @Override
    public List<BookInfo> selectByOwnerBookInfo(Integer cur, Integer size, Integer userId) {
        String sql = "select book_name BookName,book_author BookAuthor,book_details BookDetails,price Price,owner Owner,remark Remark,book_ref BookRef from book_info where userId = ? limit ?,?";
        int index = (cur - 1) * size;
        return baseDao.baseQuery(BookInfo.class,sql,userId,index,size);
    }

    @Override
    public List<BookInfo> selectAllByBookName(Integer cur, Integer size, String bookName) {
        // 使用通配符之后的模糊匹配
        String sql = "select * from book_info where book_name like ? limit ?,?";
        int index = (cur - 1) * size;
        List<BookInfo> bookInfos = baseQuery(BookInfo.class, sql,"%" + bookName + "%",index,size);
        return bookInfos;
    }

    @Override
    public BookInfo selectByBookIdBookInfo(Integer bookId) {
        String sql = "select book_name bookName,book_author bookAuthor,book_details bookDetails,price price,owner owner,status,remark remark,book_ref bookRef from book_info where book_id = ?";
        int param = bookId;
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class,sql,param);
        return bookInfos.get(0);
    }

    @Override
    public List<BookInfo> selectAllList(Integer cur, Integer size) {
        String sql = "select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,book_ref bookRef from book_info limit ?,?";
        int index = (cur - 1) * size;
        Object[] params = {index,size};
        List<BookInfo> bookInfos = baseQuery(BookInfo.class,sql,params);
        return bookInfos;
    }

    @Override
    public Integer selectCount(String bookName,Integer userId) {
        String sql = "select count(*) from book_info";
        if (bookName!=null && userId!=null){
            sql+="where book_name like ? and user_id =?";
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql,"%" + bookName + "%",userId)));
        }
        else if(bookName!=null && userId==null){
            sql+="where book_name like ?";
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql,"%"+bookName+"%")));
        }
        else if(bookName==null && userId!=null){
            sql+="where userId =?";
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql,userId)));
        }
        else{
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql)));
        }
    }

}
