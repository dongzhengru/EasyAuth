package top.zhengru.sso.unified.param;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/4
 * @Time: 12:54
 */
public class AppAuditParam {

    /** 应用ID */
    private Long appId;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 审核说明
     */
    private String auditMsg;

    public AppAuditParam() {
    }

    public AppAuditParam(Long appId, Integer auditStatus, String auditMsg) {
        this.appId = appId;
        this.auditStatus = auditStatus;
        this.auditMsg = auditMsg;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }
}
