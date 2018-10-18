package cn.wukun.web;

import cn.wukun.util.ApplicationInfo;
import cn.wukun.util.GeneratingEntity;
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

        //自定义的生成实体类的方法
        GeneratingEntity entityGenerating=new GeneratingEntity();

        for(int i=0;i<sList.size();i++){
            entityGenerating.generatingHibernateDomain(applicationInfo, sList.get(i));
            entityGenerating.generatingHibernateDao(applicationInfo, sList.get(i));
        }

        return "生成实体类成功";
    }
}
