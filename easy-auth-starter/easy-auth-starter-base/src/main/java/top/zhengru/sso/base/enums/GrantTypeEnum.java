package top.zhengru.sso.base.enums;

/**
 * 授权方式
 */
public enum GrantTypeEnum {

    /**
     * 授权码模式
     */
    AUTHORIZATION_CODE("authorization_code");

    private String value;

    GrantTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}