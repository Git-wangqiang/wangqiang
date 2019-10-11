package cn.wmyskxz.springboot.mo;

public class CurrencyRequestMO extends PageBaseMO {
    private String tablename;
    private String selectsql;

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
