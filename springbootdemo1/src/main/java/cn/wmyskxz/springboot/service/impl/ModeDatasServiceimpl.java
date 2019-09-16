package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.exceptions.BusinessException;
import cn.wmyskxz.springboot.mapper.ModeDatasMapper;
import cn.wmyskxz.springboot.mo.DelDatasRequestMO;
import cn.wmyskxz.springboot.mo.UserManagerReqoestMO;
import cn.wmyskxz.springboot.mo.UserManagerResponseMO;
import cn.wmyskxz.springboot.mo.ZckjRequestMO;
import cn.wmyskxz.springboot.pojo.Zckj;
import cn.wmyskxz.springboot.service.ModeDatasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "modeDatasService")
public class ModeDatasServiceimpl implements ModeDatasService {
    @Autowired
    private ModeDatasMapper modeDatasMapper;

    @Override
    public List<Zckj> findZckj(ZckjRequestMO zckjRequestMO) {
        return modeDatasMapper.findZckj(zckjRequestMO);
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

}
