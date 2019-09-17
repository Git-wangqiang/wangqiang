package cn.wmyskxz.springboot.pojo;

import java.util.Date;

public class Zckj {
    private Integer id;
    private String mode_name;
    private String yyzt;
    private Date updatedate;
    private String rdnf;

    public String getRdnf() {
        return rdnf;
    }

    public void setRdnf(String rdnf) {
        this.rdnf = rdnf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }

    public String getYyzt() {
        return yyzt;
    }

    public void setYyzt(String yyzt) {
        this.yyzt = yyzt;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }
}
