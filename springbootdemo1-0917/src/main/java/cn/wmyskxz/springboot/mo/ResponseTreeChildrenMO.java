package cn.wmyskxz.springboot.mo;

public class ResponseTreeChildrenMO {
    private String title;
    private Integer id;
    // private boolean spread;
    private boolean disabled;
    private boolean checked;//是否选中

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }
}
