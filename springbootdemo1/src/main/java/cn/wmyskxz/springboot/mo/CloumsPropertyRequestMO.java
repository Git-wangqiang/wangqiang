package cn.wmyskxz.springboot.mo;

public class CloumsPropertyRequestMO {
    private String cloumtname;//字段名
    private String cloumtype;//字段类型
    private Integer cloumlength;//字段长度
    private Integer cloumpoint;//小数点长度
    private String isnull;//0为非空 1 为空
    private String iskey;//是否为主键
    private String isautoincrement;//是否自动递增
    private String type;
    private String tablename;
    private String cloumtnote;//excle显示名称
    private String isselect;

    public String getIsselect() {
        return isselect;
    }

    public void setIsselect(String isselect) {
        this.isselect = isselect;
    }

    public String getCloumtnote() {
        return cloumtnote;
    }

    public void setCloumtnote(String cloumtnote) {
        this.cloumtnote = cloumtnote;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getIsnull() {
        return isnull;
    }

    public void setIsnull(String isnull) {
        this.isnull = isnull;
    }

    public String getIskey() {
        return iskey;
    }

    public void setIskey(String iskey) {
        this.iskey = iskey;
    }

    public String getIsautoincrement() {
        return isautoincrement;
    }

    public void setIsautoincrement(String isautoincrement) {
        this.isautoincrement = isautoincrement;
    }
}
