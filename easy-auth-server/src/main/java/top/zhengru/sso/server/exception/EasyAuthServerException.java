package top.zhengru.sso.server.exception;

/**
 * 应用服务异常
 */
public class EasyAuthServerException extends RuntimeException {

    public EasyAuthServerException() {
    }

    public EasyAuthServerException(String message) {
        super(message);
    }
}
