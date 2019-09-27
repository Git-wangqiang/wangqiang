package cn.wmyskxz.springboot.controller;

import cn.wmyskxz.springboot.mo.CreateTableRequestMO;
import cn.wmyskxz.springboot.mo.SelectDataSourceRequestMO;
import cn.wmyskxz.springboot.mo.SelectDataSourceResponseMO;
import cn.wmyskxz.springboot.service.ExportAndImportService;
import cn.wmyskxz.springboot.util.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
