package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.service.ExportAndImportService;
import cn.wmyskxz.springboot.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Object getRemarkBytablename(@RequestBody CloumsPropertyRequestMO  cloumsPropertyRequestMO) {
        CreateTableRequestMO createTableRequestMO = exportAndImportService.getRemarkBytablename(cloumsPropertyRequestMO);
        return JsonUtil.success(createTableRequestMO);
    }


}
