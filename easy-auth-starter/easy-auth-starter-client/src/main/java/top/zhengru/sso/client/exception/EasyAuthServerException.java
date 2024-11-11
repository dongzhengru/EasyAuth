package top.zhengru.sso.client.exception;

/**
 * EasyAuth服务异常
 */
public class EasyAuthServerException extends RuntimeException {

    public EasyAuthServerException() {
    }

    public EasyAuthServerException(String message) {
        super(message);
    }
}
