package cn.wmyskxz;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootdemoApplicationTests {

    @Test
    public static void main(String[] args) {
        List list = new ArrayList();
        List list1 = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list1.add("q");
        list1.add("a");
        list1.add("c");
        list.addAll(list1);
        System.out.println(list);
    }

}
