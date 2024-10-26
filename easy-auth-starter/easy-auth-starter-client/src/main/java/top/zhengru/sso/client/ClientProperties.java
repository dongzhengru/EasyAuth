package top.zhengru.sso.client;

import top.zhengru.sso.client.constant.ClientConstant;

public class ClientProperties {

    /**
     * 服务端地址
     */
    private String serverUrl;

    /**
     * 应用Id
     */
    private String appId;

    /**
     * 应用密钥
     */
    private String appSecret;

    /**
     * 拦截urls，默认拦截全路径
     */
    private String[] urlPatterns = {ClientConstant.URL_FUZZY_MATCH};

    /**
     * 忽略拦截urls
     */
    private String[] excludeUrls;

    /**
     * 过滤器排序，默认10
     */
    private int order = 10;

    /**
     * 客户端注销地址
     */
    private String logoutPath = "/logout";

    /**
     * 存放在cookie或者Header中的token名称
     */
    private String tokenName = "easy-auth-token";
}