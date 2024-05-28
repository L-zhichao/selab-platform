package tyut.selab.bookservice.service.impl;

import tyut.selab.bookservice.service.BorrowService;
import tyut.selab.bookservice.vo.BorrowBookVo;

import java.util.List;

/**
 * @className: BorrowServiceImpl
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/5 11:05
 * @version: 1.0
 */
public class BorrowServiceImpl implements BorrowService {

    @Override
    public Integer borrowBook(Integer bookId) {
        return null;
    }

    @Override
    public Integer returnBook(Integer bookId) {
        return null;
    }

    @Override
    public List<BorrowBookVo> selectListByUserid(Integer userId, Integer cur, Integer size) {
        return null;
    }

    @Override
    public List<BorrowBookVo> selectListByBookId(Integer bookId, Integer cur, Integer size) {
        return null;
    }

    @Override
    public List<BorrowBookVo> selectList(Integer cur, Integer size) {
        return null;
    }

    @Override
    public List<BorrowBookVo> selectAllForNoReturn(Integer cur, Integer size) {
        return null;
    }
}
