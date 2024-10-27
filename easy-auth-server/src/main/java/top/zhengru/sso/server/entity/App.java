package top.zhengru.sso.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 应用
 */
@TableName("sys_app")
public class App extends BaseEntity {

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

	/** 应用ID */
	private Long appId;

	/** 应用密钥 */
	private String appSecret;

	/** 默认首页地址 */
	private String homePage;

	/** 审核状态 */
	private Integer auditStatus;

	/** 审核信息 */
	private String auditMsg;

	/** 发布状态 0未发布 1发布 */
	private Integer publishStatus;

	/** 应用版本 */
	private String appVersion;

	/** 应用上下架状态 0下架 1上架 */
	private Integer shelveStatus;

	/** 排序字段 */
	private Integer sort;

	/** 开发者 */
	private Long developer;

	public App() {
	}

	public App(Integer appType, Integer serviceType, String name, String description, String icon, String iconUrl, Long appId, String appSecret, String homePage, Integer auditStatus, String auditMsg, Integer publishStatus, String appVersion, Integer shelveStatus, Integer sort, Long developer) {
		this.appType = appType;
		this.serviceType = serviceType;
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.iconUrl = iconUrl;
		this.appId = appId;
		this.appSecret = appSecret;
		this.homePage = homePage;
		this.auditStatus = auditStatus;
		this.auditMsg = auditMsg;
		this.publishStatus = publishStatus;
		this.appVersion = appVersion;
		this.shelveStatus = shelveStatus;
		this.sort = sort;
		this.developer = developer;
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

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getHomePage() {
		return homePage;
	}

	public void setHomePage(String homePage) {
		this.homePage = homePage;
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

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	public Integer getShelveStatus() {
		return shelveStatus;
	}

	public void setShelveStatus(Integer shelveStatus) {
		this.shelveStatus = shelveStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getDeveloper() {
		return developer;
	}

	public void setDeveloper(Long developer) {
		this.developer = developer;
	}
}
