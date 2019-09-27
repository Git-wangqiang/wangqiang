package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.mapper.ExportAndImportMapper;
import cn.wmyskxz.springboot.mo.CloumsPropertyRequestMO;
import cn.wmyskxz.springboot.mo.CreateTableRequestMO;
import cn.wmyskxz.springboot.mo.SelectDataSourceRequestMO;
import cn.wmyskxz.springboot.mo.SelectDataSourceResponseMO;
import cn.wmyskxz.springboot.service.ExportAndImportService;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "ExportAndImportService")
public class ExportAndImportServiceImpl implements ExportAndImportService {
    @Autowired
    private ExportAndImportMapper exportAndImportMapper;

    @Override
    @Transactional
    public void createTable(CreateTableRequestMO createTableRequestMO) {
        StringBuilder sql = new StringBuilder();
        sql.append("create table sbxt." + createTableRequestMO.getTablename() + " (");
        List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = createTableRequestMO.getCloumsPropertyRequestMOList();
        if (cloumsPropertyRequestMOList != null && cloumsPropertyRequestMOList.size() > 0) {
            String key = "";
            for (CloumsPropertyRequestMO cloumsPropertyRequestMO : cloumsPropertyRequestMOList) {
                switch (cloumsPropertyRequestMO.getType()) {
                    case "1":
                        cloumsPropertyRequestMO.setCloumtype("int");
                        break;
                    case "2":
                        cloumsPropertyRequestMO.setCloumtype("varchar");
                        break;
                    case "3":
                        cloumsPropertyRequestMO.setCloumtype("date");
                        break;
                    case "4":
                        cloumsPropertyRequestMO.setCloumtype("float");
                        break;
                }
                if (cloumsPropertyRequestMO.getIskey().equals("1")) {
                    cloumsPropertyRequestMO.setIsautoincrement("1");
                }
                sql.append(cloumsPropertyRequestMO.getCloumtname() + " ");
                if (cloumsPropertyRequestMO.getCloumtype().equals("varchar") || cloumsPropertyRequestMO.getCloumtype().equals("int")) {
                    sql.append(cloumsPropertyRequestMO.getCloumtype() + "(" + cloumsPropertyRequestMO.getCloumlength() + ")");
                } else {
                    sql.append(cloumsPropertyRequestMO.getCloumtype());
                }
                if (cloumsPropertyRequestMO.getIsnull().equals(1)) {
                    if (cloumsPropertyRequestMO.getCloumtype().equals("varchar")) {
                        sql.append(" CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, ");
                    } else {
                        sql.append(" NULL DEFAULT NULL, ");
                    }
                } else {
                    sql.append(" NOT NULL, ");
                }
                if (cloumsPropertyRequestMO.getIsautoincrement() != null && cloumsPropertyRequestMO.getIsautoincrement().equals(1)) {
                    sql.append("AUTO_INCREMENT,");
                }
                if (cloumsPropertyRequestMO.getIskey() != null && cloumsPropertyRequestMO.getIskey().equals(1)) {
                    key = cloumsPropertyRequestMO.getCloumtname();
                }
                cloumsPropertyRequestMO.setTablename(createTableRequestMO.getTablename());
                exportAndImportMapper.addDataSourceDetail(cloumsPropertyRequestMO);
            }
            if (!StringUtils.isNullOrEmpty(key)) {
                sql.append("PRIMARY KEY ('" + key + "') USING BTREE");
            }
            sql.append(") ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic ");
            sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",") + 1, "");
            System.out.println(sql);
            createTableRequestMO.setSqlyuju(sql.toString());
        }
        System.out.println(sql);
        exportAndImportMapper.createTable(createTableRequestMO);
        exportAndImportMapper.addDataSource(createTableRequestMO);
    }

    @Override
    public List<SelectDataSourceResponseMO> selectDataSource(SelectDataSourceRequestMO selectDataSourceRequestMO) {
        return exportAndImportMapper.selectDataSource(selectDataSourceRequestMO);
    }

    @Override
    public String codeGeneration(CreateTableRequestMO createTableRequestMO) {
        StringBuilder sql = new StringBuilder();
        sql.append("create table sbxt." + createTableRequestMO.getTablename() + " (");
        List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = createTableRequestMO.getCloumsPropertyRequestMOList();
        if (cloumsPropertyRequestMOList != null && cloumsPropertyRequestMOList.size() > 0) {
            String key = "";
            for (CloumsPropertyRequestMO cloumsPropertyRequestMO : cloumsPropertyRequestMOList) {
                switch (cloumsPropertyRequestMO.getType()) {
                    case "1":
                        cloumsPropertyRequestMO.setCloumtype("int");
                        break;
                    case "2":
                        cloumsPropertyRequestMO.setCloumtype("varchar");
                        break;
                    case "3":
                        cloumsPropertyRequestMO.setCloumtype("date");
                        break;
                    case "4":
                        cloumsPropertyRequestMO.setCloumtype("float");
                        break;
                }
                if (cloumsPropertyRequestMO.getIskey().equals("1")) {
                    cloumsPropertyRequestMO.setIsautoincrement("1");
                }
                sql.append(cloumsPropertyRequestMO.getCloumtname() + " ");
                if (cloumsPropertyRequestMO.getCloumtype().equals("varchar") || cloumsPropertyRequestMO.getCloumtype().equals("int")) {
                    sql.append(cloumsPropertyRequestMO.getCloumtype() + "(" + cloumsPropertyRequestMO.getCloumlength() + ")");
                } else {
                    sql.append(cloumsPropertyRequestMO.getCloumtype());
                }
                if (cloumsPropertyRequestMO.getIsnull().equals(1)) {
                    if (cloumsPropertyRequestMO.getCloumtype().equals("varchar")) {
                        sql.append(" CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL, ");
                    } else {
                        sql.append(" NULL DEFAULT NULL, ");
                    }
                } else {
                    sql.append(" NOT NULL, ");
                }
                if (cloumsPropertyRequestMO.getIsautoincrement() != null && cloumsPropertyRequestMO.getIsautoincrement().equals(1)) {
                    sql.append("AUTO_INCREMENT,");
                }
                if (cloumsPropertyRequestMO.getIskey() != null && cloumsPropertyRequestMO.getIskey().equals(1)) {
                    key = cloumsPropertyRequestMO.getCloumtname();
                }
            }
            if (!StringUtils.isNullOrEmpty(key)) {
                sql.append("PRIMARY KEY ('" + key + "') USING BTREE");
            }
            sql.append(") ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic ");
            sql.replace(sql.lastIndexOf(","), sql.lastIndexOf(",") + 1, "");
        }
        return sql.toString();
    }

}
