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
        String sql = "insert into book_info (book_id,book_name,book_author,book_details,price,owner,status,del_flag=?,remark,create_time,update_time,book_ref) values (DEFAULT,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {bookInfo.getBookName(),bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getStatus(),bookInfo.getDelFlag(),bookInfo.getRemark(),bookInfo.getCreateTime(),bookInfo.getUpdateTime(),bookInfo.getBookRef()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer update(BookInfo bookInfo) {
        String sql = "update book_info set book_name=?,book_author=?,book_details=?,price=?,owner=?,status=?,create_time=?,update_time=?,book_ref=? where book_id =?";
        Object[] params = {bookInfo.getBookName(),bookInfo.getBookAuthor(),bookInfo.getBookDetails(),bookInfo.getPrice(),bookInfo.getOwner(),bookInfo.getStatus(),bookInfo.getCreateTime(),bookInfo.getUpdateTime(),bookInfo.getBookRef(),bookInfo.getBookId()};
        return baseDao.baseUpdate(sql,params);
    }

    @Override
    public Integer delete(Integer bookId) {
        String sql = "update book_info set delFlag=1 where bookId=?";
        return baseDao.baseUpdate(sql,bookId);
    }

    @Override
    public List<BookInfo> selectByOwnerBookName(Integer cur, Integer size, Integer userId, String bookName) {
        String sql ="select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,book_ref bookRef from book_info where del_flag=0 and owner=? and book_name like ? limit ?,?";
        int index = (cur - 1) * size;
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class,sql,userId,"%"+bookName+"%",index,size);
        return bookInfos;
    }

    @Override
    public List<BookInfo> selectByOwnerBookInfo(Integer cur, Integer size, Integer userId) {
        String sql = "select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,book_ref bookRef from book_info where del_flag=0 and owner=? limit ?,?;";
        int index = (cur - 1) * size;
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class,sql,userId,index,size);
        return bookInfos;
    }

    @Override
    public List<BookInfo> selectAllByBookName(Integer cur, Integer size, String bookName) {
        // 使用通配符之后的模糊匹配
        String sql = "select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,book_ref bookRef from book_info where del_flag=0 and book_name like ? limit ?,?;";
        int index = (cur - 1) * size;
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class, sql,"%"+bookName+"%",index,size);
        return bookInfos;
    }

    @Override
    public BookInfo selectByBookIdBookInfo(Integer bookId) {
        String sql = "select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,book_ref bookRef from book_info where del_flag=0 and book_id=?";
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class, sql, bookId);
        if (!bookInfos.isEmpty()) {
            return bookInfos.get(0);
        } else{
            return null;
        }
    }

    @Override
    public List<BookInfo> selectAllList(Integer cur, Integer size) {
        String sql = "select book_id bookId,book_name bookName,book_author bookAuthor,book_details bookDetails,price,owner,status,create_time createTime,update_time updateTime,book_ref bookRef from book_info where del_flag=0 limit ?,?";
        int index = (cur - 1) * size;
        List<BookInfo> bookInfos = baseDao.baseQuery(BookInfo.class,sql,index,size);
        return bookInfos;
    }

    @Override
    public Integer selectCount(String bookName,Integer userId) {
        String sql = "select count(*) from book_info where del_flag=0";
        if (bookName!=null && userId!=null){
            sql+=" and book_name like ? and owner =?";
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql,"%"+bookName+"%",userId)));
        }
        else if(bookName!=null && userId==null){
            sql+=" and book_name like ?";
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql,"%"+bookName+"%")));
        }
        else if(bookName==null && userId!=null){
            sql+=" and owner=?";
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql,userId)));
        }
        else{
            return Integer.parseInt(String.valueOf(baseDao.baseQueryObject(Long.class,sql)));
        }
    }

}
