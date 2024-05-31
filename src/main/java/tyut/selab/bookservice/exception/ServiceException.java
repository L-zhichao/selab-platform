package tyut.selab.bookservice.exception;

/**
 * ClassName: ServiceException
 * Package: tyut.selab.bookservice.exception
 * Description:
 *
 * @Author The_fish
 * @Create 2024/5/17 16:51
 * @Version 1.0
 */
public class ServiceException extends RuntimeException {

    static final long serialVersionUID = -7034897190747766939L;
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}