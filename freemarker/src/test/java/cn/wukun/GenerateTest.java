package cn.wukun;

import cn.wukun.web.GenerateController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GenerateTest {

    @Autowired
    private GenerateController generateController;

    @Test
    public void domain(){
        Map<String, String> map = generateController.domain();
        System.out.println("driverClass:"+map.get("driverClass"));
    }
}
