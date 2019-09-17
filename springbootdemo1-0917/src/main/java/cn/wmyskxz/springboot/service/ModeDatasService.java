package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.mo.DelDatasRequestMO;
import cn.wmyskxz.springboot.mo.UserManagerReqoestMO;
import cn.wmyskxz.springboot.mo.UserManagerResponseMO;
import cn.wmyskxz.springboot.mo.ZckjRequestMO;
import cn.wmyskxz.springboot.pojo.Zckj;

import java.util.List;

public interface ModeDatasService {
    List<Zckj> findZckj(ZckjRequestMO zckjRequestMO);

    void delDataByid(ZckjRequestMO zckjRequestMO);

    void editData(ZckjRequestMO zckjRequestMO);

    void delDatas(DelDatasRequestMO delDatasRequestMO);

    void importexcel(List<ZckjRequestMO> list);

    Zckj findZckjByid(Integer id);

}
