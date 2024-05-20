package tyut.selab.bookservice.service.impl;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.druid.support.json.JSONUtils;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.domain.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: BookServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 17:26
 * @version: 1.0
 */
public class BookServiceImpl implements BookService {
    private BookInfoDao bookDao = new BookInfoDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    @Override
    public Integer insertBook(BookDto bookDto) throws SQLException {
        BookInfo bookInfo = new BookInfo();
        String jsonString = JSONUtils.toJSONString(bookDto);
        bookInfo = (BookInfo) JSONUtils.parse(jsonString);
        return bookDao.insert(bookInfo);
    }

    @Override
    public Integer updateBook(BookVo bookVo) throws SQLException {
        BookInfo bookInfo = new BookInfo();

        return bookDao.update(bookInfo);
    }

    public Integer deleteBook(Integer bookId) throws SQLException {
        return bookDao.delete(bookId);
    }

    @Override
    public List<BookVo> selectList(Integer cur, Integer size) {
        List<BookVo> list = new ArrayList<BookVo>();
        List<BookInfo> bookInfos = bookDao.selectAll(cur, size);
        for (BookInfo bookInfo : bookInfos) {
            Integer owner = bookInfo.getOwner();
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            User user = userDao.selectByUserIdUser(owner);
            String ownerName = user.getUserName();
            bookVo.setOwnerName(ownerName);
            list.add(bookVo);
        }
        return list;
    }

    @Override
    public BookVo selectBookById(Integer bookId) {
        return null;
    }

    @Override
    public BookVo selectBookByBookName(String bookName) {
        return null;
    }

    @Override
    public List<BookVo> selectListByOwnerId(Integer userid, Integer cur, Integer size) {
        return null;
    }

    private BookVo bookIofoToBookVo(BookInfo bookInfo) {
        BookVo bookVo = new BookVo();
        bookVo.setBookAuthor(bookInfo.getBookAuthor());
        bookVo.setBookDetails(bookInfo.getBookDetails());
        bookVo.setBookId(bookInfo.getBookId());
        bookVo.setBookName(bookInfo.getBookName());
        bookVo.setCreateTime(bookInfo.getCreateTime());
        bookVo.setPrice(bookInfo.getPrice());
        bookVo.setStatus(bookInfo.getStatus());
        bookVo.setUpdateTime(bookInfo.getUpdateTime());
        bookVo.setBookRef(bookInfo.getBookRef());
        return bookVo;
    }
}
