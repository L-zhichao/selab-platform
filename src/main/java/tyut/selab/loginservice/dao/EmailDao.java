package tyut.selab.loginservice.dao;

import tyut.selab.loginservice.domain.Email;

import java.nio.channels.InterruptedByTimeoutException;

public interface EmailDao {
    /**
     * 新增书籍
     * @param email
     * @return
     */
    public Integer insert(Email email);

    /**
     *  修改书籍信息
     * @param email
     * @return
     */
    public Integer update(Email email);

    /**
     *  查询统一邮件绑定用户数量
     * @param email
     * @return
     */
    public Integer selectNumForSameEmail(String email);

    /**
     *  删除邮箱信息
     * @param userId
     * @return
     */
    public Integer delect(Integer userId);
}
