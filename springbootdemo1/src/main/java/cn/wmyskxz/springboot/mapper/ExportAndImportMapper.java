package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.CreateTableRequestMO;
import cn.wmyskxz.springboot.mo.SelectDataSourceRequestMO;
import cn.wmyskxz.springboot.mo.SelectDataSourceResponseMO;

import java.util.List;

public interface ExportAndImportMapper {
    void createTable(CreateTableRequestMO createTableRequestMO);

    List<SelectDataSourceResponseMO> selectDataSource(SelectDataSourceRequestMO selectDataSourceRequestMO);
}
