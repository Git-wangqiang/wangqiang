package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.*;

import java.util.List;
import java.util.Map;

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

    SaveCategoryRequestMO getFormworkAndrolebyrole(GetFormworkAndrolebyroleRequestMO getFormworkAndrolebyroleRequestMO);

    void addInfo(MakedataInfoRequestMO makedataInfoRequestMO);

    List<CloumsPropertyRequestMO> getClounmsByTablename(String tablename);

    void deleteinfoByid(MakedataInfoRequestMO makedataInfoRequestMO);

    void delinfosByids(MakedataInfoRequestMO makedataInfoRequestMO);

    void editinfo(MakedataInfoRequestMO makedataInfoRequestMO);
    List<Map<String, Object>> getEmpAsMapById(CurrencyRequestMO currencyRequestMO);

    void importexcel(List<ZckjRequestMO> zckjRequestMOList);
}

