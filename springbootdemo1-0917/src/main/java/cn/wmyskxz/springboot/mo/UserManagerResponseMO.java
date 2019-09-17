package cn.wmyskxz.springboot.mo;

import java.util.Date;

public class UserManagerResponseMO {

    private Integer id;
    private String username;
    private String telephone;
    private String description;
    private Date updatedate;
    private String acailable;
    private String salt;
    private Integer roleid;
    private String saltstatus;

    public String getSaltstatus() {
        return saltstatus;
    }

    public void setSaltstatus(String saltstatus) {
        this.saltstatus = saltstatus;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public String getAcailable() {
        return acailable;
    }

    public void setAcailable(String acailable) {
        this.acailable = acailable;
    }
}
