package tyut.selab.bookservice.service;

import tyut.selab.bookservice.domain.BookInfo;
import tyut.selab.bookservice.domain.BorrowBook;
import tyut.selab.bookservice.dto.BorrowBookDto;
import tyut.selab.bookservice.vo.BorrowBookVo;
import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.userservice.domain.User;

import java.util.List;

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
    public List<BorrowBookVo> selectListByUserid(Integer userId,Integer cur,Integer size);

    /**
     *  通过书籍id查询借阅记录
     * @param bookId
     * @return
     */
    public List<BorrowBookVo> selectListByBookId(Integer bookId,Integer cur,Integer size);

    /**
     *  查询所有借阅记录(按时间排序)
     * @return
     */
    public List<BorrowBookVo> selectList(Integer cur,Integer size);

    /**
     *  查询所有未归还书籍
     * @return
     */
    public List<BorrowBookVo> selectAllForNoReturn(Integer cur,Integer size);

    public BorrowBook borrowBookDtoToBorrowBook(BorrowBookDto borrowBookDto, UserLocal userLocal);

}
