package cn.wmyskxz.springboot.pojo;

public class SysRole {
    private Integer id;
    private String role;//角色标识程序中判断使用,如"admin",这个是唯一的:
    private String description; // 角色描述
    private String available; // 是否可用,如果不可用将不会添加给用户
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }
}