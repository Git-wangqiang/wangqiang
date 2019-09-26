package cn.wmyskxz.springboot.controller;


import cn.wmyskxz.springboot.mo.DelDatasRequestMO;
import cn.wmyskxz.springboot.mo.UserManagerReqoestMO;
import cn.wmyskxz.springboot.mo.UserManagerResponseMO;
import cn.wmyskxz.springboot.mo.ZckjRequestMO;
import cn.wmyskxz.springboot.pojo.Zckj;
import cn.wmyskxz.springboot.service.ModeDatasService;

import cn.wmyskxz.springboot.service.UserInfoService;
import cn.wmyskxz.springboot.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import netscape.javascript.JSObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/api")
public class ModeDatasController {

    @Autowired
    private ModeDatasService modeDatasService;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(value = "/zckj", method = RequestMethod.POST)
    @ResponseBody
    public Object zckj(@RequestBody ZckjRequestMO zckjRequestMO) {
        PageHelper.startPage(zckjRequestMO.getPage(), zckjRequestMO.getLimit());
        List<Zckj> data = modeDatasService.findZckj(zckjRequestMO);
        //查询
        PageInfo<Zckj> page = new PageInfo<Zckj>(data);
        System.out.println("总数量：" + page.getTotal());
        System.out.println("当前页查询记录：" + page.getList().size());
        System.out.println("当前页码：" + page.getPageNum());
        System.out.println("每页显示数量：" + page.getPageSize());
        System.out.println("总页：" + page.getPages());
        System.out.println(page);
        Object object = JsonUtil.successTable(page.getList(), page.getTotal());
        System.out.println(object);
        return object;
    }

    //    /api/deldataByid

