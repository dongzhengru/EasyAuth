package top.zhengru.sso.base.entity;

import java.beans.Transient;

/**
 * 返回结果
 */
public class Result<T> {

    /**
     * 成功
     */
    public static final int SUCCESS_CODE = 1000;

    /**
     * 重定向
     */
    public static final int REDIRECT_CODE = 1001;

    /**
     * 系统错误
     */
    public static final int ERROR_CODE = 9001;

    /**
     * 结果体
     */
    private T data;

    /**
     * 状态码
     */
    private int code;

    /**
     * 信息
     */
    private String message;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this(code, message);
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, "成功");
    }

    public static <T> Result<T> success(T data) {
        Result<T> r = success();
        r.setData(data);
        return r;
    }

    public static <T> Result<T> redirect() {
        return new Result<>(REDIRECT_CODE, "重定向");
    }

    public static <T> Result<T> redirect(T data) {
        Result<T> r = redirect();
        r.setData(data);
        return r;
    }

    public static <T> Result<T> error() {
        return new Result<>(ERROR_CODE, "系统执行出错");
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(ERROR_CODE, message);
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    @Transient
    public boolean isSuccess() {
        return SUCCESS_CODE == code;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
