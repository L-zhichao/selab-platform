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
import java.util.Date;
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
        BookInfo bookInfo = bookDtoToBookInfo(bookDto);
        return bookDao.insert(bookInfo);
    }

    @Override
    public Integer updateBook(BookVo bookVo)  {
        BookInfo bookInfo = bookVoToBookInfo(bookVo);
        return bookDao.update(bookInfo);
    }

    @Override
    public Integer deleteBook(Integer bookId) {
        return bookDao.delete(bookId);
    }

    @Override
    public BookVo selectBookById(Integer bookId) {
        BookInfo bookInfo = bookDao.selectByBookIdBookInfo(bookId);
        BookVo bookVo = bookIofoToBookVo(bookInfo);
        return bookVo;
    }

    @Override
    public PageUtil<BookVo> selectList(Integer cur, Integer size, Integer userId, String bookName) {
        PageUtil<BookVo> pageUtil = new PageUtil<BookVo>();
        pageUtil.setCur(cur);
        pageUtil.setSize(size);
        List<BookVo> list = new ArrayList<BookVo>();
        List<BookInfo> bookInfos = bookDao.selectByOwnerBookName(cur, size,userId,bookName);
        if(cur == 1){
            Integer count = bookDao.selectCount(null,null);
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
    public PageUtil<BookVo> selectBookByBookName(Integer cur, Integer size, String bookName) {
        PageUtil<BookVo> page = new PageUtil<>();
        page.setSize(size);
        page.setCur(cur);
        if(cur==1){
            Integer count = bookDao.selectCount(bookName,null);
            page.setTotal(count);
        }
        List<BookVo> bookVos = new ArrayList<>();
        List<BookInfo> bookInfos = bookDao.selectAllByBookName(cur,size,bookName);
        for(BookInfo bookInfo:bookInfos){
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            bookVos.add(bookVo);
        }
        page.setData(bookVos);

        return page;
    }

    @Override
    public PageUtil<BookVo> selectListByOwnerId(Integer cur, Integer size, Integer userId) {
        PageUtil<BookVo> page = new PageUtil<BookVo>();
        page.setSize(size);
        page.setCur(cur);
        if(cur == 1){
            Integer count = bookDao.selectCount(null,userId);
            page.setTotal(count);
        }
        List<BookVo> bookVos = new ArrayList<>();
        List<BookInfo> bookInfos = bookDao.selectByOwnerBookInfo(cur, size, userId);
        for (BookInfo bookInfo : bookInfos) {
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            bookVos.add(bookVo);
        }
        page.setData(bookVos);
        return page;
    }

    @Override
    public PageUtil<BookVo> selectAllList (Integer cur, Integer size){
        PageUtil<BookVo> page = new PageUtil<BookVo>();
        page.setSize(size);
        page.setCur(cur);
        if(cur==1){
            Integer count = bookDao.selectCount(null,null);
            page.setTotal(count);
        }
        List<BookVo> bookVos = new ArrayList<>();
        List<BookInfo> bookInfos = bookDao.selectAllList(cur, size);
        for (BookInfo bookInfo : bookInfos) {
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            bookVos.add(bookVo);
        }
        page.setData(bookVos);
        return page;
    }

    @Override
    public BookVo bookIofoToBookVo (BookInfo bookInfo){
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
        bookVo.setOwner(bookInfo.getOwner());
        User user = userDao.selectByUserIdUser(owner);
        String ownerName = user.getUserName();
        bookVo.setOwnerName(ownerName);
        return bookVo;
    }

    @Override
    public BookInfo bookDtoToBookInfo(BookDto bookDto){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setPrice(bookDto.getPrice());
        bookInfo.setBookAuthor(bookDto.getBookAuthor());
        bookInfo.setBookDetails(bookDto.getBookDetails());
        bookInfo.setBookName(bookDto.getBookName());
        bookInfo.setOwner(bookDto.getOwner());
        bookInfo.setRemark(bookDto.getRemark());
        bookInfo.setBookRef(bookDto.getBookRef());
        bookInfo.setStatus(0);
        Date nowdate = new Date();
        bookInfo.setCreateTime(nowdate);
        return bookInfo;
    }

    @Override
    public BookInfo bookVoToBookInfo(BookVo bookVo){
        BookInfo bookInfo = new BookInfo();
        bookInfo.setPrice(bookVo.getPrice());
        bookInfo.setBookAuthor(bookVo.getBookAuthor());
        bookInfo.setBookDetails(bookVo.getBookDetails());
        bookInfo.setBookName(bookVo.getBookName());
        bookInfo.setOwner(bookVo.getOwner());
        bookInfo.setStatus(bookVo.getStatus());
        bookInfo.setBookId(bookVo.getBookId());
        bookInfo.setBookRef(bookVo.getBookRef());
        bookInfo.setCreateTime(bookVo.getCreateTime());
        bookInfo.setUpdateTime(bookVo.getUpdateTime());
        return bookInfo;
    }
}
