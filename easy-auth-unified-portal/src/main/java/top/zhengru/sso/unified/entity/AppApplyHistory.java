package top.zhengru.sso.unified.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * 应用申请记录表
 * @TableName sys_app_apply_history
 */
@TableName(value ="sys_app_apply_history")
public class AppApplyHistory extends BaseEntity implements Serializable {
    /**
     * 应用id
     */
    private Long appId;

    /**
     * 排序字段
     */
    private Integer sort;

    /**
     * 审核状态
     */
    private Integer auditStatus;

    /**
     * 更新说明
     */
    private String updateMsg;

    /**
     * 审核说明
     */
    private String auditMsg;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public AppApplyHistory() {
    }

    public AppApplyHistory(Long appId, Integer sort, Integer auditStatus, String updateMsg, String auditMsg) {
        this.appId = appId;
        this.sort = sort;
        this.auditStatus = auditStatus;
        this.updateMsg = updateMsg;
        this.auditMsg = auditMsg;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg;
    }

    public String getAuditMsg() {
        return auditMsg;
    }

    public void setAuditMsg(String auditMsg) {
        this.auditMsg = auditMsg;
    }
}