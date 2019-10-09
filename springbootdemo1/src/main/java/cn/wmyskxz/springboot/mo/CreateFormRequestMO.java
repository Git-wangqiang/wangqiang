package cn.wmyskxz.springboot.mo;

public class CreateFormRequestMO {
    private Integer id;
    private String username;
    private String formworkname;
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFormworkname() {
        return formworkname;
    }

    public void setFormworkname(String formworkname) {
        this.formworkname = formworkname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
