package tyut.selab.utils;

import tyut.selab.bookservice.vo.BookVo;

import java.util.List;

/**
 * @className: Result
 * @author: try.
 * @description: TODO
 * @date: 2024/5/4 11:34
 * @version: 1.0
 */
public class Result<T> {
    /**
     * 错误码
     */
    private Integer code;
    /**
     *  返回数据
     */
    private T data;
    /**
     *  错误信息
     */
    private String msg;

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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public static <T> Result<T> success(T data){
        return new Result(200,data,"success");
    }
    public static <T> Result<T> error(Integer code,String msg){
        return new Result<>(code,null,msg);
    }

    private Result(Integer code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }
}