    @RequestMapping(value = "/deldataByid", method = RequestMethod.POST)
    @ResponseBody
    public Object delDataByid(@RequestBody ZckjRequestMO zckjRequestMO) {
        modeDatasService.delDataByid(zckjRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/editdata", method = RequestMethod.POST)
    @ResponseBody
    public Object editData(@RequestBody ZckjRequestMO zckjRequestMO) {
        modeDatasService.editData(zckjRequestMO);
        return JsonUtil.success();
    }


    @RequestMapping(value = "/deldatas", method = RequestMethod.POST)
    @ResponseBody
    public Object delDatas(@RequestBody DelDatasRequestMO delDatasRequestMO) {
        modeDatasService.delDatas(delDatasRequestMO);
        return JsonUtil.success();
    }


    //导入
    @RequestMapping(value = "/importexcel", method = RequestMethod.POST)
    @ResponseBody
    public Object imporTexcel(@RequestParam("file") MultipartFile xlsFile) {
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
        List<ZckjRequestMO> zckjRequestMOS = null;
        try {
            im = xlsFile.getInputStream();
            wb = WorkbookFactory.create(im);
            // 根据页面index 获取sheet页
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            ZckjRequestMO zckjRequestMO = null;
            List<ZckjRequestMO> zckjRequestMOList = new ArrayList<>();
            // 循环sheet页中数据从第x行开始,例:第3行开始为导入数据
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                // 获取每一行数据
                row = sheet.getRow(i);
                if (row != null) {
                    // 输出表格内容,此处可替换为数据插入操作
                    // 日期,表格数字格式为日期
                  /*  if (null != row.getCell(0) && "" != row.getCell(0).toString()) {
                        //System.out.println((new SimpleDateFormat("yyyy-MM-dd")).format(row.getCell(0).getDateCellValue()));

                    }*/
                    // 内容,表格数字格式为常规
                    zckjRequestMO = new ZckjRequestMO();
                    if (null != row.getCell(1) && "" != row.getCell(1).toString()) {
                        // 如果表格内容为数字,需要设置CellType为string，否则调用getStringCellValue()会出现获取类型错误
                        row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
                        System.out.println(row.getCell(1).getStringCellValue());
                        zckjRequestMO.setRdnf(row.getCell(1).getStringCellValue());
                    }
                    if (null != row.getCell(2) && "" != row.getCell(2).toString()) {
                        zckjRequestMO.setMc(row.getCell(2).getStringCellValue());
                    }
                    if (null != row.getCell(3) && "" != row.getCell(3).toString()) {
                        zckjRequestMO.setYyzt(row.getCell(3).getStringCellValue());
                    }
                    if (null != row.getCell(4) && "" != row.getCell(4).toString()) {
                        zckjRequestMO.setUpdatedate(row.getCell(4).getDateCellValue());
                    }
                    zckjRequestMOList.add(zckjRequestMO);
                }
            }
            modeDatasService.importexcel(zckjRequestMOList);
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


    /**
     * 下载excel模板
     */
    @GetMapping("/downloadexcel")
    public void downloadPermMatrix(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Workbook wb;
        try {
            ClassPathResource resource = new ClassPathResource("/templates/zckj.xlsx");
            InputStream inputStream = resource.getInputStream();
            // 根据不同excel创建不同对象,Excel2003版本-->HSSFWorkbook,Excel2007版本-->XSSFWorkbook
            wb = WorkbookFactory.create(inputStream);
            response.reset();
            response.setContentType("multipart/form-data");
            if (wb.getClass().getSimpleName() == "HSSFWorkbook") {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + new String("excel模板".getBytes(), "utf-8") + ".xlsx");
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + new String("excel模板".getBytes(), "utf-8") + ".xlsx");
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * excel导出
     */
    @GetMapping("/exportexcel")
    public void exportPermMatrixGet(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String pap = URLDecoder.decode(request.getQueryString(), "UTF-8");
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("xxx信息表");
        //此处添加数据
        //id	认定年份	名称	yyzt	时间
        HSSFRow headerRow1 = sheet.createRow(0);
        headerRow1.createCell(0).setCellValue("编号");
        headerRow1.createCell(1).setCellValue("认定年份");
        headerRow1.createCell(2).setCellValue("名称");
        headerRow1.createCell(3).setCellValue("yyzt");
        headerRow1.createCell(4).setCellValue("时间");

        HSSFRow headerRow2 = sheet.createRow(1);
        headerRow2.createCell(0).setCellValue(1);
        headerRow2.createCell(1).setCellValue("2");
        headerRow2.createCell(2).setCellValue("3");
        headerRow2.createCell(3).setCellValue("4");
        headerRow2.createCell(4).setCellValue("5");
        //清空response
        response.reset();
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment; filename=" + new String("excel".getBytes(), "utf-8") + ".xls");
        OutputStream os = new BufferedOutputStream(response.getOutputStream());
        workbook.write(os);
        os.flush();
        os.close();
        workbook.close();
    }

    @GetMapping("/saveExcle")
    public void saveExcle() {
        Map<String, Integer> accts = new HashMap<String, Integer>() {
            {
                put("123456", 125);
                put("123451", 121);
                put("123457", 124);
                put("123459", 122);

            }
        };
        // 创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        // 建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("FXT");
        // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        HSSFCell cellOne = row1.createCell(0);
        // 设置单元格内容
        cellOne.setCellValue("账号");
        HSSFCell cellTwo = row1.createCell(1);
        // 设置单元格内容
        cellTwo.setCellValue("金额");

        //行数
        int rowNum = 1;
        //遍历hashmap
        Iterator iterator = accts.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object key = entry.getKey();
            Object val = entry.getValue();
            //创建一行行记录
            rowNum++;
            // 在sheet里创建下一行
            HSSFRow newRow = sheet.createRow(rowNum);
            // 创建单元格并设置单元格内容
            newRow.createCell(0).setCellValue((String) key);
            newRow.createCell(1).setCellValue((Integer) val);

        }

        // 第六步，将文件存到指定位置
        try {
            String path = "/resources/templates/11.xls";
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
