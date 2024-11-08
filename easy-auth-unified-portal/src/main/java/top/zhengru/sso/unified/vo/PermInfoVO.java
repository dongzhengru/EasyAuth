package top.zhengru.sso.unified.vo;

public class PermInfoVO {
    private String permission;
    private String permName;
    private int permValue;

    public PermInfoVO() {
    }

    public PermInfoVO(String permission, String permName, int permValue) {
        this.permission = permission;
        this.permName = permName;
        this.permValue = permValue;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getPermName() {
        return permName;
    }

    public void setPermName(String permName) {
        this.permName = permName;
    }

    public int getPermValue() {
        return permValue;
    }

    public void setPermValue(int permValue) {
        this.permValue = permValue;
    }
}
