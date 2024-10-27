package top.zhengru.sso.server.enums;


/**
 * 错误码枚举
 */
public enum ErrorCodeEnum {

    E2001(2001, "密码不能为空"),
    E2002(2002, "密码加密失败"),
    E2003(2003, "应用编码已存在"),
    E2004(2004, "用户名已存在");

    private Integer code;
    private String desc;

    ErrorCodeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}