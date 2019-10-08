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
}

