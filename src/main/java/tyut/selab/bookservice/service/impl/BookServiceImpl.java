package tyut.selab.bookservice.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.vo.BookVo;
// import tyut.selab.userservice.dao.impl.UserDaoImpl;

import java.sql.SQLException;
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
    //private UserDao userDao = new UserDaoImpl();
    @Override
    public Integer insertBook(BookDto bookDto) {
        String jsonString = JSONUtils.toJSONString(bookDto);
        BookInfo bookInfo = JSONObject.parseObject(jsonString,BookInfo.class);
        try {
            return bookDao.insert(bookInfo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer updateBook(BookVo bookVo)  {
        String jsonString = JSONUtils.toJSONString(bookVo);
        BookInfo bookInfo = JSONObject.parseObject(jsonString,BookInfo.class);
        try {
            return bookDao.update(bookInfo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer deleteBook(Integer bookId) {
        try {
            return bookDao.delete(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BookVo> selectList(Integer cur, Integer size) {
        /*
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
         */
        return null;
    }

    @Override
    public BookVo selectByBookIdUserIdBookName(Integer bookId,Integer userId,String bookName){
        BookInfo bookInfo = null;
        try {
            bookInfo = bookDao.selectByBookIdUserIdBookName(bookId, userId, bookName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        BookVo bookVo = bookIofoToBookVo(bookInfo);
        return bookVo;
    }

    @Override
    public BookVo selectBookById(Integer bookId) {
        BookInfo bookInfo = null;
        try {
            bookInfo = bookDao.selectByBookIdBookInfo(bookId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        String jsonString = JSONUtils.toJSONString(bookInfo);
        BookVo bookVo = JSONObject.parseObject(jsonString, BookVo.class);
        return bookVo;
    }

    @Override
    public BookVo selectBookByBookName(String bookName) {
        try {
            List<BookInfo> bookInfos = bookDao.selectAllByBookName(bookName);
            for(BookInfo bookInfo:bookInfos){
                String jsonString = JSONUtils.toJSONString(bookInfo);
                BookVo bookVo = JSONObject.parseObject(jsonString,BookVo.class);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<BookVo> selectListByOwnerId(Integer userid, Integer cur, Integer size) {
        try {
            bookDao.selectByOwnerBookInfo(userid)
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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
