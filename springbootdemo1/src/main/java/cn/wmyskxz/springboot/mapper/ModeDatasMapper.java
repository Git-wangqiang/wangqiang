package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.mo.DelDatasRequestMO;
import cn.wmyskxz.springboot.mo.UserManagerReqoestMO;
import cn.wmyskxz.springboot.mo.UserManagerResponseMO;
import cn.wmyskxz.springboot.mo.ZckjRequestMO;
import cn.wmyskxz.springboot.pojo.Student;
import cn.wmyskxz.springboot.pojo.Zckj;

import java.util.List;


public interface ModeDatasMapper {
    List<Zckj> findZckj(ZckjRequestMO zckjRequestMO);

    void delDataByid(ZckjRequestMO zckjRequestMO);

    void editData(ZckjRequestMO zckjRequestMO);

    void delDatas(DelDatasRequestMO delDatasRequestMO);

    void imporTexcel(ZckjRequestMO zckjRequestMO);

    Zckj findZckjByid(Integer id);

    List<UserManagerResponseMO> userManagerSelect(UserManagerReqoestMO userManagerReqoestMO);

}
