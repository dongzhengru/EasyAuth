package top.zhengru.sso.server.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

/**
 * MybatisPlus基础持久化基类
 */
public class BaseEntity extends Entity {

    public static String CREATE_BY = "createBy";
    public static String UPDATE_BY = "updateBy";
    public static String CREATE_TIME = "createTime";
    public static String UPDATE_TIME = "updateTime";
    public static String IS_DELETED = "isDeleted";

    @TableField(value = "create_by")
    public Long createBy;

    @TableField(value = "update_by")
    public Long updateBy;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected Date createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected Date updateTime;

    @TableField(value = "is_deleted")
    @TableLogic
    public Integer isDeleted;

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}