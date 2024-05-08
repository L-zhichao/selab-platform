package tyut.selab.bookservice.utils;

/**
 * @className: Result
 * @author: lizhichao
 * @description: TODO
 * @date: 2024/5/4 11:34
 * @version: 1.0
 */
public class Result<T> {

    private Integer code;
    private T data;

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
