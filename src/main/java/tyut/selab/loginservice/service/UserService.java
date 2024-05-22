package tyut.selab.loginservice.service;

import tyut.selab.loginservice.dto.UserRegisterDto;

public interface UserService {
    public Integer findByUsername(String username);
    public Integer findByPassword(String password);
    public UserRegisterDto getUserByUsername(String username);
}
