import org.junit.Test;
import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;
import tyut.selab.loginservice.service.impl.EmailServiceImpl;

public class EmailTest {
    @Test
    public void test(){
        EmailDaoImpl emailDao = new EmailDaoImpl();
        Email email1 = emailDao.executeQueryOne(Email.class, "select * from sys_email where userId = 1");
        System.out.println(email1.getCreateTime());
    }
    @Test
    public void deleteTest(){
        EmailDaoImpl emailDao = new EmailDaoImpl();
        int result = emailDao.delete(1);
        System.out.println(result);
    }
    @Test
    public void queryNumTest(){
        EmailServiceImpl emailService = new EmailServiceImpl();
        Integer i = emailService.queryNumForSameEmail("3388532526@qq.com");
        System.out.println(i);
    }

}
