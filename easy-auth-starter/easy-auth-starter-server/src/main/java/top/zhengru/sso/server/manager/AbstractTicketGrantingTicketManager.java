package top.zhengru.sso.server.manager;

import org.springframework.util.StringUtils;
import top.zhengru.sso.base.entity.LifecycleManager;
import top.zhengru.sso.base.entity.TokenUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 登录凭证（TGT）管理抽象
 *
 * @author Joe
 */
public abstract class AbstractTicketGrantingTicketManager implements LifecycleManager<TokenUser> {

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
        if (!StringUtils.hasLength(tgt)) {
            tgt = create(tokenUser);
            response.setHeader("X-TGT", tgt);
        }
//        else {
//            create(tgt, tokenUser);
//        }
        return tgt;
    }

    public void invalidate(HttpServletRequest request, HttpServletResponse response) {
        String tgt = getHeaderTgt(request);
        if (!StringUtils.hasLength(tgt)) {
            return;
        }
        // 删除登录凭证
        remove(tgt);
        // 删除所有Token，通知所有客户端退出，注销其本地Token
        tokenManager.removeByTgt(tgt);
        // TODO 删除凭证TGT
    }

    public String get(HttpServletRequest request) {
        String tgt = getHeaderTgt(request);
        if (!StringUtils.hasLength(tgt) || get(tgt) == null) {
            return null;
        } else {
            return tgt;
        }
    }
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
