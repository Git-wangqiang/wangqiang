package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ExportAndImportService {
    void createTable(CreateTableRequestMO createTableRequestMO);

    List<SelectDataSourceResponseMO> selectDataSource(SelectDataSourceRequestMO selectDataSourceRequestMO);

    String codeGeneration(CreateTableRequestMO createTableRequestMO);

    List<FormworkResponseMO> selectFormwork(FormworkRequestMO formworkRequestMO);

    void createformwork(CreateFormRequestMO createFormRequestMO);

    GetCategoryAndRoleResponseMO getCategoryAndRole(GetCategoryAndRoleRequestMO getCategoryAndRoleRequestMO);

    void saveCategory(SaveCategoryRequestMO saveCategoryRequestMO);

    List<CreateFormResponseMO> getDatabtCols(GetDatabtColsRequestMO getDatabtColsRequestMO);

    CreateTableRequestMO getRemarkBytablename(CloumsPropertyRequestMO cloumsPropertyRequestMO);

    boolean getFormworkAndrolebyrole(GetFormworkAndrolebyroleRequestMO getFormworkAndrolebyroleRequestMO);

    void  addInfo(MakedataInfoRequestMO makedataInfoRequestMO);

    void deleteinfoByid(MakedataInfoRequestMO makedataInfoRequestMO);

    void delinfosByids(MakedataInfoRequestMO makedataInfoRequestMO);

    void editinfo(MakedataInfoRequestMO makedataInfoRequestMO);

    List<CloumsPropertyRequestMO> getClounmsByTablename(String tablename);
    List<Map<String, Object>> getEmpAsMapById(CurrencyRequestMO currencyRequestMO);
    Map<String, Object> importexcel(MultipartFile xlsFile,String tablename);

    void saveExcle(LeadingOutRequestMO leadingOutRequestMO);
}

