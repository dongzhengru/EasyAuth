package top.zhengru.sso.unified.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class UserDetailImpl implements UserDetails, Serializable {

    /** 姓名 */
    private String name;

    /** 手机号 */
    private String phone;

    /** 卡号 */
    private String cardNo;

    /** 邮箱 */
    private String email;

    /** 用户名 */
    private String username;

    /** 密码 */
    @JsonIgnore
    private String password;

    /** 最后登录时间 */
    @JsonIgnore
    private Date lastLoginTime;

    /** 登录总次数 */
    @JsonIgnore
    private Integer loginCount;
    @JsonIgnore
    private boolean accountNonExpired;
    @JsonIgnore
    private boolean accountNonLocked;
    @JsonIgnore
    private boolean credentialsNonExpired;

    /** 是否启用 */
    @JsonIgnore
    private Boolean enabled;
    private List<String> roles;

    private List<GrantedAuthorityImpl> authorities;

    public UserDetailImpl(String name, String phone, String cardNo, String email, String username, String password, List<String> roles, List<GrantedAuthorityImpl> authorities) {
        this.name = name;
        this.phone = phone;
        this.cardNo = cardNo;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.authorities = authorities;
    }

    public UserDetailImpl(String name, String phone, String cardNo, String email, String username, String password, Date lastLoginTime, Integer loginCount, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, Boolean enabled, List<String> roles, List<GrantedAuthorityImpl> authorities) {
        this.name = name;
        this.phone = phone;
        this.cardNo = cardNo;
        this.email = email;
        this.username = username;
        this.password = password;
        this.lastLoginTime = lastLoginTime;
        this.loginCount = loginCount;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.roles = roles;
        this.authorities = authorities;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public UserDetailImpl() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void setAuthorities(List<GrantedAuthorityImpl> authorities) {
        this.authorities = authorities;
    }
}
