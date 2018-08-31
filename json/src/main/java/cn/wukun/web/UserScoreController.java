package cn.wukun.web;

import cn.wukun.business.UserScoreManager;
import cn.wukun.dao.UserScoreDao;
import cn.wukun.domian.JsonResult;
import cn.wukun.domian.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userScore")
public class UserScoreController {

    @Autowired
    private UserScoreDao userScoreDao;
    @Autowired
    private UserScoreManager userScoreManager;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 使用getOne时报错 No serializer found for class org.hibernate.proxy.pojo.javassist.JavassistLazyInitializer and no properties discovered 2 to create BeanSerializer
     * @return
     */
    @RequestMapping("/getUserScore")
    public UserScore getUserScore(){
        return userScoreDao.findOneByUserId(15);
    }

    @RequestMapping("/getUserScoreList")
    public List<UserScore> getUserScoreList(){
        return userScoreDao.findAll();
    }

    @RequestMapping("/getMap")
    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<>();

        UserScore userScore = userScoreDao.findOneByUserId(15);
        map.put("手机号", userScore.getUserMobile());
        map.put("姓名", userScore.getUserName());
        map.put("分数", userScore.getScoreValue());
        map.put("创建时间", simpleDateFormat.format(userScore.getCreateTime()));

        return map;
    }

    @RequestMapping("/insertUserScore")
    public UserScore insertUserScore(){
        UserScore userScore = userScoreManager.fillUserScore("吴堃", null, 86);
        return userScoreDao.save(userScore);
    }

    @RequestMapping("/jsonresult/getUserScore")
    public JsonResult<UserScore> getUserScoreByJsonResult(){
        UserScore userScore = userScoreDao.findOneByUserId(16);
        return new JsonResult<>(userScore);
    }

    @RequestMapping("/jsonresult/getUserScoreList")
    public JsonResult<List<UserScore>> getUserScoreListByJsonResult(){
        List<UserScore> userScoreList = userScoreDao.findAll();
        return new JsonResult<>(userScoreList, "操作是成功的");
    }


    @RequestMapping("/jsonresult/getMap")
    public JsonResult<Map> getMapByJsonResult(){
        Map<String, Object> map = new HashMap<>();

        UserScore userScore = userScoreDao.findOneByUserId(15);
        map.put("手机号", userScore.getUserMobile());
        map.put("姓名", userScore.getUserName());
        map.put("分数", userScore.getScoreValue());
        map.put("创建时间", simpleDateFormat.format(userScore.getCreateTime()));

        return new JsonResult<>(map);
    }

}
