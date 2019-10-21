package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.service.ExportAndImportService;
import cn.wmyskxz.springboot.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/api")
public class ExportAndImportContriller {

    @Autowired
    private ExportAndImportService exportAndImportService;

    @RequestMapping(value = "/createtable", method = RequestMethod.POST)
    @ResponseBody
    public Object creaTetable(@RequestBody CreateTableRequestMO createTableRequestMO) {
        exportAndImportService.createTable(createTableRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/selectdatasource", method = RequestMethod.POST)
    @ResponseBody
    public Object selectDataSource(@RequestBody SelectDataSourceRequestMO selectDataSourceRequestMO) {
        PageHelper.startPage(selectDataSourceRequestMO.getPage(), selectDataSourceRequestMO.getLimit());
        List<SelectDataSourceResponseMO> data = exportAndImportService.selectDataSource(selectDataSourceRequestMO);
        //查询
        PageInfo<SelectDataSourceResponseMO> page = new PageInfo<SelectDataSourceResponseMO>(data);
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


    @RequestMapping(value = "/codegeneration", method = RequestMethod.POST)
    @ResponseBody
    public Object codeGeneration(@RequestBody CreateTableRequestMO createTableRequestMO) {
        return JsonUtil.success(exportAndImportService.codeGeneration(createTableRequestMO));
    }

    @RequestMapping(value = "/selectformwork", method = RequestMethod.POST)
    @ResponseBody
    public Object selectFormwork(@RequestBody FormworkRequestMO formworkRequestMO) {
        PageHelper.startPage(formworkRequestMO.getPage(), formworkRequestMO.getLimit());
        List<FormworkResponseMO> data = exportAndImportService.selectFormwork(formworkRequestMO);
        //查询
        PageInfo<FormworkResponseMO> page = new PageInfo<FormworkResponseMO>(data);
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


    @RequestMapping(value = "/selectdatasourcenopage", method = RequestMethod.POST)
    @ResponseBody
    public Object selectDataSourceNopage(@RequestBody SelectDataSourceRequestMO selectDataSourceRequestMO) {
        List<SelectDataSourceResponseMO> data = exportAndImportService.selectDataSource(selectDataSourceRequestMO);
        return JsonUtil.success(data);
    }

    @RequestMapping(value = "/createformwork", method = RequestMethod.POST)
    @ResponseBody
    public Object createformwork(@RequestBody CreateFormRequestMO createFormRequestMO) {
        exportAndImportService.createformwork(createFormRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/getcategoryandrole", method = RequestMethod.POST)
    @ResponseBody
    public Object getCategoryAndRole(@RequestBody GetCategoryAndRoleRequestMO getCategoryAndRoleRequestMO) {
        GetCategoryAndRoleResponseMO getCategoryAndRoleResponseMO = exportAndImportService.getCategoryAndRole(getCategoryAndRoleRequestMO);
        return JsonUtil.success(getCategoryAndRoleResponseMO);
    }

    @RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCategory(@RequestBody SaveCategoryRequestMO saveCategoryRequestMO) {
        exportAndImportService.saveCategory(saveCategoryRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/getdatabtcols", method = RequestMethod.POST)
    @ResponseBody
    public Object getDatabtCols(@RequestBody GetDatabtColsRequestMO getDatabtColsRequestMO) {
        List<CreateFormResponseMO> createFormResponseMOList = exportAndImportService.getDatabtCols(getDatabtColsRequestMO);
        return JsonUtil.success(createFormResponseMOList);
    }

    @RequestMapping(value = "/getRemarkBytablename", method = RequestMethod.POST)
    @ResponseBody
    public Object getRemarkBytablename(@RequestBody CloumsPropertyRequestMO cloumsPropertyRequestMO) {
        CreateTableRequestMO createTableRequestMO = exportAndImportService.getRemarkBytablename(cloumsPropertyRequestMO);
        return JsonUtil.success(createTableRequestMO);
    }

    @RequestMapping(value = "/getformworkandrolebyrole", method = RequestMethod.POST)
    @ResponseBody
    public Object getFormworkAndrolebyrole(@RequestBody GetFormworkAndrolebyroleRequestMO getFormworkAndrolebyroleRequestMO) {
        boolean flag = exportAndImportService.getFormworkAndrolebyrole(getFormworkAndrolebyroleRequestMO);
        return JsonUtil.success(flag);
    }

    @RequestMapping(value = "/addinfo", method = RequestMethod.POST)
    @ResponseBody
    public Object addInfo(@RequestBody MakedataInfoRequestMO makedataInfoRequestMO) {
        exportAndImportService.addInfo(makedataInfoRequestMO);
        return JsonUtil.success();
    }


    @RequestMapping(value = "/deleteinfoByid", method = RequestMethod.POST)
    @ResponseBody
    public Object deleteinfoByid(@RequestBody MakedataInfoRequestMO makedataInfoRequestMO) {
        exportAndImportService.deleteinfoByid(makedataInfoRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/delinfosByids", method = RequestMethod.POST)
    @ResponseBody
    public Object delinfosByids(@RequestBody MakedataInfoRequestMO makedataInfoRequestMO) {
        exportAndImportService.delinfosByids(makedataInfoRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/editinfo", method = RequestMethod.POST)
    @ResponseBody
    public Object editinfo(@RequestBody MakedataInfoRequestMO makedataInfoRequestMO) {
        exportAndImportService.editinfo(makedataInfoRequestMO);
        return JsonUtil.success();
    }

    @RequestMapping(value = "/getclounmsbytablename", method = RequestMethod.POST)
    @ResponseBody
    public Object getClounmsByTablename(@RequestBody MakedataInfoRequestMO makedataInfoRequestMO) {
        List<CloumsPropertyRequestMO> cloumsPropertyRequestMOList = exportAndImportService.getClounmsByTablename(makedataInfoRequestMO.getTablename());
        return JsonUtil.success(cloumsPropertyRequestMOList);
    }

    @RequestMapping(value = "/getEmpAsMapById", method = RequestMethod.POST)
    @ResponseBody
    public Object getEmpAsMapById(@RequestBody CurrencyRequestMO currencyRequestMO) {
        List<Map<String, Object>> data = exportAndImportService.getEmpAsMapById(currencyRequestMO);
        PageHelper.startPage(currencyRequestMO.getPage(), currencyRequestMO.getLimit());
        //查询
        PageInfo page = new PageInfo(data);
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

    //导入
    @RequestMapping(value = "/importexcel", method = RequestMethod.POST)
    @ResponseBody
    public Object imporTexcel(@RequestParam("file") MultipartFile xlsFile, String tablename) {
        Map<String, Object> map = exportAndImportService.importexcel(xlsFile, tablename);
        return map;
    }


    /**
     * 下载excel模板
     */
    @RequestMapping(value = "/downloadexcel", method = RequestMethod.GET)
    @ResponseBody
    public void downloadPermMatrix(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String formworkname = request.getParameter("formworkname");
        Workbook wb;
        try {
            String path = "E:/resources/templates/" + formworkname + ".xls";
            File file = new File(path);
            InputStream inputStream = new FileInputStream(file);
            // 根据不同excel创建不同对象,Excel2003版本-->HSSFWorkbook,Excel2007版本-->XSSFWorkbook
            wb = WorkbookFactory.create(inputStream);
            response.reset();
            response.setContentType("multipart/form-data");
            if (wb.getClass().getSimpleName() == "HSSFWorkbook") {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + new String("excel模板".getBytes(), "utf-8") + ".xls");
            } else {
                response.setHeader("Content-Disposition",
                        "attachment; filename=" + new String("excel模板".getBytes(), "utf-8") + ".xls");
            }
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/saveExcle", method = RequestMethod.POST)
    @ResponseBody
    public Object saveExcle(@RequestBody LeadingOutRequestMO leadingOutRequestMO) {
        exportAndImportService.saveExcle(leadingOutRequestMO);
        return JsonUtil.success();
    }

    public static void main(String[] args) {
        String regex = "^(?![A-Za-z]+$)(?![A-Z\\d]+$)(?![A-Z\\W]+$)(?![a-z\\d]+$)(?![a-z\\W]+$)(?![\\d\\W]+$)\\S{8,20}$";
        System.out.println("1234@qaz".matches(regex));
    }

}
