package cn.wmyskxz.springboot.mo;

public class SaveCategoryRequestMO {
    private Integer datasourceid;
    private Integer formworkid;
    private Integer roleid;
    private String roletitle;
    private String formworktitle;
    private String datasourcetitle;


    public Integer getDatasourceid() {
        return datasourceid;
    }

    public void setDatasourceid(Integer datasourceid) {
        this.datasourceid = datasourceid;
    }

    public Integer getFormworkid() {
        return formworkid;
    }

    public void setFormworkid(Integer formworkid) {
        this.formworkid = formworkid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getRoletitle() {
        return roletitle;
    }

    public void setRoletitle(String roletitle) {
        this.roletitle = roletitle;
    }

    public String getFormworktitle() {
        return formworktitle;
    }

    public void setFormworktitle(String formworktitle) {
        this.formworktitle = formworktitle;
    }

    public String getDatasourcetitle() {
        return datasourcetitle;
    }

    public void setDatasourcetitle(String datasourcetitle) {
        this.datasourcetitle = datasourcetitle;
    }
}
