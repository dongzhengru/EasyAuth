package top.zhengru.sso.base.entity;

/**
 * 授权结果实体
 */
public class AuthContent {
    private String appToken;
    private TokenUser tokenUser;

    public AuthContent() {
    }

    public AuthContent(String appToken, TokenUser tokenUser) {
        this.appToken = appToken;
        this.tokenUser = tokenUser;
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
}
