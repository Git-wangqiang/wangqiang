package cn.wmyskxz.springboot.mo;

import java.util.List;
import java.util.Map;

public class CurrencyRequestMO extends PageBaseMO {
    private String tablename;
    private String selectsql;
    private List<Map> selectterm;

    public List<Map> getSelectterm() {
        return selectterm;
    }

    public void setSelectterm(List<Map> selectterm) {
        this.selectterm = selectterm;
    }

 /*    private SelectTermRequestMO selectterm;

    public SelectTermRequestMO getSelectterm() {
        return selectterm;
    }

    public void setSelectterm(SelectTermRequestMO selectterm) {
        this.selectterm = selectterm;
    }*/

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getSelectsql() {
        return selectsql;
    }

    public void setSelectsql(String selectsql) {
        this.selectsql = selectsql;
    }
}
