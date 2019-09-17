package cn.wmyskxz.springboot.mo;

import java.util.List;

public class ResponseTreeMO {

    private String title;
    private Integer id;
    private boolean spread;
    //private boolean disabled;
    private List<ResponseTreeChildrenMO> children;

    public List<ResponseTreeChildrenMO> getChildren() {
        return children;
    }

    public void setChildren(List<ResponseTreeChildrenMO> children) {
        this.children = children;
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

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }




  /*    {
        title: '早餐'
                , id: 1
                , spread: true
                , children: [{
            title: '油条'
                    , id: 5,
                    disabled: false
        }, {
            title: '包子'
                    , id: 6
        }, {
            title: '豆浆'
                    , id: 7
        }]
    }
    */
}
