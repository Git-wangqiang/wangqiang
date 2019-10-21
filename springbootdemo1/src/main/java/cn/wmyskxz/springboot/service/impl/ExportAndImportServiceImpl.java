package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mapper.ExportAndImportMapper;
import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.service.ExportAndImportService;
import com.mysql.jdbc.StringUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
    @Transactional
    public void createformwork(CreateFormRequestMO createFormRequestMO) {
        List<CreateFormResponseMO> createFormResponseMOList = exportAndImportMapper.getCloumns(createFormRequestMO);
        Map<Integer, CreateFormResponseMO> accts = null;
        final String[] filename = {"nullexcle"};
        if (createFormResponseMOList != null && createFormResponseMOList.size() > 0) {
            accts = new HashMap<Integer, CreateFormResponseMO>() {
                {
                    filename[0] = createFormResponseMOList.get(0).getTablename();
                    for (CreateFormResponseMO createForm : createFormResponseMOList) {
                        put(createForm.getId(), createForm);
                    }
                }
            };
        } else {
            throw new BusinessException("未查到数据源信息");
        }

        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("FXT");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        int i = 0;
        for (Integer key : accts.keySet()) {
            HSSFCell cell = row1.createCell(i);
            // 设置单元格内容
            cell.setCellValue(accts.get(key).getCloumtnote());
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
        exportAndImportMapper.addFormWork(createFormRequestMO);

    }

    @Override
    public GetCategoryAndRoleResponseMO getCategoryAndRole(GetCategoryAndRoleRequestMO getCategoryAndRoleRequestMO) {
        GetCategoryAndRoleResponseMO getCategoryAndRoleResponseMO = new GetCategoryAndRoleResponseMO();
        List<DatasourceMO> datasourceMOList = exportAndImportMapper.getDataSource(getCategoryAndRoleRequestMO);
        List<FormworkMO> formworkMOList = exportAndImportMapper.getFormwork(getCategoryAndRoleRequestMO);
        List<RoleMO> roleMOList = exportAndImportMapper.getRole(getCategoryAndRoleRequestMO);
        getCategoryAndRoleResponseMO.setDatasourceMOList(datasourceMOList);
        getCategoryAndRoleResponseMO.setFormworkMOList(formworkMOList);
        getCategoryAndRoleResponseMO.setRoleMOList(roleMOList);
        return getCategoryAndRoleResponseMO;
    }

    @Override
    public void saveCategory(SaveCategoryRequestMO saveCategoryRequestMO) {
        exportAndImportMapper.saveCategory(saveCategoryRequestMO);
    }

    @Override
    public List<CreateFormResponseMO> getDatabtCols(GetDatabtColsRequestMO getDatabtColsRequestMO) {
        //根据username查询拥有的角色 和  dataroleformwork表 返回此表的数据
        SaveCategoryRequestMO saveCategoryRequestMO =
                exportAndImportMapper.getDataformworkroleByUsername(getDatabtColsRequestMO);
        List<CreateFormResponseMO> createFormResponseMOList = null;
        if (saveCategoryRequestMO != null) {
            CreateFormRequestMO createFormRequestMO = new CreateFormRequestMO();
            createFormRequestMO.setId(saveCategoryRequestMO.getDatasourceid());
            createFormResponseMOList =
                    exportAndImportMapper.getCloumns(createFormRequestMO);
        }
        //如果不是空就查询出data_detail  字段名  然后返回
        return createFormResponseMOList;
    }

    @Override
    public CreateTableRequestMO getRemarkBytablename(CloumsPropertyRequestMO cloumsPropertyRequestMO) {
        CreateTableRequestMO createTableRequestMO = exportAndImportMapper.getRemarkBytablename(cloumsPropertyRequestMO);
        List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportMapper.getCloumnsByid(createTableRequestMO);
        createTableRequestMO.setCloumsPropertyRequestMOList(cloumsPropertyRequestMOList);
        return createTableRequestMO;
    }

    @Override
    public boolean getFormworkAndrolebyrole(GetFormworkAndrolebyroleRequestMO getFormworkAndrolebyroleRequestMO) {
        SaveCategoryRequestMO saveCategoryRequestMO = exportAndImportMapper.getFormworkAndrolebyrole(getFormworkAndrolebyroleRequestMO);
        if (saveCategoryRequestMO != null) {
            return true;
        } else {
            throw new BusinessException("此角色未绑定数据源信息");
        }
    }

    @Override
    public void addInfo(MakedataInfoRequestMO makedataInfoRequestMO) {
        if (makedataInfoRequestMO != null) {
            StringBuilder addinfosql = new StringBuilder();
            String tablename = makedataInfoRequestMO.getTablename();
            List<Map<String, Object>> mapList = makedataInfoRequestMO.getRequestdata();
            if (mapList != null && mapList.size() > 0) {
                addinfosql.append("insert into ");
                addinfosql.append(tablename);
                addinfosql.append(" (");
                List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportMapper.getClounmsByTablename(tablename);
                Map<String, CloumsPropertyRequestMO> cloumsPropertyRequestMOMap = cloumsPropertyRequestMOList.stream().collect(Collectors.toMap(CloumsPropertyRequestMO::getCloumtname, a -> a, (k1, k2) -> k1));
                for (Map<String, Object> map : mapList) {
                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String cloumtype = cloumsPropertyRequestMOMap.get(entry.getKey()).getCloumtype();
                            String isautoincrement = cloumsPropertyRequestMOMap.get(entry.getKey()).getIsautoincrement();
                            if (!("int".equals(cloumtype) && "1".equals(isautoincrement))) {
                                addinfosql.append(entry.getKey() + ",");
                            }
                        }
                        addinfosql.append(") value (");
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String cloumtype = cloumsPropertyRequestMOMap.get(entry.getKey()).getCloumtype();
                            String isautoincrement = cloumsPropertyRequestMOMap.get(entry.getKey()).getIsautoincrement();
                            if ("int".equals(cloumtype)) {
                                if (!"1".equals(isautoincrement)) {
                                    addinfosql.append(entry.getValue() + ",");
                                }
                            } else if ("varchar".equals(cloumtype)) {
                                addinfosql.append("'" + entry.getValue() + "'" + ",");
                            }
                        }
                        addinfosql.append(")");
                    }
                }
            }
            String reg = ",\\)";
            Pattern pat = Pattern.compile(reg);
            Matcher mat = pat.matcher(addinfosql);
            String longLoadPermit = mat.replaceAll(")");
            System.out.println(longLoadPermit + "\n" + addinfosql);
            makedataInfoRequestMO.setAddinfosql(longLoadPermit);
        }
        exportAndImportMapper.addInfo(makedataInfoRequestMO);
    }

    @Override
    @Transactional
    public void deleteinfoByid(MakedataInfoRequestMO makedataInfoRequestMO) {
        if (makedataInfoRequestMO != null && makedataInfoRequestMO.getId() != null) {
            StringBuilder addinfosql = new StringBuilder();
            addinfosql.append("delete from ");
            addinfosql.append(makedataInfoRequestMO.getTablename());
            addinfosql.append(" where  id = ");
            addinfosql.append(makedataInfoRequestMO.getId());
            makedataInfoRequestMO.setDeleteinfobyidsql(addinfosql.toString());
        }
        exportAndImportMapper.deleteinfoByid(makedataInfoRequestMO);
    }

    @Override
    public void delinfosByids(MakedataInfoRequestMO makedataInfoRequestMO) {
        Integer[] ids = makedataInfoRequestMO.getIds();
        if (ids != null && ids.length > 0) {
            for (Integer id : ids) {
                makedataInfoRequestMO.setId(id);
                exportAndImportMapper.delinfosByids(makedataInfoRequestMO);
            }
        }
    }

    @Override
    public void editinfo(MakedataInfoRequestMO updateinfoRequestMO) {
        if (updateinfoRequestMO != null) {
            StringBuilder updateinfosql = new StringBuilder();
            String tablename = updateinfoRequestMO.getTablename();
            List<Map<String, Object>> mapList = updateinfoRequestMO.getRequestdata();
            String id = "";
            String idvalue = "";
            if (mapList != null && mapList.size() > 0) {
                updateinfosql.append("update ");
                updateinfosql.append(tablename);
                updateinfosql.append(" set ");
                List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportMapper.getClounmsByTablename(tablename);
                Map<String, CloumsPropertyRequestMO> cloumsPropertyRequestMOMap = cloumsPropertyRequestMOList.stream().collect(Collectors.toMap(CloumsPropertyRequestMO::getCloumtname, a -> a, (k1, k2) -> k1));
                for (Map<String, Object> map : mapList) {
                    if (map != null) {
                        for (Map.Entry<String, Object> entry : map.entrySet()) {
                            String cloumtype = cloumsPropertyRequestMOMap.get(entry.getKey()).getCloumtype();
                            String isautoincrement = cloumsPropertyRequestMOMap.get(entry.getKey()).getIsautoincrement();
                            if (!("int".equals(cloumtype) && "1".equals(isautoincrement))) {
                                updateinfosql.append(entry.getKey() + "='" + entry.getValue() + "',");
                            } else {
                                id = entry.getKey();
                                idvalue = (String) entry.getValue();
                            }
                        }
                    }
                }
            }
            updateinfosql.append(" where " + id + "=" + idvalue);
            updateinfosql.replace(updateinfosql.lastIndexOf(","), updateinfosql.lastIndexOf(",") + 1, "");
            updateinfoRequestMO.setUpdateinfosql(updateinfosql.toString());
            System.out.println(updateinfosql.toString());
        }
        exportAndImportMapper.editinfo(updateinfoRequestMO);
    }

    @Override
    public List<CloumsPropertyRequestMO> getClounmsByTablename(String tablename) {
        List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportMapper.getClounmsByTablename(tablename);
        if (cloumsPropertyRequestMOList != null && cloumsPropertyRequestMOList.size() > 0) {
            for (int i = 0; i < cloumsPropertyRequestMOList.size(); i++) {
                if (!(cloumsPropertyRequestMOList.get(i).getIsselect() != null && "1".equals(cloumsPropertyRequestMOList.get(i).getIsselect()))) {
                    cloumsPropertyRequestMOList.remove(i);
                }
            }
        }
        return cloumsPropertyRequestMOList;
    }

    @Override
    public List<Map<String, Object>> getEmpAsMapById(CurrencyRequestMO currencyRequestMO) {
        if (currencyRequestMO != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select * from ");
            sql.append(currencyRequestMO.getTablename());
            sql.append(" where 1=1 ");
            if (currencyRequestMO.getSelectterm() != null && currencyRequestMO.getSelectterm().size() > 0) {
                for (Map<String, String> map : currencyRequestMO.getSelectterm()) {
                    if (map != null) {
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            if (entry.getValue() != null) {
                                sql.append(" and " + entry.getKey() + " like '%" + entry.getValue() + "%'");
                            }

                        }
                    }
                }
            }
            currencyRequestMO.setSelectsql(sql.toString());
        }
        List<Map<String, Object>> map = exportAndImportMapper.getEmpAsMapById(currencyRequestMO);
        return map;
    }

    @Override
    public Map<String, Object> importexcel(MultipartFile xlsFile, String tablename) {
         /*   将上传到的MultipartFile转为输入流，然后交给POI去解析即可，第一步需要创建Workbook，HSSFWorkbook和XSSFWorkbook都实现了Workbook
        接口，也可以创建HSSFWorkbook或XSSFWorkbook，它们方法名基本一致，需要注意的是POI读取excel2003、excel2007存在兼容性问题，excel主
        要有两类：xls、xlsx，HSSFWorkbook：是操作Excel2003以前（包括2003）
        的版本，扩展名是.xls，XSSFWorkbook：是操作Excel2007的版本，扩展名是.xlsx，如果使用XSSFWorkbook类导入Excel2003版本的表就会出错*/
        Map<String, Object> result = new HashMap<>();
        // contentType
        // String contentType = file.getContentType();
        // excel文件名
        // String fileName = file.getOriginalFilename();
        if (xlsFile.isEmpty()) {
            result.put("code", 500);
            result.put("message", "导入文件为空！");
            return result;
        }
        // 根据不同excel创建不同对象,Excel2003版本-->HSSFWorkbook,Excel2007版本-->XSSFWorkbook
        Workbook wb = null;
        InputStream im = null;
        try {
            im = xlsFile.getInputStream();
            wb = WorkbookFactory.create(im);
            // 根据页面index 获取sheet页
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            Row row1 = null;
            List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportMapper.getClounmsByTablename(tablename);
            Map<String, CloumsPropertyRequestMO> cloumsPropertyRequestMOMap = cloumsPropertyRequestMOList.stream().collect(Collectors.toMap(CloumsPropertyRequestMO::getCloumtname, a -> a, (k1, k2) -> k1));
            // 循环sheet页中数据从第x行开始,例:第3行开始为导入数据
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 获取每一行数据
                row = sheet.getRow(i);
                row1 = sheet.getRow(0);//第一行题目的内容
                Map<String, String> map = new HashMap<>();
                String text = "";
               /* List listrow1 = IteratorUtils.toList(row1.cellIterator());
                List listrow = IteratorUtils.toList(row.cellIterator());*/
              /*  //第二行数据
                for (i = 0; i < listrow.size(); i++) {
                    map.put(listrow.get(i),cloumsPropertyRequestMOMap.get(listrow1.get(i)).getCloumtname());
                }*/
                int time = 0;
                for (Iterator iterator = row.cellIterator(); iterator.hasNext(); ) {
                    Cell cell = (Cell) iterator.next();
                    //根据单元的的类型 读取相应的结果
                    if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) text = cell.getStringCellValue();
                    else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
                        text = String.valueOf(cell.getNumericCellValue());
                    else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) text = cell.getCellFormula();
                    CloumsPropertyRequestMO cloumsPropertyRequestMO = cloumsPropertyRequestMOList.get(time);
                    time++;
                    if (cloumsPropertyRequestMO != null) {
                        map.put(cloumsPropertyRequestMO.getCloumtname(), text);
                    } else {
                        throw new BusinessException("字段不存在");
                    }
                }

                if (map != null) {
                    StringBuffer addinfosql = new StringBuffer();
                    addinfosql.append("insert into ");
                    addinfosql.append(tablename);
                    addinfosql.append(" (");
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String cloumtype = cloumsPropertyRequestMOMap.get(entry.getKey()).getCloumtype();
                        String isautoincrement = cloumsPropertyRequestMOMap.get(entry.getKey()).getIsautoincrement();
                        if (!("int".equals(cloumtype) && "1".equals(isautoincrement))) {
                            addinfosql.append(entry.getKey() + ",");
                        }
                    }
                    addinfosql.append(") value (");
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String cloumtype = cloumsPropertyRequestMOMap.get(entry.getKey()).getCloumtype();
                        String isautoincrement = cloumsPropertyRequestMOMap.get(entry.getKey()).getIsautoincrement();
                        if ("int".equals(cloumtype)) {
                            if (!"1".equals(isautoincrement)) {
                                addinfosql.append(entry.getValue() + ",");
                            }
                        } else if ("varchar".equals(cloumtype)) {
                            addinfosql.append("'" + entry.getValue() + "'" + ",");
                        }
                    }
                    addinfosql.append(")");
                    String reg = ",\\)";
                    Pattern pat = Pattern.compile(reg);
                    Matcher mat = pat.matcher(addinfosql);
                    String longLoadPermit = mat.replaceAll(")");
                    System.out.println(longLoadPermit + "\n" + addinfosql);
                    MakedataInfoRequestMO makedataInfoRequestMO = new MakedataInfoRequestMO();
                    makedataInfoRequestMO.setAddinfosql(longLoadPermit);
                    exportAndImportMapper.addInfo(makedataInfoRequestMO);
                }
            }
            // exportAndImportMapper.importexcel(zckjRequestMOList);
        } catch (Exception e1) {
            // 回滚数据
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e1.printStackTrace();
        } finally {
            try {
                im.close();
                wb.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        result.put("code", 200);
        result.put("message", "导入成功!");
        return result;
    }

    @Override
    public void saveExcle(LeadingOutRequestMO leadingOutRequestMO) {
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("FXT");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //行数
        int rowNum = 1;
        List<Map<String, Object>> checkdata = leadingOutRequestMO.getCheckdata();
        List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportMapper.getClounmsByTablename(leadingOutRequestMO.getTablename());
        Map<String, CloumsPropertyRequestMO> cloumsPropertyRequestMOMap = cloumsPropertyRequestMOList.stream().collect(Collectors.toMap(CloumsPropertyRequestMO::getCloumtname, a -> a, (k1, k2) -> k1));

        if (checkdata != null && checkdata.size() > 0) {
            for (Map<String, Object> map : checkdata) {
                if (map != null && map.size() > 0) {
                    Iterator iterator = map.entrySet().iterator();
                    HSSFRow newRow = sheet.createRow(rowNum);
                    rowNum++;
                    int i = 0;
                    while (iterator.hasNext()) {
                        Map.Entry entry = (Map.Entry) iterator.next();
                        Object key = entry.getKey();
                        Object val = entry.getValue();
                        Integer mapsize = map.entrySet().size();
                        HSSFCell cellOne = row1.createCell(i);
                        // 设置单元格内容
                        cellOne.setCellValue(cloumsPropertyRequestMOMap.get(key).getCloumtnote());
                        //创建一行行记录
                        // 在sheet里创建下一行

                        // 创建单元格并设置单元格内容
                        //newRow.createCell(i).setCellValue((String) key);
                        if (val.toString() != null && val.toString() != "") {
                            newRow.createCell(i).setCellValue(val.toString());
                        } else {
                            newRow.createCell(i).setCellValue("空");
                        }
                        i++;
                    }
                }
            }
        }

        // 第六步，将文件存到指定位置
        try {
            String path = "/resources/down/" + leadingOutRequestMO.getTablename() + ".xls";
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
