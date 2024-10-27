package top.zhengru.sso.base.entity;

/**
 * 用户信息
 */
public class TokenUser {

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 邮箱
     */
    private String email;


    public TokenUser() {
    }

    public TokenUser(Long id, String username, String name, String phone, String cardNo, String email) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.cardNo = cardNo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
