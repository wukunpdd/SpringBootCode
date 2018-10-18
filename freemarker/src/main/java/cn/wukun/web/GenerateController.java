package cn.wukun.web;

import cn.wukun.util.GeneratingEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GenerateController {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.url}")
    private String dbUrl;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${generating.address}")
    private String generatingAddress;
    @Value("${package.name}")
    private String packageName;

    @RequestMapping("/domain")
    public String domain() throws Exception{
        List<String> sList=new ArrayList<String>();

        sList.add("user_score");

        //自定义的生成实体类的方法
        GeneratingEntity entityGenerating=new GeneratingEntity();

        for(int i=0;i<sList.size();i++){
            entityGenerating.generatingEntity(driverClass, dbUrl, username, password, sList.get(i),generatingAddress,packageName);
            entityGenerating.generatingDao(driverClass, dbUrl, username, password, sList.get(i), generatingAddress, packageName);
        }

        return "生成实体类成功";
    }
}
