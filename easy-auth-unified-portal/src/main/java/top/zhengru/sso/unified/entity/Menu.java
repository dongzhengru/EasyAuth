package top.zhengru.sso.unified.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
public class Menu extends BaseEntity implements Serializable {

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String path;

    /**
     * 
     */
    private String component;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String iconcls;

    /**
     * 
     */
    private Integer keepalive;

    /**
     * 
     */
    private Integer requireauth;

    /**
     * 
     */
    private Integer parentid;

    /**
     * 
     */
    private Integer enabled;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public String getUrl() {
        return url;
    }

    /**
     * 
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     */
    public String getComponent() {
        return component;
    }

    /**
     * 
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     */
    public String getIconcls() {
        return iconcls;
    }

    /**
     * 
     */
    public void setIconcls(String iconcls) {
        this.iconcls = iconcls;
    }

    /**
     * 
     */
    public Integer getKeepalive() {
        return keepalive;
    }

    /**
     * 
     */
    public void setKeepalive(Integer keepalive) {
        this.keepalive = keepalive;
    }

    /**
     * 
     */
    public Integer getRequireauth() {
        return requireauth;
    }

    /**
     * 
     */
    public void setRequireauth(Integer requireauth) {
        this.requireauth = requireauth;
    }

    /**
     * 
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * 
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * 
     */
    public Integer getEnabled() {
        return enabled;
    }

    /**
     * 
     */
    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Menu other = (Menu) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath()))
            && (this.getComponent() == null ? other.getComponent() == null : this.getComponent().equals(other.getComponent()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getIconcls() == null ? other.getIconcls() == null : this.getIconcls().equals(other.getIconcls()))
            && (this.getKeepalive() == null ? other.getKeepalive() == null : this.getKeepalive().equals(other.getKeepalive()))
            && (this.getRequireauth() == null ? other.getRequireauth() == null : this.getRequireauth().equals(other.getRequireauth()))
            && (this.getParentid() == null ? other.getParentid() == null : this.getParentid().equals(other.getParentid()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getUpdateBy() == null ? other.getUpdateBy() == null : this.getUpdateBy().equals(other.getUpdateBy()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
        result = prime * result + ((getComponent() == null) ? 0 : getComponent().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getIconcls() == null) ? 0 : getIconcls().hashCode());
        result = prime * result + ((getKeepalive() == null) ? 0 : getKeepalive().hashCode());
        result = prime * result + ((getRequireauth() == null) ? 0 : getRequireauth().hashCode());
        result = prime * result + ((getParentid() == null) ? 0 : getParentid().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getUpdateBy() == null) ? 0 : getUpdateBy().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", url=").append(url);
        sb.append(", path=").append(path);
        sb.append(", component=").append(component);
        sb.append(", name=").append(name);
        sb.append(", iconcls=").append(iconcls);
        sb.append(", keepalive=").append(keepalive);
        sb.append(", requireauth=").append(requireauth);
        sb.append(", parentid=").append(parentid);
        sb.append(", enabled=").append(enabled);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}