package com.ejunhai.junhaimall.system.model;

public class SystemMgtUser implements java.io.Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 7409408215677529201L;

    private String id;

    private String username;

    private String loginName;

    private String loginPassword;

    private String role;

    private String state;

    public SystemMgtUser(String loginName, String loginPassword, String role) {
        this.loginName = loginName;
        this.loginPassword = loginPassword;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}
