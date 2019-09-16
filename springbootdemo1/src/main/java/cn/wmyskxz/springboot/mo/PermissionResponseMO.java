package cn.wmyskxz.springboot.mo;

public class PermissionResponseMO extends PageBaseMO {
    private Integer id;
    private String name;
    private String url;
    private Integer order;
    private String parentmenu;
    private String parentmenuname;
    private String permissionclassify;
    private String menuIcon;
    private Integer isMenu;
    private Integer parentId;

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public Integer getIsMenu() {
        return isMenu;
    }

    public void setIsMenu(Integer isMenu) {
        this.isMenu = isMenu;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getParentmenu() {
        return parentmenu;
    }

    public void setParentmenu(String parentmenu) {
        this.parentmenu = parentmenu;
    }

    public String getParentmenuname() {
        return parentmenuname;
    }

    public void setParentmenuname(String parentmenuname) {
        this.parentmenuname = parentmenuname;
    }

    public String getPermissionclassify() {
        return permissionclassify;
    }

    public void setPermissionclassify(String permissionclassify) {
        this.permissionclassify = permissionclassify;
    }
}
