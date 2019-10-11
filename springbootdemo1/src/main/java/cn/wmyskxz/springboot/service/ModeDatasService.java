package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.Zckj;

import java.util.List;
import java.util.Map;

public interface ModeDatasService {
    List<Zckj> findZckj(ZckjRequestMO zckjRequestMO);

    void delDataByid(ZckjRequestMO zckjRequestMO);

    void editData(ZckjRequestMO zckjRequestMO);

    void delDatas(DelDatasRequestMO delDatasRequestMO);

    void importexcel(List<ZckjRequestMO> list);

    Zckj findZckjByid(Integer id);

    List<Map<String, Object>> getEmpAsMapById(CurrencyRequestMO currencyRequestMO);

}
