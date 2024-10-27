package top.zhengru.sso.server.manager;


import top.zhengru.sso.base.entity.LifecycleManager;
import top.zhengru.sso.server.entity.CodeContent;

import java.util.UUID;

/**
 * 授权码code管理
 */
public abstract class AbstractCodeManager implements LifecycleManager<CodeContent> {

    private int timeout;

    public AbstractCodeManager(int timeout) {
        this.timeout = timeout;
    }

    /**
     * 创建授权码
     *
     * @param tgt
     * @param appId
     * @return
     */
    public String create(String tgt, String appId) {
        String code = "Code-" + UUID.randomUUID().toString().replaceAll("-", "");
        create(code, new CodeContent(tgt, appId));
        return code;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }
}
