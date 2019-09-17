package cn.wmyskxz.springboot.mapper;

import cn.wmyskxz.springboot.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by Administrator on 2018/7/6.
 */

public interface StudentMapper {
    List<Student> findAll();
}
