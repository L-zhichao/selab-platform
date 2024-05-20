package tyut.selab.bookservice.dao;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.vo.BookVo;

import java.sql.SQLException;
import java.util.List;

/**
* @author 26580
* @description 针对表【book_info(书籍信息表)】的数据库操作Mapper
* @createDate 2024-05-04 17:11:15
* @Entity tyut.selab.bookservice.domain.BookInfo
*/
public interface BookInfoDao {

    /**
     *  增加书籍信息
     * @param bookInfo
     * @return
     */
    public Integer insert(BookInfo bookInfo) throws SQLException;

    /**
     *  修改书籍信息
     * @param bookInfo
     * @return
     */
    public Integer update(BookInfo bookInfo) throws SQLException;

    /**
     *  删除图书信息
     * @param bookId
     * @return
     */
    public Integer delete(Integer bookId) throws SQLException;

    /**
     * 通过id查询书籍信息
     * @param bookId
     * @return
     */
    public BookInfo selectByBookIdBookInfo(Integer bookId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException;

    /**
     *  通过拥有者id查询本人所有书籍
     * @param userId
     * @return
     */
    public List<BookInfo> selectByOwnerBookInfo(Integer userId) throws SQLException;

    /**
     * 查询所有书籍
     * @return
     */
    public List<BookInfo> selectAll(Integer cur, Integer size);

    /**
     * 通过书籍名称<模糊查询>书籍信息
     * @return
     */
    public List<BookInfo> selectAllByBookName();



}




