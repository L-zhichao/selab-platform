package tyut.selab.bookservice.test;

import org.junit.jupiter.api.Test;
import tyut.selab.bookservice.controller.BookController;
import tyut.selab.utils.Result;

/**
 * ClassName: BookListTest
 * Package: tyut.selab.bookservice.test
 * Description:
 *
 * @Author The_fish
 * @Create 2024/5/14 12:42
 * @Version 1.0
 */
public class BookListTest {
    @Test
    public void test(){
        Result<Object> objectResult = new Result<>(200,"{bookname = 《nihao》,Author = zhangsan}");
        objectResult.setMsg("success");
        System.out.println(objectResult);
    }
}
