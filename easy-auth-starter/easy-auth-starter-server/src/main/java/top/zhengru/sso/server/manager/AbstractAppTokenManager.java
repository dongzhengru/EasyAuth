package top.zhengru.sso.server.manager;

import top.zhengru.sso.base.constant.BaseConstant;
import top.zhengru.sso.base.entity.LifecycleManager;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.base.util.HttpUtils;
import top.zhengru.sso.server.entity.CodeContent;
import top.zhengru.sso.server.entity.TokenContent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 应用凭证AppToken管理抽象
 *
 * @author Joe
 */
public abstract class AbstractAppTokenManager implements LifecycleManager<TokenContent> {

    /**
     * AppToken超时时效
     */
    private int appTokenTimeout;

    public AbstractAppTokenManager(int appTokenTimeout) {
        this.appTokenTimeout = appTokenTimeout;
    }

    /**
     * 通过AppToken获取
     *
     * @param appToken
     * @return
     */
    public abstract TokenContent getByAppToken(String appToken);

    /**
     * 通过TGT移除
     *
     * @param tgt
     */
    public abstract void removeByTgt(String tgt);

    /**
     * 创建AppToken
     *
     * @param tc
     * @return
     */
    public TokenContent create(TokenContent tc) {
        return create(tc.getTokenUser(), tc.getLogoutUri(), tc);
    }

    /**
     * 创建AppToken
     *
     * @param tokenUser
     * @param codeContent
     * @return
     */
    public TokenContent create(TokenUser tokenUser, String logoutUri, CodeContent codeContent) {
        String appToken = "AT-" + UUID.randomUUID().toString().replaceAll("-", "");
        TokenContent tc = new TokenContent(codeContent.getTgt(), codeContent.getAppId(), appToken, tokenUser, logoutUri);
        create(appToken, tc);
        return tc;
    }

    /**
     * 发起客户端退出请求
     *
     * @param redirectUri
     * @param appToken
     */
    protected void sendLogoutRequest(String redirectUri, String appToken) {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(BaseConstant.LOGOUT_PARAMETER_NAME, appToken);
        HttpUtils.postHeader(redirectUri, headerMap);
    }

    public int getAppTokenTimeout() {
        return appTokenTimeout;
    }

    public void setAppTokenTimeout(int appTokenTimeout) {
        this.appTokenTimeout = appTokenTimeout;
    }

}
