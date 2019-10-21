package cn.wmyskxz.springboot.mo;


import java.util.List;
import java.util.Map;

public class LeadingOutRequestMO {
    private String tablename;
    private List<Map<String, Object>> checkdata;

    public List<Map<String, Object>> getCheckdata() {
        return checkdata;
    }

    public void setCheckdata(List<Map<String, Object>> checkdata) {
        this.checkdata = checkdata;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
}
