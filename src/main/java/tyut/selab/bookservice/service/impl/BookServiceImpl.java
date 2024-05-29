package tyut.selab.bookservice.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.dto.BookDto;
import tyut.selab.bookservice.service.BookService;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.domain.User;
import tyut.selab.utils.Page;

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
        String jsonString = JSONUtils.toJSONString(bookDto);
        BookInfo bookInfo = JSONObject.parseObject(jsonString,BookInfo.class);
        return bookDao.insert(bookInfo);
    }

    @Override
    public Integer updateBook(BookVo bookVo)  {
        String jsonString = JSONUtils.toJSONString(bookVo);
        BookInfo bookInfo = JSONObject.parseObject(jsonString,BookInfo.class);
        return bookDao.update(bookInfo);
    }

    public Integer deleteBook(Integer bookId) {
        return bookDao.delete(bookId);
    }

    @Override
    public BookVo selectBookById(Integer bookId) {
        BookInfo bookInfo = null;
        bookInfo = bookDao.selectByBookIdBookInfo(bookId);
        String jsonString = JSONUtils.toJSONString(bookInfo);
        BookVo bookVo = JSONObject.parseObject(jsonString, BookVo.class);
        return bookVo;
    }

    @Override
    public Page<BookVo> selectList(Integer cur, Integer size, Integer userId, String bookName) {
        Page<BookVo> page = new Page<BookVo>();
        page.setCur(cur);
        page.setSize(size);
        if(cur==1){
            Integer count = bookDao.selectCount(bookName,userId);
            page.setTotal(count);
        }
        List<BookVo> list = new ArrayList<BookVo>();
        List<BookInfo> bookInfos = null;
        bookInfos = bookDao.selectByOwnerBookName(cur, size,userId,bookName);
        for (BookInfo bookInfo : bookInfos) {
            Integer owner = bookInfo.getOwner();
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            User user = userDao.selectByUserIdUser(owner);
            String ownerName = user.getUserName();
            bookVo.setOwnerName(ownerName);
            list.add(bookVo);
        }
        page.setData(list);
        return page;
    }

    @Override
    public Page<BookVo> selectBookByBookName(Integer cur, Integer size, String bookName) {
        Page<BookVo> page = new Page<BookVo>();
        page.setSize(size);
        page.setCur(cur);
        if(cur==1){
            Integer count = bookDao.selectCount(bookName,null);
            page.setTotal(count);
        }
        List<BookVo> bookVos = new ArrayList<>();
        List<BookInfo> bookInfos = bookDao.selectAllByBookName(cur,size,bookName);
        for(BookInfo bookInfo:bookInfos){
            // 拿到书籍拥有者的编号
            Integer owner = bookInfo.getOwner();
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            // 找到owner对应的ownerName
            User user = userDao.selectByUserIdUser(owner);
            String ownerName = user.getUserName();
            bookVo.setOwnerName(ownerName);
            bookVos.add(bookVo);
        }
        page.setData(bookVos);
        return page;
    }

    @Override
    public Page<BookVo> selectListByOwnerId(Integer cur, Integer size, Integer userId) {
        Page<BookVo> page = new Page<BookVo>();
        page.setSize(size);
        page.setCur(cur);
        if(cur == 1){
            Integer count = bookDao.selectCount(null,userId);
            page.setTotal(count);
        }
        List<BookVo> bookVos = new ArrayList<>();
        List<BookInfo> bookInfos = bookDao.selectByOwnerBookInfo(cur, size, userId);
        for (BookInfo bookInfo : bookInfos) {
            Integer owner = bookInfo.getOwner();
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            User user = userDao.selectByUserIdUser(owner);
            String ownerName = user.getUserName();
            bookVo.setOwnerName(ownerName);
            bookVos.add(bookVo);
        }
        page.setData(bookVos);
        return page;
    }

    public Page<BookVo> selectAllList (Integer cur, Integer size){
        Page<BookVo> page = new Page<BookVo>();
        page.setSize(size);
        page.setCur(cur);
        if(cur==1){
            Integer count = bookDao.selectCount(null,null);
            page.setTotal(count);
        }
        List<BookVo> bookVos = new ArrayList<>();
        List<BookInfo> bookInfos = bookDao.selectAllList(cur, size);
        for (BookInfo bookInfo : bookInfos) {
            Integer owner = bookInfo.getOwner();
            BookVo bookVo = bookIofoToBookVo(bookInfo);
            User user = userDao.selectByUserIdUser(owner);
            String ownerName = user.getUserName();
            bookVo.setOwnerName(ownerName);
            bookVos.add(bookVo);
        }
        page.setData(bookVos);
        return page;
    }

    public BookVo bookIofoToBookVo (BookInfo bookInfo){
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
