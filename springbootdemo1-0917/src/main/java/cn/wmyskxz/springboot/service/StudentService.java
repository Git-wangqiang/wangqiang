package cn.wmyskxz.springboot.service;

import cn.wmyskxz.springboot.pojo.Student;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */
public interface StudentService {
   public List<Student> findAll();
}
