package top.zhengru.sso.server.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import top.zhengru.sso.base.entity.LifecycleManager;
import top.zhengru.sso.base.entity.TokenUser;
import top.zhengru.sso.server.ServerProperties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登录凭证（TGT）管理抽象
 */
public abstract class AbstractTicketGrantingTicketManager implements LifecycleManager<TokenUser> {

    @Autowired
    ServerProperties serverProperties;
    private AbstractAppTokenManager tokenManager;
    private int tgtTimeout;

    public AbstractTicketGrantingTicketManager(AbstractAppTokenManager tokenManager, int tgtTimeout) {
        this.tokenManager = tokenManager;
        this.tgtTimeout = tgtTimeout;
    }

    /**
     * 登录成功后，根据用户信息创建TGT令牌
     *
     * @param tokenUser
     * @return
     */
    String create(TokenUser tokenUser) {
        String tgt = "TGT-" + UUID.randomUUID().toString().replaceAll("-", "");
        create(tgt, tokenUser);
        return tgt;
    }

    public String getOrCreate(TokenUser tokenUser, HttpServletRequest request, HttpServletResponse response) {
        String tgt = getHeaderTgt(request);
        if (!StringUtils.hasLength(tgt) || get(tgt) == null) {
            if (!serverProperties.isMultiDeviceOnlineAllowed()) {
                // 下线其他设备
                removeByUserId(tokenUser.getId());
            }
            tgt = create(tokenUser);
            response.setHeader("X-TGT", tgt);
        }
        return tgt;
    }

    public void invalidate(String tgt) {
        if (!StringUtils.hasLength(tgt)) {
            return;
        }
        // 删除所有Token，通知所有客户端退出，注销其本地Token
        tokenManager.removeByTgt(tgt);
        // 删除TGT登录凭证
        remove(tgt);
    }

    public String get(HttpServletRequest request) {
        String tgt = getHeaderTgt(request);
        if (!StringUtils.hasLength(tgt) || get(tgt) == null) {
            return null;
        } else {
            return tgt;
        }
    }

    public abstract void removeByUserId(Long userId);

    private String getHeaderTgt(HttpServletRequest request) {
        return request.getHeader("X-TGT");
    }

    public AbstractAppTokenManager getTokenManager() {
        return tokenManager;
    }

    public void setTokenManager(AbstractAppTokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public int getTgtTimeout() {
        return tgtTimeout;
    }

    public void setTgtTimeout(int tgtTimeout) {
        this.tgtTimeout = tgtTimeout;
    }
}
