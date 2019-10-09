package cn.wmyskxz.springboot.mo;


public class CreateFormResponseMO {
    private Integer id;
    private String cloumtname;
    private String cloumtype;
    private Integer cloumlength;
    private Integer cloumpoint;
    private String tablename;
    private String cloumtnote;

    public String getCloumtnote() {
        return cloumtnote;
    }

    public void setCloumtnote(String cloumtnote) {
        this.cloumtnote = cloumtnote;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCloumtname() {
        return cloumtname;
    }

    public void setCloumtname(String cloumtname) {
        this.cloumtname = cloumtname;
    }

    public String getCloumtype() {
        return cloumtype;
    }

    public void setCloumtype(String cloumtype) {
        this.cloumtype = cloumtype;
    }

    public Integer getCloumlength() {
        return cloumlength;
    }

    public void setCloumlength(Integer cloumlength) {
        this.cloumlength = cloumlength;
    }

    public Integer getCloumpoint() {
        return cloumpoint;
    }

    public void setCloumpoint(Integer cloumpoint) {
        this.cloumpoint = cloumpoint;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }
}
