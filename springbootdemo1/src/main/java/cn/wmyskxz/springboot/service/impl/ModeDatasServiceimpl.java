package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mapper.ModeDatasMapper;
import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.Zckj;
import cn.wmyskxz.springboot.pojo.Zckj1;
import cn.wmyskxz.springboot.service.ModeDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service(value = "modeDatasService")
public class ModeDatasServiceimpl implements ModeDatasService {
    @Autowired
    private ModeDatasMapper modeDatasMapper;

    @Override
    public List<Zckj> findZckj(ZckjRequestMO zckjRequestMO) {
        List<Zckj> list = new ArrayList<>();
        List<Zckj1> list1 = modeDatasMapper.findZckj(zckjRequestMO);
        return list;
    }

    @Override
    @Transactional
    public void delDataByid(ZckjRequestMO zckjRequestMO) {
        modeDatasMapper.delDataByid(zckjRequestMO);
    }

    @Override
    @Transactional
    public void editData(ZckjRequestMO zckjRequestMO) {
        modeDatasMapper.editData(zckjRequestMO);
    }

    @Override
    @Transactional
    public void delDatas(DelDatasRequestMO delDatasRequestMO) {
        modeDatasMapper.delDatas(delDatasRequestMO);
    }

    @Override
    @Transactional
    public void importexcel(List<ZckjRequestMO> list) {
        for (ZckjRequestMO zckjRequestMO : list) {
            try {
                modeDatasMapper.imporTexcel(zckjRequestMO);
            } catch (Exception e) {
                throw new BusinessException(zckjRequestMO.getMc() + "导入失败！");
            }
        }
    }

    @Override
    public Zckj findZckjByid(Integer id) {
        return modeDatasMapper.findZckjByid(id);
    }

    @Override
    public List<Map<String, Object>> getEmpAsMapById(CurrencyRequestMO currencyRequestMO) {
        if (currencyRequestMO != null) {
             StringBuffer sql = new StringBuffer();
             sql.append("select * from ");
             sql.append(currencyRequestMO.getTablename());
             currencyRequestMO.setSelectsql(sql.toString());
        }
        List<Map<String, Object>> map = modeDatasMapper.getEmpAsMapById(currencyRequestMO);
        return map;
    }

}
