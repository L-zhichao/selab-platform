package tyut.selab.loginservice.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @className: Email
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/8 9:24
 * @version: 1.0
 */
public class Email implements Serializable {

    public static final Long serialVersionUID = 1L;
    private String email;
    private Integer userId;
    private Date createTime;


}
