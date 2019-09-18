package cn.wmyskxz.springboot.mo;

public class AddRoleRequestMO {
    private Integer acailable;
    private String description;
    private String role;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAcailable() {
        return acailable;
    }

    public void setAcailable(Integer acailable) {
        this.acailable = acailable;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
