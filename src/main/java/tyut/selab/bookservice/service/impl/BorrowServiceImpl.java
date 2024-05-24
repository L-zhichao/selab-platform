package tyut.selab.bookservice.service.impl;

import org.junit.platform.commons.function.Try;
import tyut.selab.bookservice.dao.BookInfoDao;
import tyut.selab.bookservice.dao.BorrowBookDao;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.dao.impl.BorrowBookDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.domain.BorrowBook;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.service.BorrowService;
import tyut.selab.bookservice.vo.BookVo;
import tyut.selab.bookservice.vo.BorrowBookVo;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.utils.SecurityUtil;
import tyut.selab.userservice.dao.UserDao;
import tyut.selab.userservice.dao.impl.UserDaoImpl;
import tyut.selab.userservice.domain.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @className: BorrowServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 11:05
 * @version: 1.0
 */
public class BorrowServiceImpl implements BorrowService {
     BorrowBookDao borrowBookDao = new BorrowBookDaoImpl();
     BookInfoDao bookInfoDao = new BookInfoDaoImpl();

     UserDao userDao = new UserDaoImpl();

    @Override
    public Integer borrowBook(BorrowBookDto borrowBookDto) {
        Integer i = 0;

        // 查询书籍状态
        BookInfo bookInfo = bookInfoDao.selectByBookIdBookInfo(borrowBookDto.getBookId());
        if(bookInfo == null){
            return -5;
        }
        Integer status = bookInfo.getStatus();
        SecurityUtil securityUtil = new SecurityUtil();
        UserLocal user = securityUtil.getUser();
        Integer userId = user.getUserId();
        //判断是否可借阅，并进行相关处理
        if(status == 0){
            BorrowBook borrowBook = borrowBookDtoToBorrowBook(borrowBookDto);
            i = borrowBookDao.insert(borrowBook);
            if (i > 0){
                bookInfo.setStatus(1);
                bookInfoDao.update(bookInfo);
            }
        }else if (status == 1){
            List<BorrowBook> borrowBooks = borrowBookDao.selectAllByBookId(borrowBookDto.getBookId());
            if(userId == borrowBooks.get(borrowBooks.size()-1).getBorrowUser()){
                i = -4;
            }else {
                i = -2;
            }
        } else if (status == 2) {
            i = -3;
        }

        return i;
    }

    @Override
    public Integer returnBook(Integer borrowId) {
        BorrowBook borrowBook = borrowBookDao.selectByBorrowIdBorrowBook(borrowId);
        if (borrowBook == null) {
            return -1;
        }
        Integer borrowUser = borrowBook.getBorrowUser();
        Integer status = borrowBook.getStatus();
        Integer bookId = borrowBook.getBookId();
        //查询当前用户id
        SecurityUtil securityUtil = new SecurityUtil();
        UserLocal user = securityUtil.getUser();
        Integer userId = user.getUserId();
        if (borrowUser != userId){
            return -2;
        }
        if (status == 1){
            return -3;
        }
        status = 1;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        borrowBook.setReturnTime(date);
        Integer i = borrowBookDao.update(borrowBook);
        if (i > 0){
            BookInfo bookInfo = bookInfoDao.selectByBookIdBookInfo(bookId);
            bookInfo.setStatus(0);
            bookInfoDao.update(bookInfo);
        }
        return i;
    }

    @Override
    public List<BorrowBookVo> selectListByUserid(Integer userId, Integer cur, Integer size) {
        List<BorrowBookVo> books = new ArrayList<BorrowBookVo>();
        List<BorrowBook> borrowBooks = borrowBookDao.selectAllByUserId(userId, cur, size);
        for(BorrowBook book : borrowBooks){
            BorrowBookVo borrowBookVo = borrowBookToVo(book);
            books.add(borrowBookVo);
        }
        return books;
    }

    @Override
    public BorrowBookVo selectByBorrowId(Integer borrowId){
        BorrowBook borrowBook = borrowBookDao.selectByBorrowIdBorrowBook(borrowId);
        BorrowBookVo borrowBookVo = borrowBookToVo(borrowBook);
        return borrowBookVo;
    }

    @Override
    public List<BorrowBookVo> selectListByBookId(Integer bookId,Integer cur,Integer size) {
        List<BorrowBookVo> books = new ArrayList<BorrowBookVo>();
        List<BorrowBook> borrowBooks = borrowBookDao.selectAllByBookId(bookId,cur,size);
        for(BorrowBook book : borrowBooks){
            BorrowBookVo borrowBookVo = borrowBookToVo(book);
            books.add(borrowBookVo);
        }
        return books;
    }

    @Override
    public List<BorrowBookVo> selectList(Integer cur, Integer size) {
        List<BorrowBookVo> books = new ArrayList<BorrowBookVo>();
        List<BorrowBook> borrowBooks = borrowBookDao.selectAll(cur, size);
        for(BorrowBook book : borrowBooks){
            BorrowBookVo borrowBookVo = borrowBookToVo(book);
            books.add(borrowBookVo);
        }
        return books;
    }

    @Override
    public List<BorrowBookVo> selectAllForNoReturn(Integer cur, Integer size) {
        List<BorrowBookVo> list = new ArrayList<BorrowBookVo>();
        List<BorrowBook> borrowBooks = borrowBookDao.selectAllForNoReturn();
        for(BorrowBook book : borrowBooks){
            BorrowBookVo borrowBookVo = borrowBookToVo(book);
            list.add(borrowBookVo);
        }
        return list;
    }

    @Override
    public BorrowBook borrowBookDtoToBorrowBook(BorrowBookDto borrowBookDto) {
        BorrowBook borrowBook = new BorrowBook();

        SecurityUtil securityUtil = new SecurityUtil();
        UserLocal user = securityUtil.getUser();
        Integer userId = user.getUserId();

        borrowBook.setBookId(borrowBookDto.getBookId());
        borrowBook.setBorrowDuration(borrowBookDto.getBorrowDuration());
        borrowBook.setReturnTime(borrowBookDto.getReturnTime());
        borrowBook.setBorrowUser(userId);
        return borrowBook;
    }
    public BorrowBookVo borrowBookToVo(BorrowBook borrowBook){
        BorrowBookVo borrowBookVo = new BorrowBookVo();

        Integer bookId = borrowBook.getBookId();
        Integer borrowUser = borrowBook.getBorrowUser();

        borrowBookVo.setBorrowId(borrowBook.getBorrowId());
        borrowBookVo.setBookId(borrowBook.getBookId());
        borrowBookVo.setBorrowUser(borrowBook.getBorrowUser());
        borrowBookVo.setBorrowDuration(borrowBook.getBorrowDuration());
        borrowBookVo.setStatus(borrowBook.getStatus());
        borrowBookVo.setBorrowTime(borrowBook.getBorrowTime());
        borrowBookVo.setReturnTime(borrowBook.getReturnTime());

        BookInfo bookInfo = bookInfoDao.selectByBookIdBookInfo(bookId);
        borrowBookVo.setBookName(bookInfo.getBookName());

        User user = userDao.selectByUserIdUser(borrowUser);
        borrowBookVo.setBorrowUserName(user.getUserName());

        return borrowBookVo;
    }
}
