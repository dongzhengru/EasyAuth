package top.zhengru.sso.unified.param;

/**
 * @Author: dongzhengru
 * @Blog: blog.zhengru.top
 * @Date: 2024/11/3
 * @Time: 22:47
 */
public class AppApplyParam {

    /** 应用类型 0:小程序 1:H5 */
    private Integer appType;

    /** 服务类型 0:内部开发应用 1:第三方开发应用 */
    private Integer serviceType;

    /** 应用名称 */
    private String name;

    /** 应用描述 */
    private String description;

    /** 图标名 */
    private String icon;

    /** 图标地址 */
    private String iconUrl;

    /** 默认首页地址 */
    private String homePage;

    /** 应用版本 */
    private String appVersion;

    public AppApplyParam() {
    }

    public AppApplyParam(Integer appType, Integer serviceType, String name, String description, String icon, String iconUrl, String homePage, String appVersion) {
        this.appType = appType;
        this.serviceType = serviceType;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.iconUrl = iconUrl;
        this.homePage = homePage;
        this.appVersion = appVersion;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
