package cn.wmyskxz.springboot.mo;

import java.util.List;

public class CreateTableRequestMO {
    private String username;
    private String sqlyuju;
    private String tablename;
    private String remark;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList;

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

    public String getSqlyuju() {
        return sqlyuju;
    }

    public void setSqlyuju(String sqlyuju) {
        this.sqlyuju = sqlyuju;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<CloumsPropertyRequestMO> getCloumsPropertyRequestMOList() {
        return cloumsPropertyRequestMOList;
    }

    public void setCloumsPropertyRequestMOList(List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList) {
        this.cloumsPropertyRequestMOList = cloumsPropertyRequestMOList;
    }
}
