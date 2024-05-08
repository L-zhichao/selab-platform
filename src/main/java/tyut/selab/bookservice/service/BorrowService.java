package tyut.selab.bookservice.service;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.domain.BorrowBook;

import java.util.List;

/**
 *  书籍借阅service接口
 */
public interface BorrowService {

    /**
     * 借阅书籍
     * @param bookId
     * @return
     */
    public Integer borrowBook(Integer bookId);

    /**
     *  归还书籍
     * @param bookId
     * @return
     */
    public Integer returnBook(Integer bookId);

    /**
     * 通过userId查询用户借阅记录
     * @param userId
     * @return
     */
    public List<BorrowBook> selectListByUserid(Integer userId);

    /**
     *  通过书籍id查询借阅记录
     * @param bookId
     * @return
     */
    public List<BorrowBook> selectListByBookId(Integer bookId);

    /**
     *  查询所有借阅记录(按时间排序)
     * @return
     */
    public List<BorrowBook> selectList(Integer cur,Integer size);

    /**
     *  查询所有未归还书籍
     * @return
     */
    public List<BorrowBook> selectAllForNoReturn();
}
