package cn.wmyskxz.springboot.mo;

public class RoleResponseMO {

    private Integer id;
    private String acailable;
    private String description;
    private String role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAcailable() {
        return acailable;
    }

    public void setAcailable(String acailable) {
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
