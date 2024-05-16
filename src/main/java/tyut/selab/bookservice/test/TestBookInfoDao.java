package tyut.selab.bookservice.test;

import org.junit.jupiter.api.Test;
import tyut.selab.bookservice.dao.impl.BookInfoDaoImpl;
import tyut.selab.bookservice.domain.BookInfo;

import java.sql.SQLException;
import java.util.Date;

/**
 * ClassName: TestBookInfoDao
 * Package: tyut.selab.bookservice.test
 * Description:
 *
 * @Author The_fish
 * @Create 2024/5/12 21:25
 * @Version 1.0
 */
public class TestBookInfoDao {
    private static BookInfoDaoImpl bookInfoDao;
    @Test
    public void testInsert() throws SQLException {
        BookInfoDaoImpl bookInfoDao1 = new BookInfoDaoImpl();
        BookInfo bookInfo = new BookInfo();
        bookInfo.setBookId(10);
        bookInfo.setBookAuthor("Zhangsan");
        bookInfo.setBookName("nihao");
        bookInfo.setBookDetails("hahaha");
        bookInfo.setPrice(100);
        bookInfo.setOwner(1L);
        bookInfo.setStatus(1);
        bookInfo.setBookRef("zxc");
        bookInfo.setCreateTime(new Date());
        bookInfo.setUpdateTime(new Date());
        bookInfo.setRemark("第一个");
        int rows = bookInfoDao1.insert(bookInfo);
        System.out.println(rows);
    }
}
