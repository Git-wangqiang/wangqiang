package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.*;

import java.util.List;

public interface ExportAndImportMapper {
    void createTable(CreateTableRequestMO createTableRequestMO);

    List<SelectDataSourceResponseMO> selectDataSource(SelectDataSourceRequestMO selectDataSourceRequestMO);

    void addDataSource(CreateTableRequestMO createTableRequestMO);

    void addDataSourceDetail(CloumsPropertyRequestMO cloumsPropertyRequestMO);

    List<FormworkResponseMO> selectFormwork(FormworkRequestMO formworkRequestMO);

    List<CreateFormResponseMO> getCloumns(CreateFormRequestMO createFormRequestMO);

    void addFormWork(CreateFormRequestMO createFormRequestMO);

    List<RoleMO> getRole(GetCategoryAndRoleRequestMO getCategoryAndRoleRequestMO);

    List<DatasourceMO> getDataSource(GetCategoryAndRoleRequestMO getCategoryAndRoleRequestMO);

    List<FormworkMO> getFormwork(GetCategoryAndRoleRequestMO getCategoryAndRoleRequestMO);

    void saveCategory(SaveCategoryRequestMO saveCategoryRequestMO);

    SaveCategoryRequestMO getDataformworkroleByUsername(GetDatabtColsRequestMO getDatabtColsRequestMO);

    CreateTableRequestMO getRemarkBytablename(CloumsPropertyRequestMO cloumsPropertyRequestMO);

    List<CloumsPropertyRequestMO> getCloumnsByid(CreateTableRequestMO createTableRequestMO);
}

