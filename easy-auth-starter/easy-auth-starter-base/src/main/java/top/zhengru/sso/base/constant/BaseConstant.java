package top.zhengru.sso.base.constant;

/**
 * 单点登录基础常量
 */
public class BaseConstant {

    /**
     * 服务端登录路径
     */
    public static final String LOGIN_PATH = "/sso/login";

    /**
     * 服务端退出路径
     */
    public static final String LOGOUT_PATH = "/sso/logout";

    /**
     * 服务端回调客户端地址参数名称
     */
    public static final String REDIRECT_URI = "redirectUri";

    /**
     * 服务端注销回调客户端注销地址
     */
    public static final String LOGOUT_URI = "logoutUri";

    /**
     * 授权方式
     */
    public static final String GRANT_TYPE = "grantType";

    /**
     * 应用ID
     */
    public static final String APP_ID = "appId";

    /**
     * 应用密钥
     */
    public static final String APP_SECRET = "appSecret";

    /**
     * 授权码
     */
    public static final String AUTH_CODE = "code";
}