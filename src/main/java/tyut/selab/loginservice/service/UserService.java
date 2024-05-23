package tyut.selab.loginservice.service;

import tyut.selab.loginservice.dto.UserLocal;
import tyut.selab.loginservice.dto.UserRegisterDto;

public interface UserService {
    public Integer findByUsername(String username);
    public Integer findByPassword(String password);
    public UserRegisterDto getUserByUsername(String username);
    public UserLocal getUserLocal();
    public Integer insertUser(UserRegisterDto user);
}
