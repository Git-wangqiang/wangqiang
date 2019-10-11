package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.*;
import cn.wmyskxz.springboot.pojo.Student;
import cn.wmyskxz.springboot.pojo.Zckj;
import cn.wmyskxz.springboot.pojo.Zckj1;

import java.util.List;
import java.util.Map;


public interface ModeDatasMapper {
    List<Zckj1> findZckj(ZckjRequestMO zckjRequestMO);


    void delDataByid(ZckjRequestMO zckjRequestMO);

    void editData(ZckjRequestMO zckjRequestMO);

    void delDatas(DelDatasRequestMO delDatasRequestMO);

    void imporTexcel(ZckjRequestMO zckjRequestMO);

    Zckj findZckjByid(Integer id);

    List<UserManagerResponseMO> userManagerSelect(UserManagerReqoestMO userManagerReqoestMO);

    List<Map<String, Object>> getEmpAsMapById(CurrencyRequestMO currencyRequestMO);
}
