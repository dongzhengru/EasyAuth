package top.zhengru.sso.client;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("easy-auth.sso.client")
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
     * 应用默认回调地址
     */
    private String defaultCallbackUrl;

    /**
     * 客户端注销地址
     */
    private String logoutUrl;

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public String getDefaultCallbackUrl() {
        return defaultCallbackUrl;
    }

    public void setDefaultCallbackUrl(String defaultCallbackUrl) {
        this.defaultCallbackUrl = defaultCallbackUrl;
    }

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }
}