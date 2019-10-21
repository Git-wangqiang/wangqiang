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



    Zckj findZckjByid(Integer id);



}
