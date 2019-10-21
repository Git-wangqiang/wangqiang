package cn.wmyskxz.springboot.mo;

import java.util.List;
import java.util.Map;

public class MakedataInfoRequestMO {
    private List<Map<String, Object>> requestdata;
    private String tablename;
    private String addinfosql;
    private Integer id;
    private String deleteinfobyidsql;
    private String deleteinfosbyidssql;
    private Integer[] ids;
    private String updateinfosql;

    public String getUpdateinfosql() {
        return updateinfosql;
    }

    public void setUpdateinfosql(String updateinfosql) {
        this.updateinfosql = updateinfosql;
    }

    public String getDeleteinfosbyidssql() {
        return deleteinfosbyidssql;
    }

    public void setDeleteinfosbyidssql(String deleteinfosbyidssql) {
        this.deleteinfosbyidssql = deleteinfosbyidssql;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }


    public String getDeleteinfobyidsql() {
        return deleteinfobyidsql;
    }

    public void setDeleteinfobyidsql(String deleteinfobyidsql) {
        this.deleteinfobyidsql = deleteinfobyidsql;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddinfosql() {
        return addinfosql;
    }

    public void setAddinfosql(String addinfosql) {
        this.addinfosql = addinfosql;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<Map<String, Object>> getRequestdata() {
        return requestdata;
    }

    public void setRequestdata(List<Map<String, Object>> requestdata) {
        this.requestdata = requestdata;
    }
}
