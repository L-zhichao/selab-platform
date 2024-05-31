package tyut.selab.userservice.service;

import org.junit.Test;
import tyut.selab.userservice.service.impl.UserServiceImpl;
import tyut.selab.userservice.vo.UserVo;

import java.util.List;

public class UserServiceTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void testQueryByRoleId(){
        List<UserVo> userVos = userService.selectAll();
        System.out.println(userVos);
    }
}
