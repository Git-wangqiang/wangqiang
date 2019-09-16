package cn.wmyskxz.springboot.pojo;

public class SysPermission {
    private Integer id;//主键.
    private String name;//名称.
    private String url;//资源路径.
    private String permission; //权限字符串
    private String parentmenu;//父菜单
    private String parentmenuname;

    private String order;

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getParentmenuname() {
        return parentmenuname;
    }

    public void setParentmenuname(String parentmenuname) {
        this.parentmenuname = parentmenuname;
    }

    public String getParentmenu() {
        return parentmenu;
    }

    public void setParentmenu(String parentmenu) {
        this.parentmenu = parentmenu;
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

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}