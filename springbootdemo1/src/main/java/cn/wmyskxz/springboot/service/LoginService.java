package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.mo.LoginfoRequestMO;
import cn.wmyskxz.springboot.pojo.SysPermission;

import java.util.List;
import java.util.Map;

public interface LoginService {
    Map<String, List<SysPermission>> login(LoginfoRequestMO loginfoRequestMO);
}
