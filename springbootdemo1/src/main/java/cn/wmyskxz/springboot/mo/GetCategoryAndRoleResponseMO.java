package cn.wmyskxz.springboot.mo;

import java.util.List;

public class GetCategoryAndRoleResponseMO {
    private List<DatasourceMO> datasourceMOList;
    private List<FormworkMO> formworkMOList;
    private List<RoleMO> roleMOList;

    public List<DatasourceMO> getDatasourceMOList() {
        return datasourceMOList;
    }

    public void setDatasourceMOList(List<DatasourceMO> datasourceMOList) {
        this.datasourceMOList = datasourceMOList;
    }

    public List<FormworkMO> getFormworkMOList() {
        return formworkMOList;
    }

    public void setFormworkMOList(List<FormworkMO> formworkMOList) {
        this.formworkMOList = formworkMOList;
    }

    public List<RoleMO> getRoleMOList() {
        return roleMOList;
    }

    public void setRoleMOList(List<RoleMO> roleMOList) {
        this.roleMOList = roleMOList;
    }
}
