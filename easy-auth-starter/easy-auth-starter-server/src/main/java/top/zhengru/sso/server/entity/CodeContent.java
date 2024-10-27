package top.zhengru.sso.server.entity;

/**
 * 授权码存储信息
 */
public class CodeContent {

    private String tgt;
    private String appId;

    public CodeContent() {
    }

    public CodeContent(String tgt, String appId) {
        this.tgt = tgt;
        this.appId = appId;
    }

    public String getTgt() {
        return tgt;
    }

    public void setTgt(String tgt) {
        this.tgt = tgt;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}