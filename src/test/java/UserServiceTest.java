import org.junit.Test;
import tyut.selab.loginservice.dto.UserRegisterDto;
import tyut.selab.loginservice.service.impl.UserServiceImpl;

public class UserServiceTest {
    @Test
    public void findUserTest(){
        UserServiceImpl userService = new UserServiceImpl();
        System.out.println(userService.findByUsername("shaobo"));
    }
    @Test
    public void getUserByUserNameTest(){
        UserServiceImpl userService = new UserServiceImpl();
        UserRegisterDto shaobo = userService.getUserByUsername("shaobo");
        System.out.println(shaobo);
    }
    @Test
    public void insertUserTest(){
        UserServiceImpl userService = new UserServiceImpl();
        UserRegisterDto user = new UserRegisterDto("shaobo","123456",1,"3388532526@qq.com","18811111111",1);
        userService.insertUser(user);
    }
}
