package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.mapper.ExportAndImportMapper;
import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.service.ExportAndImportService;
import com.mysql.jdbc.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

    @Override
    public List<FormworkResponseMO> selectFormwork(FormworkRequestMO formworkRequestMO) {
        return exportAndImportMapper.selectFormwork(formworkRequestMO);
    }

    @Override
    public void createformwork(CreateFormRequestMO createFormRequestMO) {
        List<CreateFormResponseMO> createFormResponseMOList = exportAndImportMapper.getCloumns(createFormRequestMO);
        final String[] filename = {"nullexcle"};
        Map<String, Integer> accts = new HashMap<String, Integer>() {
            {
                if (createFormResponseMOList != null && createFormResponseMOList.size() > 0) {
                    filename[0] = createFormResponseMOList.get(0).getTablename();
                    for (CreateFormResponseMO createForm : createFormResponseMOList) {
                        put(createForm.getCloumtname(), createForm.getCloumpoint());
                    }
                }
            }
        };
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("FXT");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        int i = 0;
        for (String key : accts.keySet()) {
            HSSFCell cell = row1.createCell(i);
            // 设置单元格内容
            cell.setCellValue(key);
            i++;
        }
        // 第六步，将文件存到指定位置
        try {
            String path = "/resources/templates/" + filename[0] + ".xls";
            File file = new File(path);
            //如果已经存在则删除
            if (file.exists()) {
                file.delete();
            }
            //检查父包是否存在
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            //创建文件
            file.createNewFile();
            FileOutputStream fout = new FileOutputStream(path);
            wb.write(fout);
            String str = "导出成功！";
            System.out.println(str);
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            String str1 = "导出失败！";
            System.out.println(str1);
        }
        // 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));
    }

}
