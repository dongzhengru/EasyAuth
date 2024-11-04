package top.zhengru.sso.unified.param;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/3
 * @Time: 21:35
 */
public class AppQueryParam extends PageParam{

    /** 应用名称 */
    private String name;

    /** 审核状态 */
    private Integer auditStatus;

    /** 发布状态 0未发布 1发布 */
    private Integer publishStatus;

    /** 应用上下架状态 0下架 1上架 */
    private Integer shelveStatus;

    public AppQueryParam() {
    }

    public AppQueryParam(String name, Integer auditStatus, Integer publishStatus, Integer shelveStatus) {
        this.name = name;
        this.auditStatus = auditStatus;
        this.publishStatus = publishStatus;
        this.shelveStatus = shelveStatus;
    }

    public AppQueryParam(Integer pageNo, Integer pageSize, Integer offsetPage, String name, Integer auditStatus, Integer publishStatus, Integer shelveStatus) {
        super(pageNo, pageSize, offsetPage);
        this.name = name;
        this.auditStatus = auditStatus;
        this.publishStatus = publishStatus;
        this.shelveStatus = shelveStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(Integer publishStatus) {
        this.publishStatus = publishStatus;
    }

    public Integer getShelveStatus() {
        return shelveStatus;
    }

    public void setShelveStatus(Integer shelveStatus) {
        this.shelveStatus = shelveStatus;
    }
}
