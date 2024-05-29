package tyut.selab.bookservice.service.impl;

import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.PageUtil;

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
    public Integer insertBook(BookDto bookDto) {
        return null;
    }

    @Override
    public Integer updateBook(BookVo bookVo) {
        return null;
    }

    @Override
    public PageUtil<BookVo> selectList(Integer cur, Integer size) {
        PageUtil<BookVo> pageUtil = new PageUtil<BookVo>();
        pageUtil.setCur(cur);
        pageUtil.setSize(size);
        List<BookVo> list = new ArrayList<BookVo>();
        List<BookInfo> bookInfos = bookDao.selectAll(cur, size);
        if(cur == 1){
            Integer count = bookDao.selectAllCount();
            pageUtil.setTotal(count);
        }

        for (BookInfo bookInfo : bookInfos) {
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            list.add(bookVo);
        }

        pageUtil.setData(list);

        return pageUtil;
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
    @Override
    public BookVo bookIofoToBookVo(BookInfo bookInfo) {
        BookVo bookVo = new BookVo();
        Integer owner = bookInfo.getOwner();
        bookVo.setBookAuthor(bookInfo.getBookAuthor());
        bookVo.setBookDetails(bookInfo.getBookDetails());
        bookVo.setBookId(bookInfo.getBookId());
        bookVo.setBookName(bookInfo.getBookName());
        bookVo.setCreateTime(bookInfo.getCreateTime());
        bookVo.setPrice(bookInfo.getPrice());
        bookVo.setStatus(bookInfo.getStatus());
        bookVo.setUpdateTime(bookInfo.getUpdateTime());
        bookVo.setBookRef(bookInfo.getBookRef());
        User user = userDao.selectByUserIdUser(owner);
        String ownerName = user.getUserName();
        bookVo.setOwnerName(ownerName);
        return bookVo;
    }

    @Override
    public List<BookVo> selectByOwnerBookName(Integer userId, String bookName) {
        return null;
    }
}
