package tyut.selab.loginservice.common;

public interface Constant {

    /**
     *  对于同一邮箱注册的最大用户数量
     */
    public static final Integer MAX_SAME_MMAIL_REGISTER_NUM = 3;
    Integer STATUS_CODE_INNSER_ERROR = 500;
    Integer STATUS_CODE_NON_IMPLEMENTATION = 501;
    Integer STATUS_CODE_NON_TOKEN = 505;
}
