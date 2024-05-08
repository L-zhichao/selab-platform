package tyut.selab.bookservice.dao;

import tyut.selab.bookservice.domain.BorrowBook;

import java.util.List;

/**
* @author 26580
* @description 针对表【book_borrow(书籍借阅表)】的数据库操作Mapper
* @createDate 2024-05-04 17:30:14
* @Entity tyut.selab.bookservice.domain.BorrowBook
*/
public interface BorrowBookDao {

    /**
     *  增加书籍借阅信息
     * @param borrowBook
     * @return
     */
    public Integer insert(BorrowBook borrowBook);

    /**
     *  修改书籍借阅信息
     * @param borrowBook
     * @return
     */
    public Integer update(BorrowBook borrowBook);

    /**
     *  查询书籍借阅信息
     * @param borrowId 借阅表id
     * @return
     */
    public BorrowBook selectByBorrowIdBorrowBook(Integer borrowId);

    /**
     *  查询未归还书籍借阅信息
     * @param userId
     * @return
     */
    public List<BorrowBook> selectAllByBorrowUserForNoReturn(Integer userId);

    /**
     *  查询借阅记录通过书籍id
     * @param bookId
     * @return
     */
    public List<BorrowBook> selectAllByBookId(Integer bookId);
}




