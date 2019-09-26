package cn.wmyskxz.springboot.mo;

import java.util.List;

public class CreateTableRequestMO {
    private String sqlyuju;
    private String tablename;
    private List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList;

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
