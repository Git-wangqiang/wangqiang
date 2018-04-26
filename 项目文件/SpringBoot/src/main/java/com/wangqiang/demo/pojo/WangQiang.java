package com.wangqiang.demo.pojo;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration   //标记这是一个需要读取配置的类
@ConfigurationProperties(prefix = "com.wangqiang")  //标记配置文件中参数名的前缀
@PropertySource(value = "classpath:application.properties") //标记配置文件的位置，文件被打包后配置文件会在classpath下
public class WangQiang {

    private String name;
    private String path;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
