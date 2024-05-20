import org.junit.Test;
import tyut.selab.loginservice.dao.impl.EmailDaoImpl;
import tyut.selab.loginservice.domain.Email;

public class EmailTest {
    @Test
    public void test(){
        EmailDaoImpl emailDao = new EmailDaoImpl();
        Email email1 = emailDao.executeQueryOne(Email.class, "select * from sys_email where userId = 1");
        System.out.println(email1.getCreateTime());
    }
}
