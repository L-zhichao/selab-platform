package tyut.selab.bookservice.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.vo.BookVo;

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
    @Override
    public Integer insertBook(BookDto bookDto) throws SQLException {
        BookInfo bookInfo = new BookInfo();
        String Str = JSONUtils.toJSONString(bookDto);
        bookInfo = (BookInfo) JSONUtils.parse(Str);
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
        return bookDao.selectAll(cur,size);
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
}
