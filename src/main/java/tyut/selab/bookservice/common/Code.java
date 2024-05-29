package tyut.selab.bookservice.common;

/**
 * @className: Code
 * @author: lizhichao
 * @description: TODO 错误码（在项目中Result.class 设置code属性专用类）
 * @date: 2024/5/5 11:06
 * @version: 1.0
 */
public interface Code {
    public static final Integer SUCCESS = 200;
    public static final Integer DATABASE_HAND_UNKNOWN_ERROR = 50001;
    public static final Integer MESSAGE_ERROR = 500001;
    public static final Integer PATH_ERROR = 500004;
    public static final Integer OTHER_BORRORED = 500006;
    public static final Integer CAN_NOT_BORROWED = 500007;
    public static final Integer ALREADY_BORROWED = 500008;
    public static final Integer NOT_FIND_BOOK = 500009;
    public static final Integer NO_RECORDED = 500010;
    public static final Integer HAVE_NOT_RECORDED = 500011;
    public static final Integer ALREADY_RETURNED = 500012;


}
