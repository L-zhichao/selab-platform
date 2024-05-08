package tyut.selab.loginservice.service;

import tyut.selab.loginservice.domain.Email;

public interface EmailService {

    /**
     *  添加邮箱信息
     * @param email
     * @return
     */
    public Integer save(Email email);

    /**
     *  修改邮箱信息
     * @param email
     * @return
     */
    public Integer update(Email email);

    /**
     *  查询同一个邮箱注册用户数量
     * @param email
     * @return
     */
    public Integer queryNumForSameEmail(String email);




}
