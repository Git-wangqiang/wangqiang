package cn.wmyskxz.springboot.service.impl;

import cn.wmyskxz.springboot.mapper.StudentMapper;
import cn.wmyskxz.springboot.pojo.Student;
import cn.wmyskxz.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/7/9.
 */
@Service(value = "studentService")
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> findAll() {
        return studentMapper.findAll();
    }
}
