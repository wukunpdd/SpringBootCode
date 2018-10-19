package cn.wukun.web;

import cn.wukun.util.ApplicationInfo;
import cn.wukun.util.GeneratingEntity;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GenerateController {

    @Autowired
    private ApplicationInfo applicationInfo;

    @RequestMapping("/domain")
    public String domain() throws Exception{

        List<String> sList=new ArrayList<String>();
        sList.add("user_score");

        generateMyBatis(sList);
        return "生成实体类成功";
    }

    public void generateMyBatis(List<String> sList) throws Exception{
        //自定义的生成实体类的方法
        GeneratingEntity entityGenerating=new GeneratingEntity();

        for(int i = 0; i < sList.size(); i++) {
            entityGenerating.generateMyBatisDomain(applicationInfo, sList.get(i));
            entityGenerating.generateMyBatisMapper(applicationInfo, sList.get(i));
            entityGenerating.generateMyBatisDao(applicationInfo, sList.get(i));
            entityGenerating.generateMyBatisService(applicationInfo, sList.get(i));
            entityGenerating.generateMyBatisServiceImpl(applicationInfo, sList.get(i));
            entityGenerating.generateMyBatisWeb(applicationInfo, sList.get(i));
        }
    }

    public void generateHibernate(List<String> sList) throws Exception{
        //自定义的生成实体类的方法
        GeneratingEntity entityGenerating=new GeneratingEntity();

        for(int i = 0; i < sList.size(); i++) {
            entityGenerating.generateHibernateDomain(applicationInfo, sList.get(i));
            entityGenerating.generateHibernateDao(applicationInfo, sList.get(i));
            entityGenerating.generateHibernateService(applicationInfo, sList.get(i));
            entityGenerating.generateHibernateServiceImpl(applicationInfo, sList.get(i));
            entityGenerating.generateHibernateWeb(applicationInfo, sList.get(i));
        }
    }
}
