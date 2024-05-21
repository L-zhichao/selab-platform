package tyut.selab.loginservice.service;

public interface UserService {
    public Integer findByUsername(String username);
    public Integer findByPassword(String password);
}
