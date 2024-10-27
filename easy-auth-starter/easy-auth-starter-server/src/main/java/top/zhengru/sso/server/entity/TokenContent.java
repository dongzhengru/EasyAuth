package top.zhengru.sso.server.entity;

import top.zhengru.sso.base.entity.TokenUser;

/**
 * Token存储信息
 */
public class TokenContent extends CodeContent {

    private String appToken;
    private TokenUser tokenUser;
    private String logoutUri;

    public TokenContent() {
        super();
    }

    public TokenContent(String accessToken, TokenUser tokenUser, String logoutUri) {
        this.appToken = accessToken;
        this.tokenUser = tokenUser;
        this.logoutUri = logoutUri;
    }

    public TokenContent(String tgt, String appId, String appToken, TokenUser tokenUser, String logoutUri) {
        super(tgt, appId);
        this.appToken = appToken;
        this.tokenUser = tokenUser;
        this.logoutUri = logoutUri;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }

    public TokenUser getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(TokenUser tokenUser) {
        this.tokenUser = tokenUser;
    }

    public String getLogoutUri() {
        return logoutUri;
    }

    public void setLogoutUri(String logoutUri) {
        this.logoutUri = logoutUri;
    }

    @Override
    public String toString() {
        return "TokenContent{" +
                "appToken='" + appToken + '\'' +
                ", tokenUser=" + tokenUser +
                ", logoutUri='" + logoutUri + '\'' +
                '}';
    }
}