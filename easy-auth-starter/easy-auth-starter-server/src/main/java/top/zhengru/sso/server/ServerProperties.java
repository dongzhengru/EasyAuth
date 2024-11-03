package top.zhengru.sso.server;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("easy-auth.sso.server")
public class ServerProperties {

    /*
     * 授权码失效时间，默认为10分钟（单位秒）
     */
    private int codeTimeout = 60 * 10;

    /*
     * 调用凭证appToken失效时间，默认为6小时（单位秒）
     */
    private int appTokenTimeout = 60 * 60 * 6;

    /*
     * 单点登录（TGT凭证）失效时间，默认24小时（单位秒）
     */
    private int tgtTimeout = 60 * 60 * 24;

    /*
     * 认证中心登录地址
     */
    private String serverLoginUrl;

    /*
     * 是否允许多端在线
     */
    private boolean isMultiDeviceOnlineAllowed = false;

    public int getCodeTimeout() {
        return codeTimeout;
    }

    public void setCodeTimeout(int codeTimeout) {
        this.codeTimeout = codeTimeout;
    }

    public int getAppTokenTimeout() {
        return appTokenTimeout;
    }

    public void setAppTokenTimeout(int appTokenTimeout) {
        this.appTokenTimeout = appTokenTimeout;
    }

    public int getTgtTimeout() {
        return tgtTimeout;
    }

    public void setTgtTimeout(int tgtTimeout) {
        this.tgtTimeout = tgtTimeout;
    }

    public String getServerLoginUrl() {
        return serverLoginUrl;
    }

    public void setServerLoginUrl(String serverLoginUrl) {
        this.serverLoginUrl = serverLoginUrl;
    }

    public boolean isMultiDeviceOnlineAllowed() {
        return isMultiDeviceOnlineAllowed;
    }

    public void setMultiDeviceOnlineAllowed(boolean multiDeviceOnlineAllowed) {
        isMultiDeviceOnlineAllowed = multiDeviceOnlineAllowed;
    }
}