package cn.wmyskxz.springboot.mo;

public class CloumsPropertyRequestMO {
    private String cloumname;//字段名
    private String cloumtype;//字段类型
    private Integer cloumlength;//字段长度
    private Integer cloumpoint;//小数点长度
    private Integer isnull;//0为非空 1 为空
    private String iskey;//是否为主键
    private String isautoincrement;//是否自动递增

    public String getCloumname() {
        return cloumname;
    }

    public void setCloumname(String cloumname) {
        this.cloumname = cloumname;
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

    public Integer getIsnull() {
        return isnull;
    }

    public void setIsnull(Integer isnull) {
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
