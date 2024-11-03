package top.zhengru.sso.client.constant;

/**
 * 客户端常量
 */
public class ClientConstant {

    /**
     * 获取认证中心登录地址接口路径
     */
    public static final String LOGIN_URL_PATH = "/sso/login/loginUrl";

    /**
     * 认证中心申请应用凭证接口路径
     */
    public static final String AUTH_PATH = "/sso/auth";

    /**
     * 认证中心统一注销接口路径
     */
    public static final String LOGOUT_PATH = "/sso/logout";

    /**
     * 模糊匹配后缀
     */
    public static final String URL_FUZZY_MATCH = "/*";

    /**
     * 未登录或已过期（无token）
     */
    public static final int NO_LOGIN = 10;

}
