package top.zhengru.sso.base.entity;

/**
 * 服务端回传凭证Token对象
 */
public class Token {

    /**
     * 调用凭证
     */
    private String accessToken;

    /**
     * 调用凭证accessToken超时时间，单位（秒）
     */
    private int expiresIn;

    /**
     * 用户信息
     */
    private TokenUser tokenUser;

    public Token() {
        super();
    }

    public Token(String accessToken, int expiresIn, TokenUser tokenUser) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.tokenUser = tokenUser;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public TokenUser getTokenUser() {
        return tokenUser;
    }

    public void setTokenUser(TokenUser tokenUser) {
        this.tokenUser = tokenUser;
    }
}