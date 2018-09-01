package cn.wukun.web;

import cn.wukun.dao.UserScoreMapper;
import cn.wukun.domain.UserScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/userScore")
public class UserScoreController {

    @Autowired
    private UserScoreMapper userScoreMapper;

    @RequestMapping("/getUserScore")
    public List<UserScore> getUserScore(){
        List<UserScore> userScoreList = new ArrayList<>();
        UserScore userScore = userScoreMapper.getUserScoreByUserName("吴堃");
        userScoreList.add(userScore);
        userScore = userScoreMapper.getUserScoreByUserId(1);
        userScoreList.add(userScore);
        return userScoreList;
    }
}
