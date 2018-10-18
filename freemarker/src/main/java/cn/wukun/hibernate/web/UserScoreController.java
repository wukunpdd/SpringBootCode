package cn.wukun.hibernate.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import cn.wukun.hibernate.domain.UserScore;
import cn.wukun.hibernate.service.UserScoreService;

@RestController
@RequestMapping("/userScore")
public class UserScoreController{

    @Autowired
    private UserScoreService userScoreService;

    @RequestMapping("/getUserScoreList")
    public List<UserScore> getUserScoreList(){
        return userScoreService.getUserScoreList();
    }

    @RequestMapping("/getPageUserScore")
    public Page<UserScore> getPageUserScore(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        return userScoreService.getPageUserScore(pageNo, pageSize);
    }

    @RequestMapping("/insertUserScore")
    public UserScore insertUserScore(UserScore userScore){
        return userScoreService.insertUserScore(userScore);
    }

    @RequestMapping("/updateUserScore")
    public UserScore updateUserScore(UserScore userScore){
        return userScoreService.updateUserScore(userScore);
    }
}