package cn.wmyskxz.springboot.mo;

public class PermissionRequestMO {
    private Integer id;
    private String name;
    private String url;
    private String parentmenuname;
    private Integer order;

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentmenuname() {
        return parentmenuname;
    }

    public void setParentmenuname(String parentmenuname) {
        this.parentmenuname = parentmenuname;
    }
}
