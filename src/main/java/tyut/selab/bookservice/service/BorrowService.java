package tyut.selab.bookservice.service;

import tyut.selab.bookservice.domain.BorrowBook;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.vo.BorrowBookVo;
import tyut.selab.utils.PageUtil;

/**
 *  书籍借阅service接口
 */
public interface BorrowService {

    /**
     * 借阅书籍
     * @param
     * @return
     */
    public Integer borrowBook(BorrowBookDto borrowBookDto);
    /**
     *  归还书籍
     * @param
     * @return
     */
    public Integer returnBook(Integer borrowId);

    /**
     * 通过userId查询用户借阅记录
     * @param userId
     * @return
     */
    public PageUtil<BorrowBookVo> selectListByUserId(Integer userId, Integer cur, Integer size);

    /**
     * 通过书籍id查询借阅记录
     *
     * @param bookId
     * @return
     */
    public PageUtil<BorrowBookVo> selectListByBookId(Integer bookId,Integer cur,Integer size);


    /**
     *  查询所有借阅记录(按时间排序)
     * @return
     */
    public PageUtil<BorrowBookVo> selectList(Integer cur,Integer size);

    /**
     *  查询所有未归还书籍
     * @return
     */
    public PageUtil<BorrowBookVo> selectAllForNoReturn(Integer cur,Integer size);

    public BorrowBook borrowBookDtoToBorrowBook(BorrowBookDto borrowBookDto);

    public BorrowBookVo borrowBookToVo(BorrowBook borrowBook);

    public PageUtil<BorrowBookVo> selectListByBorrowId(Integer borrowId);

}
