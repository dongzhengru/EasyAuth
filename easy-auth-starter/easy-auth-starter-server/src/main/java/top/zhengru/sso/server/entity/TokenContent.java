package top.zhengru.sso.server.entity;

import top.zhengru.sso.base.entity.TokenUser;

/**
 * Token存储信息
 */
public class TokenContent extends CodeContent {

    private String accessToken;
    private TokenUser tokenUser;
    private String logoutUri;

    public TokenContent() {
        super();
    }

    public TokenContent(String accessToken, TokenUser tokenUser, String logoutUri) {
        this.accessToken = accessToken;
        this.tokenUser = tokenUser;
        this.logoutUri = logoutUri;
    }

    public TokenContent(String tgt, String clientId, String accessToken, TokenUser tokenUser, String logoutUri) {
        super(tgt, clientId);
        this.accessToken = accessToken;
        this.tokenUser = tokenUser;
        this.logoutUri = logoutUri;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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
}