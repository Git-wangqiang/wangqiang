package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.mo.*;

import java.util.List;

public interface ExportAndImportService {
    void createTable(CreateTableRequestMO createTableRequestMO);

    List<SelectDataSourceResponseMO> selectDataSource(SelectDataSourceRequestMO selectDataSourceRequestMO);

    String codeGeneration(CreateTableRequestMO createTableRequestMO);

    List<FormworkResponseMO> selectFormwork(FormworkRequestMO formworkRequestMO);

    void createformwork(CreateFormRequestMO createFormRequestMO);
}

