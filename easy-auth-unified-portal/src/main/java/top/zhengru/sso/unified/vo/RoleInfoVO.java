package top.zhengru.sso.unified.vo;

import java.util.List;

public class RoleInfoVO {
    private String roleName;
    private int roleCode;
    private int rolePid;
    private List<PermInfoVO> permInfoVOList;

    public RoleInfoVO() {
    }

    public RoleInfoVO(String roleName, int roleCode, int rolePid, List<PermInfoVO> permInfoVOList) {
        this.roleName = roleName;
        this.roleCode = roleCode;
        this.rolePid = rolePid;
        this.permInfoVOList = permInfoVOList;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(int roleCode) {
        this.roleCode = roleCode;
    }

    public int getRolePid() {
        return rolePid;
    }

    public void setRolePid(int rolePid) {
        this.rolePid = rolePid;
    }

    public List<PermInfoVO> getPermInfoVOList() {
        return permInfoVOList;
    }

    public void setPermInfoVOList(List<PermInfoVO> permInfoVOList) {
        this.permInfoVOList = permInfoVOList;
    }

    @Override
    public String toString() {
        return "RoleInfoVO{" +
                "roleName='" + roleName + '\'' +
                ", roleCode=" + roleCode +
                ", rolePid=" + rolePid +
                ", permInfoVOList=" + permInfoVOList +
                '}';
    }
}
